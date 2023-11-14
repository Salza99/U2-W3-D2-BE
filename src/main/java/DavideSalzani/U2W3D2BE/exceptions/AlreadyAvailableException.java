package DavideSalzani.U2W3D2BE.exceptions;

public class AlreadyAvailableException extends RuntimeException{
    public AlreadyAvailableException(long id) {
        super("il dispositivo con id: " + id + " è già disponibile");
    }
}
