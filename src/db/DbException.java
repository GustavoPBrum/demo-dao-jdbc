package db;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// Excecao personalizada para acesso a dados
	public DbException(String msg) {
		super(msg);  // Mensagem usada para criar a excecao, sera transmitida para a super classe RuntimeException
	}

	
	
}
