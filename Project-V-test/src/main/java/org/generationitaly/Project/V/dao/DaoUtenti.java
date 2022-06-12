package org.generationitaly.Project.V.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generationitaly.Project.V.entities.Utente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoUtenti implements IDaoUtenti {

	private String dbAddress;
	private String username;
	private String password;

	public DaoUtenti(@Value("${db.address}")String dbAddress, 
			@Value("${db.user}")String username, 
			@Value("${db.pass}")String password) {
		this.dbAddress = dbAddress;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public List<Utente> utenti() {
		List<Utente> ris = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM utenti");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				ris.add(new Utente(rs.getInt("id"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("N_Telefono"), rs.getString("E_mail"), rs.getString("Residenza"), rs.getString("CF"), rs.getString("Pass")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ris;
	}

	@Override
	public Utente utente(int id) {
		Utente ris = null;

		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM utenti WHERE id = ?");

			stm.setInt(1, id);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				ris = new Utente(rs.getInt("id"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("N_Telefono"), rs.getString("E_mail"), rs.getString("Residenza"), rs.getString("CF"), rs.getString("Pass"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ris;
	}

	@Override
	public boolean aggiungi(Utente u) {
		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO utenti (nome, cognome, n_telefono, e_mail, residenza, cf, pass) "
					+ "VALUE (?, ?, ?, ?, ?, ?, ?)");
			
			stm.setString(1, u.getNome());
			stm.setString(2, u.getCognome());
			stm.setString(3, u.getNumTel());
			stm.setString(4, u.getEmail());
			stm.setString(5, u.getIndirizzo());
			stm.setString(6, u.getCF());
			stm.setString(7, u.getPw());
			
			return stm.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean elimina(int id) {
		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("DELETE FROM utenti WHERE id = ?");
			
			stm.setInt(1, id);
			
			return stm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifica(Utente u) {
		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("UPDATE utenti SET nome = ?, cognome = ?, prezzo = ?, n_telefono = ?, e_mail = ?, residenza = ?, cf = ?, pass = ? WHERE id = ?");
			
			stm.setString(1, u.getNome());
			stm.setString(2, u.getCognome());
			stm.setString(3, u.getNumTel());
			stm.setString(4, u.getEmail());
			stm.setString(5, u.getIndirizzo());
			stm.setString(6, u.getCF());
			stm.setString(7, u.getPw());
			stm.setInt(8, u.getIdUtente());
			
			return stm.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Utente login(String e_mail, String pass) {
		Utente ris = null;
		
		try (Connection conn = DriverManager.getConnection(dbAddress)) {
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM utenti WHERE e_mail = ? AND pass = ?");
			
			stm.setString(1, e_mail);
			stm.setString(2, pass);
			
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				ris = new Utente(rs.getInt("id"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("N_Telefono"), rs.getString("E_mail"), rs.getString("Residenza"), rs.getString("CF"), rs.getString("Pass"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ris;
	}
	
}
