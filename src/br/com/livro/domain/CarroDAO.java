package br.com.livro.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarroDAO extends BaseDAO {
	public Carro getCarroById(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()){
				Carro c = createCarro(rs);
				rs.close();
				return c;
			}
		}
		finally{
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Carro> findByName(String name) throws SQLException{
		List<Carro> carros = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where lower(nome) like ?");
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}
		finally{
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return carros;
	}
	
	public List<Carro> findByTipo(String tipo) throws SQLException{
		List<Carro> carros = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where tipo = ?");
			stmt.setString(1, tipo);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}
		finally{
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return carros;
	}
	
	public List<Carro> getCarros() throws SQLException{
		List<Carro> carros = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}
		finally{
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return carros;
	}

	private Carro createCarro(ResultSet rs) throws SQLException {
		Carro c = new Carro();
		c.setId(rs.getLong("id"));
		c.setNome(rs.getString("nome"));
		c.setDesc(rs.getString("descricao"));
		c.setUrlFoto(rs.getString("url_foto"));
		c.setUrlVideo(rs.getString("url_video"));
		c.setLatitude(rs.getString("latitude"));
		c.setLongitude(rs.getString("latitude"));
		c.setTipo(rs.getString("tipo"));
				
		return c;
	}
}
