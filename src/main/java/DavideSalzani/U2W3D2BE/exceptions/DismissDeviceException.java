package DavideSalzani.U2W3D2BE.exceptions;

public class DismissDeviceException extends RuntimeException{
    public DismissDeviceException(long id){
        super("dispositivo con id: " + id + " è dismesso, in attesa di eliminazione e non può essere recuperato!");
    }
}
