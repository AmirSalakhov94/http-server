package tech.itpark.http.exception.client;

public class WrongUrlRequestException extends ClientErrorException {

    private final String codeName = "Wrong url";
    private final int code = 415;

    public String getCodeName() {
        return codeName;
    }

    public int getCode() {
        return code;
    }

    public WrongUrlRequestException() {
    }

    public WrongUrlRequestException(String message) {
        super(message);
    }

    public WrongUrlRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongUrlRequestException(Throwable cause) {
        super(cause);
    }

    public WrongUrlRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
