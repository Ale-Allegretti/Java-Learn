package elezioni.persistenza;

public class BadFileFormatException extends Exception {

	private static final long serialVersionUID = -1693527604836436536L;

	public BadFileFormatException() {
		super();
	}

	public BadFileFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadFileFormatException(String message) {
		super(message);
	}

}
