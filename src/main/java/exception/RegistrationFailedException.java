package exception;

public class RegistrationFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegistrationFailedException() {
		super();
	}

	public RegistrationFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RegistrationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegistrationFailedException(String message) {
		super(message);
	}

	public RegistrationFailedException(Throwable cause) {
		super(cause);
	}

}
