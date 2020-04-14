package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdherentGeneralDAO {
	private static Connection cnx;

//	 public AdherentGeneralDAO(Connection cnx) {
//	 this.cnx = cnx;
//	 }

	public static void setConnexion(Connection connexion) {
		cnx = ConnectionFactory.getConnectionSansAutocommit();
	}

	public static int findNbMaxPrets() {

		int i = 0;
		PreparedStatement ptsmt;
		ResultSet r;

		try {
			ptsmt = cnx.prepareStatement("SELECT NBMAXPRETS FROM ADHERENTGENERAL");
			r = ptsmt.executeQuery();

			while (r.next()) {
				i = r.getInt("NBMAXPRETS");
			}
			return i;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}

	public static int findByDureeMaxPrets() {

		int i = 0;
		PreparedStatement ptsmt;
		ResultSet r;

		try {
			ptsmt = cnx.prepareStatement("SELECT DUREEMAXPRETS FROM ADHERENTGENERAL");
			r = ptsmt.executeQuery();

			while (r.next()) {
				i = r.getInt("DUREEMAXPRETS");
			}
			return i;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return i;
	}

	public static void main(String[] args) throws SQLException {
//		AdherentGeneralDAO.setConnexion(ConnectionFactory.getConnectionSansAutocommit());
//		System.out.println(AdherentGeneralDAO.findByDureeMaxPrets());
//		System.out.println(AdherentGeneralDAO.findNbMaxPrets());

	}

}
