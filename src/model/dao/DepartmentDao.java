package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {  // Faz parte do MODEL nao so as entidades, mas as classes que fazem TRANSFORMACOES dessas entidades
	
	void insert(Department obj);
	void update(Department obj);
	void deleteById(Department obj);
	Department findById(Integer id);
	List<Department> findAll();
}
