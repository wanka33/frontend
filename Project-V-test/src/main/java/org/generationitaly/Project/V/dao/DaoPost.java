package org.generationitaly.Project.V.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generationitaly.Project.V.entities.Commento;
import org.generationitaly.Project.V.entities.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoPost implements IDaoPost {

	private String dbAddress;
	private String username;
	private String password;

	public DaoPost(@Value("${db.address}")String dbAddress, 
			@Value("${db.user}")String username, 
			@Value("${db.pass}")String password) {
		this.dbAddress = dbAddress;
		this.username = username;
		this.password = password;
	}
	

	@Override
    public List<Post> post() {
        List<Post> ris = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM post order by data desc");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ris.add(new Post(rs.getInt("id"), rs.getString("Testo"), rs.getTimestamp("Data"), rs.getInt("id_utente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ris;
    }

	
	
	@Override
	public List<Post> postCommentati() {
		List<Post> ris = new ArrayList<>();
		Post nuovoPost = null;

		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM post order by data desc");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				
				nuovoPost = new Post(rs.getInt("id"), rs.getString("Testo"), rs.getTimestamp("Data"), rs.getInt("id_utente"));
				
				try (Connection conncmm = DriverManager.getConnection(dbAddress, username, password)) {
					ArrayList<Commento> commenti = new ArrayList<Commento>();
					PreparedStatement stmcmm = conn.prepareStatement("SELECT * FROM commenti where id_post = ? order by data desc");
					stmcmm.setInt(1, nuovoPost.getIdPost());
					ResultSet rscmm = stmcmm.executeQuery();
					
					while(rscmm.next()) {
						commenti.add(new Commento
								(rscmm.getInt("id"), rscmm.getString("Testo"), rscmm.getTimestamp("Data"), rscmm.getInt("id_utente"), rscmm.getInt("id_post")));
						
						nuovoPost.setCommenti(commenti);
						
					}
					
					ris.add(nuovoPost);
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ris;
	}
	
	public List<Post> postUtente(int id) {
		List<Post> ris = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("select * from Post where id_utente = ? order by Data desc");
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				ris.add(new Post(rs.getInt("id"), rs.getString("Testo"), rs.getTimestamp("Data"), rs.getInt("id_utente")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ris;
	}
	
	@Override
	public Post post(int id) {
		Post ris = null;

		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM post WHERE id = ?");

			stm.setInt(1, id);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				ris = new Post(rs.getInt("id"), rs.getString("Testo"), rs.getTimestamp("Data"), rs.getInt("id_utente"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ris;
	}

	@Override
	public boolean aggiungi(Post p) {
		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO post (testo, data, id_utente) VALUE (?, ?, ?)");
			
			stm.setString(1, p.getTesto());
			stm.setTimestamp(2, p.getData());
			stm.setInt(3, p.getIdUtente());
			
			return stm.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean elimina(int id) {
		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("DELETE FROM post WHERE id = ?");
			
			stm.setInt(1, id);
			
			return stm.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifica(Post p) {
		try (Connection conn = DriverManager.getConnection(dbAddress, username, password)) {
			PreparedStatement stm = conn.prepareStatement("UPDATE post SET testo = ?, data = ?, id_utente = ? WHERE id = ?");
			
			stm.setString(1, p.getTesto());
			stm.setTimestamp(2, p.getData());
			stm.setInt(3, p.getIdUtente());
			stm.setInt(4, p.getIdPost());
			
			return stm.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


}
