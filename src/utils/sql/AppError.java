package utils.sql;

/**
 * @author $(bilal belhaj)
 **/
public class AppError extends Exception{
    private String message;
    public AppError(String message) {
        this.message = message;
    }
}
