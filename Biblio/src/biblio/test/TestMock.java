package biblio.test;

import java.text.ParseException;

import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.BiblioException;
import biblio.domain.EmpruntArchive;
import biblio.domain.EmpruntEnCours;

public class TestMock {

	public static void main(String[] args) throws ParseException, BiblioException {

		UtilisateursDao dao = new UtilisateursDao();
		ExemplaireDao exdao = new ExemplaireDao();

		System.out.println("DEMANDE DE DEUX EXEMPLAIRES PAR LEUR ID\n");
		System.out.println("\t" + exdao.findByKey(1));
		System.out.println("\t" + exdao.findByKey(3));

		System.out.println("\nDEMANDE D'UN ADHERENT PAR SON ID");
		System.out.println("\t" + dao.findByKey("Popo"));

		System.out.println("\n DEMANDE D'UN EMPLOYE PAR SON ID");
		System.out.println("\t" + dao.findByKey("Titi"));
		System.out.println();
		System.out.println("\nCREATION D'UN EMPRUNT EN COURS POUR UN ADHERENT\n");
		EmpruntEnCours emp1 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/02/2019"));
		EmpruntEnCours emp2 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/01/2019"));
		try {
			dao.findByKey("Pipi").addEmpruntEnCours(emp1);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\t" + dao.findByKey("Pipi"));

		System.out.println("\n CREATION D'UN EMPRUNT POUR UN EMPLOYE");
		try {
			dao.findByKey("Titi").addEmpruntEnCours(emp2);
		} catch (BiblioException e) {
			System.out.println("\t" + dao.findByKey("Titi"));
		}

		System.out.println("\nVERIFICATION D'UN EMPRUNT EN COURS POUR UN ADHERENT\n");
		EmpruntEnCours emp3 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/01/2019"));
		try {
			dao.findByKey("Pipi").addEmpruntEnCours(emp3);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n VERIFICATION DE LA POSSIBILITE DE FAIRE PLUS DE TROIS EMPRUNTS SUR UN EMPLOYE\n");
		EmpruntEnCours emp6 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/01/2019"));
		EmpruntEnCours emp7 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/01/2019"));
		EmpruntEnCours emp8 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/02/2019"));
		EmpruntEnCours emp9 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("26/02/2019"));
		try {
			dao.findByKey("Tata").addEmpruntEnCours(emp6);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Tata").addEmpruntEnCours(emp7);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Tata").addEmpruntEnCours(emp8);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Tata").addEmpruntEnCours(emp9);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}

		// dao.findByKey("Tata").isConditionPretAcceptees();
		System.out.println("\nVERIFICATION DE LA POSSIBILITE DE FAIRE UN PRET EN RETARD POUR UN EMPLOYE\n");
		EmpruntEnCours emp10 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("16/01/2019"));
		EmpruntEnCours emp11 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("06/01/2019"));
		EmpruntEnCours emp12 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("19/02/2019"));
		EmpruntEnCours emp13 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("20/02/2019"));
		try {
			dao.findByKey("Pupu").addEmpruntEnCours(emp10);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Pupu").addEmpruntEnCours(emp11);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Pupu").addEmpruntEnCours(emp12);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Pupu").addEmpruntEnCours(emp13);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		///////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////

		System.out.println("\n\nVERIFICATION DE LA POSSIBILITE DE FAIRE PLUS DE TROIS EMPRUNTS SUR UN ADHERENT\n");

		EmpruntEnCours emp15 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("16/02/2019"));
		EmpruntEnCours emp16 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("14/02/2019"));
		EmpruntEnCours emp17 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("19/02/2019"));
		EmpruntEnCours emp18 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("20/02/2019"));

		try {
			dao.findByKey("Papa").addEmpruntEnCours(emp15);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Papa").addEmpruntEnCours(emp16);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Papa").addEmpruntEnCours(emp17);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Papa").addEmpruntEnCours(emp18);
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}

		// Liste des emprunts de l'adhérent ayant essayé de faire plus de trois emprunts
		System.out.println("\nVoici la liste de vos emprunts :");
		for (EmpruntEnCours emp21 : dao.findByKey("Papa").getListeEmprunt()) {
			System.out.println(emp21.toString());
		}

		// gestion des retours
		// on suppose un adhérent à qui on attribue trois emprunts

		EmpruntEnCours emp20 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("16/02/2019"));
		EmpruntEnCours emp21 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("14/02/2019"));
		EmpruntEnCours emp22 = new EmpruntEnCours(EmpruntEnCours.sdf.parse("19/02/2019"));

		System.out.println("\nVERIFICATION D'UN RETOUR");

		try {
			dao.findByKey("Popo").addEmpruntEnCours(emp20, exdao.findByKey(1));
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Popo").addEmpruntEnCours(emp21, exdao.findByKey(7));
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}
		try {
			dao.findByKey("Popo").addEmpruntEnCours(emp22, exdao.findByKey(5));
		} catch (BiblioException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n\nVoici la liste de vos livres empruntés\n");
		for (EmpruntEnCours empPopo : dao.findByKey("Popo").getListeEmprunt()) {
			System.out.println(empPopo.toString());
		}

		// String message = JOptionPane.showInputDialog("Lequel souhaitez vous rendre
		// ?");

		dao.findByKey("Popo").retourEmprunt(emp20);

		System.out.println("\nVoici la liste de vos livres empruntés après un retour\n");
		for (EmpruntEnCours empPopo : dao.findByKey("Popo").getListeEmprunt()) {
			System.out.println(empPopo.toString());
		}

		System.out.println("\n\nVoici la liste des emprunts archivés\n");
		for (EmpruntArchive empArchPopo : dao.findByKey("Popo").getListeEmpruntArchive()) {
			System.out.println(empArchPopo.toString());
		}
		System.out.println("\nLe statut de l'exemplaire concerné après retour ");
		System.out.println(exdao.findByKey(1).getStatus());
		System.out.println(emp20.toString());

	}

}
