package exception;

/**
 * Created by Qing on 2/10/2015.
 * A specialized class for displaying Chess error messages
 */
public class ChessException extends Exception{
    String message = null;

    public ChessException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        if (message == null)
            return "An unexpected Chess error has occurred.";
        return message;
    }
}
