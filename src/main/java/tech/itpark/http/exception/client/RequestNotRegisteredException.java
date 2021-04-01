package tech.itpark.http.exception.client;

public class RequestNotRegisteredException extends ClientErrorException {

    private final String codeName = "Request not registereed";
    private final int code = 413;

    public String getCodeName() {
        return codeName;
    }

    public int getCode() {
        return code;
    }

    public RequestNotRegisteredException() {
    }

    public RequestNotRegisteredException(String message) {
        super(message);
    }

    public RequestNotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestNotRegisteredException(Throwable cause) {
        super(cause);
    }

    public RequestNotRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
