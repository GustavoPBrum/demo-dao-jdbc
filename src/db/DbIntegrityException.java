package db;

public class DbIntegrityException extends RuntimeException {  //Excecao personalizada de Integridade Referencial

	private static final long serialVersionUID = 1L;

	public DbIntegrityException(String msg) {
		super(msg); // Repassamos a mensagem para a super classe
	}
	
	
	
}
