package DavideSalzani.U2W3D2BE.security.securityController;

import DavideSalzani.U2W3D2BE.exceptions.BadRequestException;
import DavideSalzani.U2W3D2BE.security.securityServices.AuthService;
import DavideSalzani.U2W3D2BE.users.User;
import DavideSalzani.U2W3D2BE.users.UserService;
import DavideSalzani.U2W3D2BE.users.userDTO.NewUserDTO;
import DavideSalzani.U2W3D2BE.users.userDTO.UserLoginDTO;
import DavideSalzani.U2W3D2BE.users.userDTO.UserLoginSuccessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService usersService;
    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body){
        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return usersService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
