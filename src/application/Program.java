package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();  // Uma forma de injecao de indepencia escondendo a implementacao
		
		System.out.println("===== TESTE 1: Seller findById =====");
		Seller seller =  sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("\n===== TESTE 1: Seller findByDepartment =====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);  // Ja criamos a lista com SellerDao
		for(Seller s : list) {
			System.out.println(s);
		}
	}

}
