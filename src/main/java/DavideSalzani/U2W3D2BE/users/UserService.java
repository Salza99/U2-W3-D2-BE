package DavideSalzani.U2W3D2BE.users;

import DavideSalzani.U2W3D2BE.exceptions.AlreadyExistException;
import DavideSalzani.U2W3D2BE.exceptions.BadRequestException;
import DavideSalzani.U2W3D2BE.exceptions.NotFoundException;
import DavideSalzani.U2W3D2BE.users.userDTO.ChangeUserEmailDTO;
import DavideSalzani.U2W3D2BE.users.userDTO.NewUserDTO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Cloudinary cloudinary;

    public User save(NewUserDTO body) throws IOException{
        User u = new User();
        if (userRepo.findByUsername(body.username()).isPresent()) {
            throw new AlreadyExistException(body.username());
        } else if (userRepo.findByEmail(body.email()).isPresent()) {
            throw new AlreadyExistException(body.email());
        }else {
            u.setEmail(body.email());
            u.setName(body.name());
            u.setSurname(body.surname());
            u.setUsername(body.username());
            u.setPassword(body.password());
            userRepo.save(u);
            return u;
        }
    }

    public Page<User> getAll(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return userRepo.findAll(pageable);
    }
    public User getSingleUser(long id){
        return userRepo.findById(id).orElseThrow(()-> new NotFoundException("User"));
    }
    public User patchOnEmail(ChangeUserEmailDTO changeUserMail, long id){
        User found = userRepo.findById(id).orElseThrow(()-> new NotFoundException("User"));
        if (Objects.equals(found.getEmail().toLowerCase(), changeUserMail.email().toLowerCase().trim())) {
            throw new BadRequestException("inserisci una mail diversa da quella precedente");
        } else if (userRepo.findByEmail(changeUserMail.email()).isPresent()) {
            throw new AlreadyExistException(changeUserMail.email());
        }else {
            found.setEmail(changeUserMail.email());
            userRepo.save(found);
            return found;
        }
    }
    public void deleteUser(long id){
       User toRemove = userRepo.findById(id).orElseThrow(()-> new NotFoundException("User"));
        userRepo.delete(toRemove);
    }
    public User UploadImage(MultipartFile file, long id) throws IOException {
        User found = userRepo.findById(id).orElseThrow(()-> new NotFoundException("user"));
        String avatarString = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatarUrl(avatarString);
        userRepo.save(found);
        return found;
    }
}
