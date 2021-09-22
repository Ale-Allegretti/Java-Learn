package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class EsempioDatabase {
	
	private Connection con;

	public static void main(String[] args) {
		EsempioDatabase d = new EsempioDatabase();
		
		try {
			System.out.println(d.getConnection().isClosed());	// ritorna false se connesso correttamente al DB
//			d.esSelect2();
//			d.esInsert("Marco", "Alleg", "marco@email.it", "333333");
			d.esUpdate();
			d.esDelete();
			d.esSelect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void esSelect() throws SQLException {
		String sql = "SELECT id, nome, cognome, email, telefono FROM clienti"; 	// query per gestione clienti
		
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("id: " + rs.getInt(1));
			System.out.println("nome: " + rs.getString(2));
			System.out.println("conome: " + rs.getString(3));
			System.out.println("email: " + rs.getString(4));
			System.out.println("telefono: " + rs.getString(5));
			System.out.println("-------------------");
		}
	}
	
	@SuppressWarnings("unused")
	private void esSelect2() throws SQLException {
		
		/* con criterio di ricerca di telefono inizia con 222 */
		String sql = "SELECT id, nome, cognome, email, telefono FROM clienti WHERE telefono LIKE '%222'";
		
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("id: " + rs.getInt(1));
			System.out.println("nome: " + rs.getString(2));
			System.out.println("conome: " + rs.getString(3));
			System.out.println("email: " + rs.getString(4));
			System.out.println("telefono: " + rs.getString(5));
			System.out.println("-------------------");
		}
	}
	
	@SuppressWarnings("unused")
	private void esInsert(String nome, String cognome, String email, String telefono) throws SQLException {
		
		/* importanti i "?" per prevenire SQL injection tramite la query */
		String sql = "INSERT INTO clienti(nome, cognome, email, telefono) "+
			 	 "VALUES(?, ?, ?, ?)";
		
		/* si potrà recuperare la key che è stata creata per questa query*/
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		/* permette di andare ad inserire i valori, in ordine, nella query */
		ps.setString(1, nome);
		ps.setString(2, cognome);
		ps.setString(3, email);
		ps.setString(4, telefono);
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		System.out.println("L'ID è " + rs.getInt(1));
		
	}
	
	private void esUpdate() throws SQLException {
		String sql = "UPDATE clienti SET telefono = '12345' WHERE id = 3";
		
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.executeUpdate();
	}
	
	private void esDelete() throws SQLException {
		String sql = "DELETE FROM clienti WHERE id = 4";
		
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.executeUpdate();
	}

	private Connection getConnection() throws SQLException {
		if(con == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setDatabaseName("dbjava");
			dataSource.setPortNumber(3306);
			dataSource.setServerName("127.0.0.1");
			dataSource.setUser("root");
			dataSource.setPassword("Wostok1243?");
			
			con = dataSource.getConnection();
		}
		return con;
	}
	
}
