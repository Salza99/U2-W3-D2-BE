package DavideSalzani.U2W3D2BE.devices.deviceDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record NewDeviceDTO(
        @NotEmpty(message = "inserire un tipo di dispositivo (laptop, tablet, smartphone)")
        @Pattern(regexp = "^(?i)(smartphone|tablet|laptop)$", message = "il valore del campo type può essere solo o 'smartphone' o 'tablet' o 'laptop'")
        String type
) {
}
