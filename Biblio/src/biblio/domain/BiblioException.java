package biblio.domain;

public class BiblioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BiblioException() {
		super("Mauvais paramètres");
	}
	
	public BiblioException(String message) {
		super(message);
	}

	
}
