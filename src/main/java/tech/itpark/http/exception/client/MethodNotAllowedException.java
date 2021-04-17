package tech.itpark.http.exception.client;

public class MethodNotAllowedException extends ClientErrorException {

    private final int code = 405;
    private final String codeName = "Method Not Allowed";

    public int getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public MethodNotAllowedException() {
        super();
    }

    public MethodNotAllowedException(String message) {
        super(message);
    }

    public MethodNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodNotAllowedException(Throwable cause) {
        super(cause);
    }

    public MethodNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
