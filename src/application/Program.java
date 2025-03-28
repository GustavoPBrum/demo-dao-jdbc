package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();  // Uma forma de injecao de indepencia escondendo a implementacao
		
		System.out.println("===== TESTE 1: Seller findById =====");
		Seller seller =  sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("\n===== TESTE 2: Seller findByDepartment =====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);  // Ja criamos a lista com SellerDao
		for(Seller s : list) {
			System.out.println(s);
		}
		
		System.out.println("\n===== TESTE 3: Seller findAll =====");
		list = sellerDao.findAll();  // Ja criamos a lista com SellerDao
		for(Seller s : list) {
			System.out.println(s);
		}
		
		System.out.println("\n===== TESTE 4: Seller insert =====");
		Seller s = new Seller(null, "RenanPlay", "caveira@gmail.com", new Date(), 3121.12, department);
		sellerDao.insert(s);
		System.out.println("Inserido! Novo Id = " + s.getId());

		System.out.println("\n===== TESTE 5: Seller update =====");
		seller = sellerDao.findById(10);  // Pegamos o obj 10 (renan) e guardamos nesse obj atualizando
		seller.setName("Sheipado");  // Mudamos nomes
		sellerDao.update(seller);  // Fara update de todos os dados
		System.out.println("Atualizacao completa");
		
		System.out.println("\n===== TESTE 6: Seller delete=====");
		System.out.print("Diga o ID do vendedor a ser deletado: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Exclusao concluida");
		
		
		sc.close();
	}

}
