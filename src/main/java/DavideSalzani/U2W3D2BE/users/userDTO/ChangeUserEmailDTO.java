package DavideSalzani.U2W3D2BE.users.userDTO;

import jakarta.validation.constraints.NotEmpty;

public record ChangeUserEmailDTO(
        @NotEmpty(message = "campo email non può essere lasciato vuoto!")
        @jakarta.validation.constraints.Email(regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}", message = "inserire una mail valida!")
        String email
) {
}
