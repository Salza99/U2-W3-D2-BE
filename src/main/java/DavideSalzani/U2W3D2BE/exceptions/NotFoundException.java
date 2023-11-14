package DavideSalzani.U2W3D2BE.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String whichRecord){
        super(whichRecord + " non presente a database!");
    }
}
