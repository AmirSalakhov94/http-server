package tech.itpark.http.exception.client;

public class WrongHttpStartLineException extends ClientErrorException {

    private final String codeName = "Wrong http";
    private final int code = 411;

    public String getCodeName() {
        return codeName;
    }

    public int getCode() {
        return code;
    }

    public WrongHttpStartLineException() {
    }

    public WrongHttpStartLineException(String message) {
        super(message);
    }

    public WrongHttpStartLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongHttpStartLineException(Throwable cause) {
        super(cause);
    }

    public WrongHttpStartLineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
