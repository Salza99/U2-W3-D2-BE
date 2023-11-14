package DavideSalzani.U2W3D2BE.exceptions;

import java.time.LocalDate;

public record ErrorsPayloadDTO(
        String message,
        LocalDate timeStamp
) {
}
