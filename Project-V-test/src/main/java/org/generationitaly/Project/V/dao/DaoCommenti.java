package org.generationitaly.Project.V.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generationitaly.Project.V.entities.Commento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoCommenti implements IDaoCommenti {

	private String dbAddress;
	private String username;
	private String password;

	public DaoCommenti(@Value("${db.address}")String dbAddress, 
			@Value("${db.user}")String username, 
			@Value("${db.pass}")String password) {
		this.dbAddress = dbAddress;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public List<Commento> commenti(int idPost) {
		List<Commento> ris = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM commenti where id_post = ?");
			stm.setInt(1, idPost);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				ris.add(new Commento(rs.getInt("id"), rs.getString("Testo"), rs.getTimestamp("Data"), rs.getInt("id_utente"), rs.getInt("id_post")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ris;
	}
	
	@Override
	public Commento commento(int id) {
		Commento ris = null;

		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM commenti WHERE id = ?");

			stm.setInt(1, id);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				ris = new Commento(rs.getInt("id"), rs.getString("Testo"), rs.getTimestamp("Data"), rs.getInt("id_utente"), rs.getInt("id_post"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ris;
	}

	@Override
	public boolean aggiungi(Commento c) {
		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO commenti (testo, data, id_utente, id_post) "
					+ "VALUE (?, ?, ?, ?)");
			
			stm.setString(1, c.getTesto());
			stm.setTimestamp(2, c.getData());
			stm.setInt(3, c.getIdUtente());
			stm.setInt(4, c.getIdPost());
			
			
			
			return stm.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean elimina(int id) {
		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("DELETE FROM commenti WHERE id = ?");
			
			stm.setInt(1, id);
			
			return stm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifica(Commento c) {
		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("UPDATE commenti SET testo = ?, data = ?, id_utente = ?, id_post = ? WHERE id = ?");
			
			stm.setString(1, c.getTesto());
			stm.setTimestamp(2, c.getData());
			stm.setInt(3, c.getIdUtente());
			stm.setInt(4, c.getIdPost());
			stm.setInt(5, c.getIdCommento());			
			
			return stm.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
