package DavideSalzani.U2W3D2BE.security;

import DavideSalzani.U2W3D2BE.exceptions.NotFoundException;
import DavideSalzani.U2W3D2BE.exceptions.UnauthorizedException;
import DavideSalzani.U2W3D2BE.users.User;
import DavideSalzani.U2W3D2BE.users.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Per favore passa il Bearer Token nell'Authorization header");
        } else {
            String token = authHeader.substring(7);
            System.out.println("TOKEN -> " + token);

            jwtTools.verifyToken(token);

            String id = jwtTools.extractIdFromToken(token);
            User currentUser = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User"));


            Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);


            filterChain.doFilter(request, response);
        }
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}