package biblio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import biblio.domain.EmpruntArchive;
import biblio.domain.EmpruntEnCours;

public class EmpruntArchiveDAO {

	private Connection cnx;

	public EmpruntArchiveDAO(Connection cnx) {
		this.cnx = cnx;
	}

	public void insertEmpruntArchive(EmpruntArchive e) throws SQLException {
		PreparedStatement pstmt = cnx.prepareStatement("INSERT INTO EMPRUNTARCHIVE VALUES(SEQ_ARCHIVE.nextval,?,?,?,?)");
		pstmt.setDate(1, new java.sql.Date(e.getDateEmprunt().getTime()));
		pstmt.setDate(2, new java.sql.Date(e.getDateRestitutionEff().getTime()));
		pstmt.setString(3, e.getExamplary().getIdExemplaire());
		pstmt.setInt(4, e.getUser().getIdUtilisateur());
		
		pstmt.executeUpdate();
		return;
	}

	public EmpruntArchive findByKey(int idUtilisateur) throws SQLException {
		PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM EMPRUNTENCOURS WHERE IDUTILISATEUR = ?");
		pstmt.setInt(1, idUtilisateur);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		EmpruntArchive empArch = new EmpruntArchive(new java.util.Date(rs.getDate("DATEEMPRUNT").getTime()),
				new Date(System.currentTimeMillis()), null, null);
		pstmt.close();
		rs.close();
		return empArch;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
