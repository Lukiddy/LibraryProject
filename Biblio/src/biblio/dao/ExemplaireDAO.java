package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;

public class ExemplaireDAO {

	private Connection cnx;

	public ExemplaireDAO(Connection cnx) {
		this.cnx = cnx;
	}

	public Exemplaire findByKey(String idexemplaire) throws SQLException {
		Exemplaire ex = null;
		PreparedStatement ptsmt = cnx.prepareStatement("SELECT * FROM EXEMPLAIRE WHERE IDEXEMPLAIRE = ?");
		ptsmt.setString(1, idexemplaire);
		ResultSet r = ptsmt.executeQuery();

		while (r.next()) {
			ex = new Exemplaire(r.getString("idexemplaire"), r.getDate("dateachat"),
					EnumStatusExemplaire.valueOf(r.getString("status")), r.getString("isbn"));
		}

		return ex;

	}
	

	public void updateExemplaire(Exemplaire e) throws SQLException {
		PreparedStatement ptsmt = cnx.prepareStatement("UPDATE EXEMPLAIRE SET STATUS = ? WHERE  IDEXEMPLAIRE = ?");
		ptsmt.setString(1, e.getStatus().toString());
		ptsmt.setString(2, e.getIdExemplaire());
		ptsmt.executeUpdate();
		cnx.commit();
	}

	public List<Exemplaire> findAll() throws SQLException {

		Exemplaire ex = null;
		ArrayList<Exemplaire> listeExemplaire = new ArrayList<>();
		PreparedStatement ptsmt = cnx.prepareStatement("SELECT * FROM EXEMPLAIRE");
		ResultSet r = ptsmt.executeQuery();

		while (r.next()) {
			ex = new Exemplaire(r.getString("idexemplaire"), r.getDate("dateachat"),
					EnumStatusExemplaire.valueOf(r.getString("status")), r.getString("isbn"));
			listeExemplaire.add(ex);
		}

		r.close();
		ptsmt.close();
		return listeExemplaire;

	}

	public static void main(String[] args) {

		ExemplaireDAO exda1 = new ExemplaireDAO(ConnectionFactory.getConnectionSansAutocommit());

		try {

			System.out.println(exda1.findByKey("1"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println();
		try {

			for (Exemplaire e : exda1.findAll()) {
				System.out.println(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
