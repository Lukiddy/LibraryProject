package biblio.ctrl;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import biblio.dao.AdherentGeneralDAO;
import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDAO;
import biblio.dao.ExemplaireDAO;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.BiblioException;
import biblio.domain.Employe;
import biblio.domain.EmpruntEnCours;
import biblio.domain.EmpruntEnCoursDB;
import biblio.domain.EnumCategorieUtilisateur;
import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;

public class EmprunterCtrl {

	private static Adherent unAdherent = null;
	//private static Employe unEmploye = null;
	private static Utilisateur user = null;
	private static Connection appConnect = ConnectionFactory.getConnectionSansAutocommit();
	private static UtilisateursDao userdao = new UtilisateursDao(appConnect);
	private static EmpruntEnCoursDAO empdao = new EmpruntEnCoursDAO(appConnect);
	public static ExemplaireDAO exdao = new ExemplaireDAO(appConnect);
	// public static AdherentGeneralDAO ad

	///////////////////////// connection au serveur/////////////////////////////

	public static void chargerConditionsPret() {

		AdherentGeneralDAO.setConnexion(ConnectionFactory.getConnectionSansAutocommit());
		Adherent.setDureeMaxPrets(AdherentGeneralDAO.findByDureeMaxPrets());
		Adherent.setNbMaxPrets(AdherentGeneralDAO.findNbMaxPrets());
	}

	////////////////////////////// Verification d' utilisateur///////////////////

	public static boolean verifUser(int idUtilisateur) throws HeadlessException, BiblioException {

		try {
			user = userdao.findByKey(idUtilisateur);
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}

		try 
		{
			EmprunterCtrl.creerListeEmpruntEnCours();
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Nombre de prets en cours :" + user.getListeEmprunt().size());

		boolean verifCondition = false;

		if (user.getCategorieUtilisateur().equals(EnumCategorieUtilisateur.ADHERENT))
		{
			if(!user.isConditionPretAcceptees())
				{
				unAdherent = (Adherent) user;
				JOptionPane.showMessageDialog(null, "Pret refusé \nNB de prets :" + user.getNbEmpruntEnCours()
						+ "Maximum :" + AdherentGeneralDAO.findNbMaxPrets() + "\nNb de Retards :" + unAdherent.getNbRetard());
				return verifCondition = false;
			} 
			else 
			{
				 return verifCondition = true;
			}
	
		}
		else {
			return verifCondition= true; 
		}
	}

	public static void entrerUtilisateur(int idUtilisateur) throws SQLException {
		user = userdao.findByKey(idUtilisateur);
	}

	public static void entrerExemplaire(String idexemplaire) {

		try {

			Exemplaire exempl = exdao.findByKey(idexemplaire);

			if (exempl.getStatus().toString().equals("DISPONIBLE")) {

				Calendar maintenant = Calendar.getInstance();
				Date dateMaintenant = new Date(maintenant.getTime().getTime());
				EmpruntEnCours newEmpruntEnCours = new EmpruntEnCours(user, exempl, dateMaintenant);
				empdao.insertEmpruntEnCours(newEmpruntEnCours);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



	public static void updateStatusExemplaireEmprunt(String idexemplaire) throws BiblioException, SQLException {

		Exemplaire motCle = exdao.findByKey(idexemplaire);

		if (motCle.getStatus().toString().equals("DISPONIBLE")) {
			motCle.setStatus(EnumStatusExemplaire.PRETE);
			exdao.updateExemplaire(motCle);
			System.out.println("ok");

		} else {

			throw new BiblioException("Exemplaire non disponible");
		}

	}

//	public static void deleteLivre(String idexemplaire) {
//		try {
//			Exemplaire exempl3 = exdao.findByKey(idexemplaire);
//			exempl3.setStatus(EnumStatusExemplaire.SUPPRIME);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}



	public static void creerListeEmpruntEnCours() throws SQLException {
		ArrayList<EmpruntEnCoursDB> exempl5 = empdao.findByUtilisateur(user);
		ArrayList<EmpruntEnCours> listeEec = new ArrayList<>();
		for (EmpruntEnCoursDB eecdb : exempl5) {
			Exemplaire e = exdao.findByKey(Integer.toString(eecdb.getIdExemplaire()));
			listeEec.add(new EmpruntEnCours(user, e, eecdb.getDateEmprunt()));
		}
		user.setListeEmprunt(listeEec);

	}
	
	

	

	public static void main(String[] args) throws ParseException {

	}

}