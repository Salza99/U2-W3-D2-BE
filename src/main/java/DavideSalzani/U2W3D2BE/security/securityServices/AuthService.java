package DavideSalzani.U2W3D2BE.security.securityServices;

import DavideSalzani.U2W3D2BE.exceptions.NotFoundException;
import DavideSalzani.U2W3D2BE.exceptions.UnauthorizedException;
import DavideSalzani.U2W3D2BE.security.JWTTools;
import DavideSalzani.U2W3D2BE.users.User;
import DavideSalzani.U2W3D2BE.users.UserRepo;
import DavideSalzani.U2W3D2BE.users.userDTO.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepo usersRepo;

    @Autowired
    private JWTTools jwtTools;
    public String authenticateUser(UserLoginDTO body){
        User user = usersRepo.findByEmail(body.email()).orElseThrow(()-> new NotFoundException("User"));
        if(body.password().equals(user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}
