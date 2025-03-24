package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {  // Obter e fechar conexao com o banco de dados

	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");  // Funciona como pares chave/valor, retona uma string.
				
				// Obter uma conexao com o banco de dados. Passamos a URL do BD e as PROPRIEDADES DE CONEXAO (props)
				conn = DriverManager.getConnection(url, props);  // por estar salvo em um obj para esta conexao, proxima vez o if vai falhar
				
				// ***Criar uma conexao com o banco de dados no JDBC eh instanciar um objeto do tipo CONNECTION***
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		try {
			if(conn != null) {  // Se der true, a conexao estara instanciada
				conn.close();
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {  // Por estar na raiz do objeto, serve este desta forma
			Properties props = new Properties();
			// load le o conteudo do arquivo .properties apontado pelo FileInputStream 'fs' 
			// Arquivos .properties contem pares `chave-valor`, sendo guardados estes pares como String no objt props, permitindo ser acessado por meio das chaves.
			props.load(fs);  
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());  // Sendo tratado pela excecao RunTimeException nao precisara tratar com try/catch toda hora
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());  // Sendo tratado pela excecao personalizada RunTimeException nao precisara por try/catch toda hora
			}
		}
	}
}



