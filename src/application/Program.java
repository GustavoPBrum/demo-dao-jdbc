package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		
		Seller seller = new Seller(21, "Junim", "junim@homail.com", new Date(), 3120.00, obj);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();  // Uma forma de injecao de indepencia escondendo a implementacao
		
		System.out.println(seller);
	}

}
