package biblio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biblio.domain.EmpruntEnCours;
import biblio.domain.EmpruntEnCoursDB;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;

public class EmpruntEnCoursDAO {
	private Connection cnx;

	public EmpruntEnCoursDAO(Connection cnx) {
		this.cnx = cnx;
	}
	

	public boolean insertEmpruntEnCours(Utilisateur u, Exemplaire e) throws SQLException {
		PreparedStatement ptsmt = cnx.prepareStatement("INSERT INTO EMPRUNTENCOURS VALUES (" + e.getIdExemplaire()
				+ ", " + u.getIdUtilisateur() + ", sysdate)");
		int r = ptsmt.executeUpdate();
		return r > 0 ? true : false;

	}

	 public void insertEmpruntEnCours(EmpruntEnCours emprunt) throws SQLException {
	PreparedStatement ptsmt = cnx.prepareStatement("INSERT INTO EMPRUNTENCOURS VALUES (?,?,?)");
	ptsmt.setString(1, emprunt.getExemplaire().getIdExemplaire());
	ptsmt.setInt(2, emprunt.getUtilisateur().getIdUtilisateur());
	ptsmt.setDate(3, (Date) emprunt.getDateEmprunt());
	int r = ptsmt.executeUpdate();
	cnx.commit();
	// return r > 0 ? true : false;
	 
	
	 }
	 

	public EmpruntEnCoursDB findByKey(String idExemplaire) throws SQLException {
		EmpruntEnCoursDB emp = null;
		String requete = "SELECT * FROM EMPRUNTENCOURS WHERE IDEXEMPLAIRE = ?";
		PreparedStatement ptsmt = cnx.prepareStatement(requete);
		ptsmt.setString(1, idExemplaire);

		ResultSet r = ptsmt.executeQuery();
		while (r.next()) {
			emp = new EmpruntEnCoursDB(r.getDate("dateEmprunt"), r.getInt("idUtilisateur"), r.getInt("idExemplaire"));
		}

		return emp;

	}

	public ArrayList<EmpruntEnCoursDB> findByUtilisateur(Utilisateur u) throws SQLException {
		ArrayList<EmpruntEnCoursDB> empruntEnCoursDB = new ArrayList<>();
		PreparedStatement ptsmt = cnx
				.prepareStatement("SELECT * FROM EMPRUNTENCOURS WHERE IDUTILISATEUR = " + u.getIdUtilisateur());
		ResultSet r = ptsmt.executeQuery();

		while (r.next()) {

			empruntEnCoursDB.add(new EmpruntEnCoursDB(r.getDate("dateEmprunt"), r.getInt("idUtilisateur"),
					r.getInt("idExemplaire")));
		}

		return empruntEnCoursDB;

	}
	
//	public void updateRetourEmprunt(String IdExemplaire) throws SQLException {
//		 PreparedStatement pstmt = cnx.prepareStatement("DELETE * FROM EMPRUNTENCOURS WHERE IdExemplaire=?");
//		 pstmt.setString(1, IdExemplaire);
//		 pstmt.executeUpdate();
//			 
//	 }
	
	
	public void updateListeEmpruntEnCours(EmpruntEnCours e) throws SQLException
	{
		PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM EMPRUNTENCOURS WHERE IDUTILISATEUR=? AND IDEXEMPLAIRE=?");
		pstmt.setInt(1, e.getUtilisateur().getIdUtilisateur());
		pstmt.setInt(2, Integer.parseInt(e.getExemplaire().getIdExemplaire()));
		pstmt.executeUpdate();
	}
	

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection co = ConnectionFactory.getConnectionSansAutocommit();
		EmpruntEnCoursDAO empdao = new EmpruntEnCoursDAO(co);
		// AdherentGeneralDAO addao = new AdherentGeneralDAO(co);
		UtilisateursDao usedao = new UtilisateursDao(co);

		ExemplaireDAO exda1 = new ExemplaireDAO(co);

		
		//System.out.println(empdao.insertEmpruntEnCours(usedao.findByKey(1), exda1.findByKey("3")));
		// System.out.println(empdao.findByKey(3));

		System.out.println(empdao.findByUtilisateur(usedao.findByKey(2)));
	
	}

}
