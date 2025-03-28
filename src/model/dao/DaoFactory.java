package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {  // Classe auxiliar responsavel por instanciar nossos DAO's
	
	public static SellerDao createSellerDao() {  // Expoe o tipo da interface
		// Agora passamos uma connection como argumento
		return new SellerDaoJDBC(DB.getConnection());  // Mas internamente ela vai instanciar uma implementacao (MACETE PRA NAO EXPOR A IMPLEMENTACAO, SOMENTE INTERFACE)
	}

	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
