package prefix.exception;

public class InvalidKeyException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidKeyException(String msg) {
		super(msg);
	}
}
