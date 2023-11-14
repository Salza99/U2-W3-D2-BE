package DavideSalzani.U2W3D2BE.exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String whichProperty){
        super(whichProperty + " già presente a database!");
    }
}
