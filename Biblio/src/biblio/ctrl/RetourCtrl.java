package biblio.ctrl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntArchiveDAO;
import biblio.dao.EmpruntEnCoursDAO;
import biblio.dao.ExemplaireDAO;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.BiblioException;
import biblio.domain.EmpruntArchive;
import biblio.domain.EmpruntEnCours;
import biblio.domain.EmpruntEnCoursDB;
import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;

public class RetourCtrl {
	private static Adherent unAdherent = null;
	// private static Employe unEmploye = null;
	private static Utilisateur user = null;

	private static Connection appConnect = ConnectionFactory.getConnectionSansAutocommit();
	private static UtilisateursDao userdao = new UtilisateursDao(appConnect);
	private static EmpruntEnCoursDAO empdao = new EmpruntEnCoursDAO(appConnect);
	public static ExemplaireDAO exdao = new ExemplaireDAO(appConnect);
	private static EmpruntArchiveDAO archdao = new EmpruntArchiveDAO(appConnect);

	///////////////////////// connection au serveur/////////////////////////////

	public static void retournerExemplaire(String idExemplaire, int idUtilisateur) throws SQLException, BiblioException {

		user = userdao.findByKey(idUtilisateur);
		Exemplaire ex = exdao.findByKey(idExemplaire);
		RetourCtrl.creerListeEmpruntEnCours();
		EmpruntArchive emparch = archdao.findByKey(user.getIdUtilisateur());
		archdao.insertEmpruntArchive(emparch);

		for (EmpruntEnCours e : user.getListeEmprunt()) {
			if (e.getExemplaire().getIdExemplaire() == ex.getIdExemplaire()) {
				empdao.updateListeEmpruntEnCours(e);
				user.getListeEmprunt().remove(e);
			}
		}
		RetourCtrl.updateStatusExemplaireRetour(ex.getIdExemplaire());
		exdao.updateExemplaire(ex);
	}

	public static void creerListeEmpruntEnCours() throws SQLException {
		ArrayList<EmpruntEnCoursDB> exempl5 = empdao.findByUtilisateur(user);
		ArrayList<EmpruntEnCours> listeEec = new ArrayList<>();
		for (EmpruntEnCoursDB eecdb : exempl5) {
			Exemplaire e = exdao.findByKey(Integer.toString(eecdb.getIdExemplaire()));
			listeEec.add(new EmpruntEnCours(user, e, eecdb.getDateEmprunt()));
		}
		user.setListeEmprunt(listeEec);

	}

	public static void updateStatusExemplaireRetour(String idexemplaire) throws BiblioException, SQLException {

		Exemplaire motCle = exdao.findByKey(idexemplaire);

		if (motCle.getStatus().toString().equals("PRETE")) {
			motCle.setStatus(EnumStatusExemplaire.DISPONIBLE);
			exdao.updateExemplaire(motCle);
			System.out.println("ok");
		}
	}
}
