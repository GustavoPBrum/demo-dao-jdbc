package model.dao.impl;  // Abreviacao de implementacao

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {  //Implementacao JDBC do SellerDao

	private Connection conn;
	
	public  SellerDaoJDBC(Connection conn) {  // Forcando injecao de dependencia e  deixando cconn disponivel para todos os metodos utilizarem
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st  =  conn.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department "
                                         + "ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
			st.setInt(1, id);  // O parametro para chamar a funcao
			rs = st.executeQuery();
			
			if (rs.next()) {  // Caso tenha retornado algum Seller
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				
				Seller seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setDepartment(dep);  // Passamos objeto como um todo montado, pois seller tem o atb Department(obj)
				
				return seller;
			}
			
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}  
}
















