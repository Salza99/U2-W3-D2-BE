package DavideSalzani.U2W3D2BE.users.userDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "campo username non può essere lasciato vuoto!")
        @Size(min = 3, max = 10, message = "l'username deve possedere un numero di caratteri compreso tra 3 e 10")
        String username,
        @NotEmpty(message = "campo name non può essere lasciato vuoto!")
        @Size(min = 3, message = "un nome deve contenere almeno tre lettere!")
        String name,
        @NotEmpty(message = "campo surname non può essere lasciato vuoto!")
        @Size(min = 3, message = "un nome deve contenere almeno tre lettere!")
        String surname,
        @NotEmpty(message = "campo email non può essere lasciato vuoto!")
        @jakarta.validation.constraints.Email(regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}", message = "inserire una mail valida!")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password
) {
}
