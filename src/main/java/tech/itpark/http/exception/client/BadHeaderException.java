package tech.itpark.http.exception.client;

public class BadHeaderException extends ClientErrorException {

    private final String codeName = "Bad Header";
    private final int code = 400;

    public BadHeaderException() {
    }

    public BadHeaderException(String message) {
        super(message);
    }

    public BadHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadHeaderException(Throwable cause) {
        super(cause);
    }

    public BadHeaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCodeName() {
        return codeName;
    }

    public int getCode() {
        return code;
    }
}
