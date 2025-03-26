package model.dao.impl;  // Abreviacao de implementacao

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();  // Tabela com o codigo gerado do obj adicionado
				if(rs.next()) { // Se houver obj
					int id = rs.getInt(1);  // Pega o inteiro da primeira coluna
					obj.setId(id);
				}
				DB.closeResultSet(rs);  // Fechamos ele aqui pois nao existira dentro do escopo do finally
			}
			else  {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
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
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
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

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(dep);
		
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {  // Propagamos a excecao
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st  =  conn.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {  // Pula para a proxima linha se houver
				
				// Na linha atual procura se existe o departamento no map, retorna se houver, mas se nao houver retorna NULO
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {  //  Caso departamento nao exista 
					dep = instantiateDepartment(rs);  // Instanciamos o departamento 
					map.put(rs.getInt("DepartmentId"), dep);  // Primeiro a CHAVE (Numero departamento), depois valor (objeto deparamento)
				}
				
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
			}
			
			return list;
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
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st  =  conn.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name");
			
			st.setInt(1, department.getId());  
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {  // Pula para a proxima linha se houver
				
				// Na linha atual procura se existe o departamento no map, retorna se houver, mas se nao houver retorna NULO
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {  //  Caso departamento nao exista 
					dep = instantiateDepartment(rs);  // Instanciamos o departamento 
					map.put(rs.getInt("DepartmentId"), dep);  // Primeiro a CHAVE (Numero departamento), depois valor (objeto deparamento)
				}
				
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
			}
			
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}  
}

