package errorhandling;

public class personNotFoundExeption extends Exception{
    int statusCode;
    public personNotFoundExeption(int statusCode,String message) {
        super(message);
        this.statusCode=statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
