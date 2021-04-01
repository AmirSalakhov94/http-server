package tech.itpark.http.exception.server;

public class HttpMethodNotSupportedException extends ServerErrorException {

    private final int code = 412;
    private final String codeName = "Malformed Request";

    public HttpMethodNotSupportedException() {
        super();
    }

    public HttpMethodNotSupportedException(String message) {
        super(message);
    }

    public HttpMethodNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpMethodNotSupportedException(Throwable cause) {
        super(cause);
    }

    protected HttpMethodNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }
}
