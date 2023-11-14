package DavideSalzani.U2W3D2BE.exceptions;

public class UnderMaintenanceException extends RuntimeException{
    public UnderMaintenanceException(long id){
        super("il dispositivo con id: " + id + " è in manutenzione e non può quindi essere assegnato ad alcun utente");
    }
}
