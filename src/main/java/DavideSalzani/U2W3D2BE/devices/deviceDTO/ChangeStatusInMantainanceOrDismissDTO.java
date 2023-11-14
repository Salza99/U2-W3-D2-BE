package DavideSalzani.U2W3D2BE.devices.deviceDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record ChangeStatusInMantainanceOrDismissDTO(
        @NotEmpty(message = "inserire 'in_manutenzione' o 'dismesso'")
        @Pattern(regexp = "^(?i)(in_manutenzione|dismesso)$", message = "devi inserire un valore valido (in_manutenzione, dismesso)")
        String status
) {
}
