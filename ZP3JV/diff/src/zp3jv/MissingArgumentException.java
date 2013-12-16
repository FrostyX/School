package zp3jv;

/**
  * Chybějící hodnota pro parametr, který ji vyžaduje
  * @author Jakub Kadlčík
  * @version 1.0
  */
class MissingArgumentException extends Exception {

	public MissingArgumentException(String message) {
		super(message);
	}

	public MissingArgumentException() {
		super();
	}

	private static final long serialVersionUID = 1L;
}
