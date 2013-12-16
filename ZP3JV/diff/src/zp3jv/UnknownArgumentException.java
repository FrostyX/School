package zp3jv;

/**
  * Neexistující parametr programu
  * @author Jakub Kadlčík
  * @version 1.0
  */
class UnknownArgumentException extends Exception {

	public UnknownArgumentException(String message) {
		super(message);
	}

	public UnknownArgumentException() {
		super();
	}

	private static final long serialVersionUID = 1L;
}
