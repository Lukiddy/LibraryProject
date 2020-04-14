package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import biblio.domain.Adherent;
import biblio.domain.Employe;
import biblio.domain.EnumCategorieEmploye;
import biblio.domain.EnumCategorieUtilisateur;
import biblio.domain.Utilisateur;

public class UtilisateursDao {
	private Connection cnx;

	public UtilisateursDao(Connection cnx) {
		this.cnx = cnx;
	}

	
	
	
	public String authentifier(String pseudo) throws SQLException {
		
		String authentifier = ("select idUtilisateur, pwd from Utilisateur where PSEUDONYME =?");
		PreparedStatement pstmtAthentifier = cnx.prepareStatement (authentifier);					 	
		pstmtAthentifier.setString(1,pseudo);
	 	ResultSet rsAuthentifier = pstmtAthentifier.executeQuery();
	 	rsAuthentifier.next();
	 	return rsAuthentifier.getString("PWD");

	}
	
	
	public Utilisateur findByKey(int idUtilisateur) throws SQLException {
		Utilisateur User = null;

		String UtilisateurRequete = ("SELECT * FROM UTILISATEUR "
				+ "LEFT JOIN ADHERENT ON UTILISATEUR.IDUTILISATEUR = ADHERENT.IDUTILISATEUR "
				+ "LEFT JOIN EMPLOYE ON UTILISATEUR.IDUTILISATEUR = EMPLOYE.IDUTILISATEUR "
				+ "WHERE UTILISATEUR.IDUTILISATEUR = ?");

		PreparedStatement pstmtUser = cnx.prepareStatement(UtilisateurRequete);
		pstmtUser.setInt(1, idUtilisateur);
		ResultSet r = pstmtUser.executeQuery();

		while (r.next()) {

			if (EnumCategorieUtilisateur.valueOf(r.getString("categorieUtilisateur"))
					.equals(EnumCategorieUtilisateur.ADHERENT)) {
				

				User = new Adherent(r.getString("nom"), r.getString("prenom"),
						r.getDate("dateNaissance"), r.getString("sexe"), r.getInt("idUtilisateur"),
						r.getString("pwd"), r.getString("pseudonyme"),
						EnumCategorieUtilisateur.valueOf(r.getString("categorieUtilisateur")),
						r.getString("telephone"));

			} else if (EnumCategorieUtilisateur.valueOf(r.getString("categorieUtilisateur"))
					.equals(EnumCategorieUtilisateur.EMPLOYE)) {

				User = new Employe(r.getString("nom"), r.getString("prenom"), r.getDate("dateNaissance"),
						r.getString("sexe"), r.getInt("idUtilisateur"), r.getString("pwd"),
						r.getString("pseudonyme"),
						EnumCategorieUtilisateur.valueOf(r.getString("categorieUtilisateur")),
						EnumCategorieEmploye.valueOf(r.getString("categorieEmploye")),
						r.getString("codeMatricule"));

			} else
				System.out.println("xx");

		}

		r.close();
		pstmtUser.close();
		return User;

	}
	
	
	
	public List <Utilisateur>findAll() throws SQLException{
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		Utilisateur User = null;
		PreparedStatement ptsmt = cnx.prepareStatement("SELECT * FROM UTILISATEUR "
				+ "LEFT JOIN ADHERENT ON UTILISATEUR.IDUTILISATEUR = ADHERENT.IDUTILISATEUR "
				+ "LEFT JOIN EMPLOYE ON UTILISATEUR.IDUTILISATEUR = EMPLOYE.IDUTILISATEUR ");
		ResultSet r = ptsmt.executeQuery();
		
		while(r.next()) {
			
			if (EnumCategorieUtilisateur.valueOf(r.getString("categorieUtilisateur"))
					.equals(EnumCategorieUtilisateur.ADHERENT)) {

				User = new Adherent(r.getString("nom"), r.getString("prenom"),
						r.getDate("dateNaissance"), r.getString("sexe"), r.getInt("idUtilisateur"),
						r.getString("pwd"), r.getString("pseudonyme"),
						EnumCategorieUtilisateur.valueOf(r.getString("categorieUtilisateur")),
						r.getString("telephone"));
				listeUtilisateur.add(User);

			} else if (EnumCategorieUtilisateur.valueOf(r.getString("categorieUtilisateur"))
					.equals(EnumCategorieUtilisateur.EMPLOYE)) {

				User = new Employe(r.getString("nom"), r.getString("prenom"), r.getDate("dateNaissance"),
						r.getString("sexe"), r.getInt("idUtilisateur"), r.getString("pwd"),
						r.getString("pseudonyme"),
						EnumCategorieUtilisateur.valueOf(r.getString("categorieUtilisateur")),
						EnumCategorieEmploye.valueOf(r.getString("categorieEmploye")),
						r.getString("codeMatricule"));
				listeUtilisateur.add(User);

			} else
				System.out.println("xx");
		}
		
		return listeUtilisateur;
		
	}
	
	

	public void chargerVariablesStatiques(){
	AdherentGeneralDAO.setConnexion(ConnectionFactory.getConnectionSansAutocommit());
		Adherent.setDureeMaxPrets(AdherentGeneralDAO.findByDureeMaxPrets());
	Adherent.setNbMaxPrets(AdherentGeneralDAO.findNbMaxPrets());
	}

	
	
	
	
	public void affichageLineByLine(ArrayList<Utilisateur> l) {
		for(Utilisateur u : l) {
			System.out.println(u);
		}
	}

	public static void main(String[] args) throws SQLException {

		UtilisateursDao usedao = new UtilisateursDao(ConnectionFactory.getConnectionSansAutocommit());

//		System.out.println(usedao.findByKey(2));
//		System.out.println(usedao.findByKey(8));
//		System.out.println(usedao.findByKey(10));

		for(Utilisateur u : usedao.findAll()) {
			System.out.println(u);
		}
		
	}

}
