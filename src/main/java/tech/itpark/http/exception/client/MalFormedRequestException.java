package tech.itpark.http.exception.client;

public class MalFormedRequestException extends ClientErrorException {

    private final int code = 412;
    private final String codeName = "Malformed Request";

    public int getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public MalFormedRequestException() {
    }

    public MalFormedRequestException(String message) {
        super(message);
    }

    public MalFormedRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalFormedRequestException(Throwable cause) {
        super(cause);
    }

    public MalFormedRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
