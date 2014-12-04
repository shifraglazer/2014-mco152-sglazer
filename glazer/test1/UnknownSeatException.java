package glazer.test1;

public class UnknownSeatException extends Exception {

	public UnknownSeatException() {
		super();
	}

	public UnknownSeatException(String message) {
		super(message);
	}

	public UnknownSeatException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

}
