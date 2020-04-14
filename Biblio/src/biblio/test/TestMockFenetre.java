package biblio.test;

import java.text.ParseException;

import javax.swing.JOptionPane;

import biblio.dao.ExemplaireDAO;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.BiblioException;
import biblio.domain.EmpruntArchive;
import biblio.domain.EmpruntEnCours;

public class TestMockFenetre {

	public static void main(String[] args) throws ParseException, BiblioException {

		UtilisateursDao dao = new UtilisateursDao();
		ExemplaireDAO exdao = new ExemplaireDAO();

		JOptionPane.showMessageDialog(null, "DEMANDE DE DEUX EXEMPLAIRES PAR LEUR ID", "Requête :",
				JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, exdao.findByKey(1), "Afficher un message", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, exdao.findByKey(3), "Afficher un message", JOptionPane.INFORMATION_MESSAGE);

		JOptionPane.showMessageDialog(null, "\nDEMANDE D'UN ADHERENT PAR SON ID");
		JOptionPane.showMessageDialog(null, "\t" + dao.findByKey("Popo"));

		JOptionPane.showMessageDialog(null, "DEMANDE D'UN EMPLOYE PAR SON ID");
		JOptionPane.showMessageDialog(null, dao.findByKey("Titi"));

		JOptionPane.showMessageDialog(null, "CREATION D'UN EMPRUNT EN COURS POUR UN ADHERENT");
		EmpruntEnCours emp1 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/02/2019"));
		EmpruntEnCours emp2 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/01/2019"));
		try {
			dao.findByKey("Pipi").addEmpruntEnCours(emp1);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		JOptionPane.showMessageDialog(null, dao.findByKey("Pipi"));

		JOptionPane.showMessageDialog(null, "CREATION D'UN EMPRUNT EN COURS POUR UN EMPLOYE");
		try {
			dao.findByKey("Titi").addEmpruntEnCours(emp2);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "Résulat de la requête");
		JOptionPane.showMessageDialog(null, dao.findByKey("Titi"));

		JOptionPane.showMessageDialog(null, "VERIFICATION D'UN EMPRUNT EN COURS POUR UN ADHERENT\n");
		EmpruntEnCours emp3 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/01/2019"));
		try {
			dao.findByKey("Pipi").addEmpruntEnCours(emp3);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		// JOptionPane.showMessageDialog(null, dao.findByKey("Pipi"));

		JOptionPane.showMessageDialog(null,
				"VERIFICATION DE LA POSSIBILITE DE FAIRE PLUS DE TROIS EMPRUNTS SUR UN EMPLOYE\n");
		EmpruntEnCours emp6 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/01/2019"));
		EmpruntEnCours emp7 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/01/2019"));
		EmpruntEnCours emp8 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/02/2019"));
		EmpruntEnCours emp9 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/02/2019"));
		try {
			dao.findByKey("Tata").addEmpruntEnCours(emp6);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Tata").addEmpruntEnCours(emp7);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Tata").addEmpruntEnCours(emp8);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Tata").addEmpruntEnCours(emp9);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		JOptionPane.showMessageDialog(null, "Liste d'emprunt de l'employé concerné");
		for (EmpruntEnCours emp : dao.findByKey("Tata").getListeEmprunt()) {
			JOptionPane.showMessageDialog(null, emp.toString());
		}

		JOptionPane.showMessageDialog(null,
				"\nVERIFICATION DE LA POSSIBILITE DE FAIRE UN PRET EN RETARD POUR UN EMPLOYE\n");
		EmpruntEnCours emp10 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("16/01/2019"));
		EmpruntEnCours emp11 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("06/01/2019"));
		EmpruntEnCours emp12 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("19/02/2019"));
		EmpruntEnCours emp13 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("20/02/2019"));
		try {
			dao.findByKey("Tutu").addEmpruntEnCours(emp10);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Tutu").addEmpruntEnCours(emp11);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Tutu").addEmpruntEnCours(emp12);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Tutu").addEmpruntEnCours(emp13);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		///////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////

		JOptionPane.showMessageDialog(null,
				"\n\nVERIFICATION DE LA POSSIBILITE DE FAIRE PLUS DE TROIS EMPRUNTS SUR UN ADHERENT\n");

		EmpruntEnCours emp15 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("16/02/2019"));
		EmpruntEnCours emp16 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("14/02/2019"));
		EmpruntEnCours emp17 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("19/02/2019"));
		EmpruntEnCours emp18 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("20/02/2019"));

		try {
			dao.findByKey("Papa").addEmpruntEnCours(emp15);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Papa").addEmpruntEnCours(emp16);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Papa").addEmpruntEnCours(emp17);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Papa").addEmpruntEnCours(emp18);
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		// Liste des emprunts de l'adhérent ayant essayé de faire plus de trois emprunts
		JOptionPane.showMessageDialog(null, "Voici la liste de vos emprunts :");
		for (EmpruntEnCours emp21 : dao.findByKey("Papa").getListeEmprunt()) {
			JOptionPane.showMessageDialog(null, emp21.toString());
		}
		
		
		// gestion des retours
		// on suppose un adhérent à qui on attribue trois emprunts

		EmpruntEnCours emp23 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("16/02/2019"));
		EmpruntEnCours emp24 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("14/02/2019"));
		EmpruntEnCours emp25 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("19/02/2019"));

		JOptionPane.showMessageDialog(null, "VERIFICATION D'UN RETOUR");

		try {
			dao.findByKey("Popo").addEmpruntEnCours(emp23, exdao.findByKey(1));
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Popo").addEmpruntEnCours(emp24, exdao.findByKey(7));
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			dao.findByKey("Popo").addEmpruntEnCours(emp25, exdao.findByKey(5));
		} catch (BiblioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		JOptionPane.showMessageDialog(null, "Voici la liste de vos livres empruntés");
		for (EmpruntEnCours empPopo : dao.findByKey("Popo").getListeEmprunt()) {
			JOptionPane.showMessageDialog(null, empPopo.toString());
		}

		// String message = JOptionPane.showInputDialog("Lequel souhaitez vous rendre
		// ?");

		dao.findByKey("Popo").retourEmprunt(emp25);

		JOptionPane.showMessageDialog(null, "Voici la liste de vos livres empruntés après un retour");
		for (EmpruntEnCours empPopo : dao.findByKey("Popo").getListeEmprunt()) {
			JOptionPane.showMessageDialog(null, empPopo.toString());
		}

		JOptionPane.showMessageDialog(null, "Voici la liste des emprunts archivés");
		for (EmpruntArchive empArchPopo : dao.findByKey("Popo").getListeEmpruntArchive()) {
			JOptionPane.showMessageDialog(null, empArchPopo.toString());
		}
		JOptionPane.showMessageDialog(null, exdao.findByKey(5).getStatus());

	}

}
