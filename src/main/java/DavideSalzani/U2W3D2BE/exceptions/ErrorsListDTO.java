package DavideSalzani.U2W3D2BE.exceptions;

import java.time.LocalDate;
import java.util.List;

public record ErrorsListDTO(
        String message,
        LocalDate timestamp,
        List<String> messageList
) {
}
