package DavideSalzani.U2W3D2BE.users;

import DavideSalzani.U2W3D2BE.exceptions.BadRequestException;
import DavideSalzani.U2W3D2BE.users.userDTO.ChangeUserEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public Page<User> getPaginatedUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "name") String orderBy){
        return userService.getAll(page,size,orderBy);
    }
    @GetMapping("{id}")
    public User getSingleUser(@PathVariable long id){
        return userService.getSingleUser(id);
    }
    @PatchMapping("/email/{id}")
    public User changeEmail(@RequestBody @Validated ChangeUserEmailDTO email, BindingResult validation,@PathVariable long id ){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            return userService.patchOnEmail(email, id);
        }
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }
    @PatchMapping("/image/{id}")
    public User uploadImage(@RequestParam("avatar")MultipartFile file, @PathVariable long id) throws IOException {
        return userService.UploadImage(file, id);
    }
}
