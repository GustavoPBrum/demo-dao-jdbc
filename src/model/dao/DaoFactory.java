package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {  // Classe auxiliar responsavel por instanciar nossos DAO's
	
	public static SellerDao createSellerDao() {  // Expoe o tipo da interface
		return new SellerDaoJDBC();  // Mas internamente ela vai instanciar uma implementacao (MACETE PRA NAO EXPOR A IMPLEMENTACAO, SOMENTE INTERFACE)
	}

}
