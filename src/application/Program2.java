package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao	= DaoFactory.createDepartmentDao();  // Uma forma de injecao de indepencia escondendo a implementacao
		
		System.out.println("===== TESTE 1: Department findById =====");
		Department dep = departmentDao.findById(4);
		System.out.println(dep);
	
		System.out.println("\n===== TESTE 2: Department findAll =====");
		List<Department> depList = departmentDao.findAll();
		for (Department d : depList) {
			System.out.println(d);
		}
		
		System.out.println("\n===== TESTE 3: Department insert =====");
		Department dep1 = new Department(null, "teste");
		departmentDao.insert(dep1);
		System.out.println("Inserido! Novo Id = " + dep1.getId());

		System.out.println("\n===== TESTE 4: Department update =====");
		dep = departmentDao.findById(6);
		dep.setName("Calvoesfera");
		departmentDao.update(dep);
		System.out.println("Atualizacao completa");
		
		System.out.println("\n===== TESTE 5: Department delete=====");
		System.out.print("Diga o ID do vendedor a ser deletado: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Exclusao concluida");
		
		
		sc.close();
	}

}
