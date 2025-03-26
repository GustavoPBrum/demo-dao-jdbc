package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();  // Uma forma de injecao de indepencia escondendo a implementacao
		
		Seller seller =  sellerDao.findById(3);
		
		System.out.println(seller);
	}

}
