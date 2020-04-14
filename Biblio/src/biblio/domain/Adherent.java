package biblio.domain;

import java.util.Date;

import biblio.dao.AdherentGeneralDAO;
import biblio.dao.ConnectionFactory;

public class Adherent extends Utilisateur {
	private String telephone;
	private static int nbMaxPrets = 0; //AdherentGeneralDAO.findNbMaxPrets();
	private static int dureeMaxPrets = 0; //AdherentGeneralDAO.findByDureeMaxPrets();
	
	public static void setNbMaxPrets(int nbMaxPrets) {
		Adherent.nbMaxPrets = nbMaxPrets;
	}

	public static void setDureeMaxPrets(int dureeMaxPrets) {
		Adherent.dureeMaxPrets = dureeMaxPrets;
	}

	

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public static int getNbMaxPrets() {
		AdherentGeneralDAO.setConnexion(ConnectionFactory.getConnectionSansAutocommit());
		nbMaxPrets = AdherentGeneralDAO.findNbMaxPrets();
		return nbMaxPrets;
	}

	public static int getDureeMaxPrets() {
		AdherentGeneralDAO.setConnexion(ConnectionFactory.getConnectionSansAutocommit());
		dureeMaxPrets = AdherentGeneralDAO.findByDureeMaxPrets();
		return dureeMaxPrets;
	}

	public Adherent(String nom, String prenom, Date dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme, EnumCategorieUtilisateur categorieUtilisateur) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme, categorieUtilisateur);
		this.setCategorieUtilisateur(categorieUtilisateur);
	}

	public Adherent(String nom, String prenom, Date dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme, EnumCategorieUtilisateur categorieUtilisateur, String telephone) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme, categorieUtilisateur);
		this.setCategorieUtilisateur(categorieUtilisateur);
		this.telephone = telephone;
	}

	public Adherent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNbRetard() {

		long jourMaxMilli = dureeMaxPrets * (24 * 60 * 60 * 1000);
		int nbRetard = 0;

		for (EmpruntEnCours emprunt : listeEmpruntEnCours) {
			Date dateRetourPrevue = new Date(emprunt.getDateEmprunt().getTime() + jourMaxMilli);
			Date dateAujourdhui = new Date(System.currentTimeMillis());
			if (dateAujourdhui.after(dateRetourPrevue))
				nbRetard++;
		}
		return nbRetard;
	}
	
	public void chargerVariablesStatiques(){
//		AdherentGeneralDAO.setConnexion(ConnectionFactory.getConnectionSansAutocommit());
//		Adherent.setDureeMaxPrets(AdherentGeneralDAO.findByDureeMaxPrets());
//		Adherent.setNbMaxPrets(AdherentGeneralDAO.findNbMaxPrets());
	}

	@Override
	public String toString() {
		return super.toString() + "[telephone=" + telephone + "]";
	}

	@Override
	public boolean isConditionPretAcceptees() {

		if (this.getNbRetard() > 0) 
		   {			
			return false;
		   } 
	   else if (this.getNbEmpruntEnCours() == nbMaxPrets)
	   {
			// System.out.println("Désolez vous ne pouvez pas emprunter car vous avez
			// "+this.getNbEmpruntEnCours()+" emprunts en cours !!!");
			return false;
		} else {
			// System.out.println("Votre emprunt a été enregistré !!!");
			return true;
		}

	}

	@Override
	public void addEmpruntEnCours(EmpruntEnCours e) throws BiblioException {
		if (this.getNbRetard() > 0) {
			throw new BiblioException("Désolez vous ne pouvez pas emprunter car vous avez " + this.getNbRetard()
					+ " emprunts en retard !!!\n");
		}
		if (this.getNbEmpruntEnCours() == nbMaxPrets) {
			throw new BiblioException("Désolez vous ne pouvez pas emprunter car vous avez " + this.getNbEmpruntEnCours()
					+ " emprunts en cours !!!\n");
		} else {
			listeEmpruntEnCours.add(e);
			throw new BiblioException("Votre emprunt a été enregistré !!!\n");
		}
	}

	@Override
	public void addEmpruntEnCours(EmpruntEnCours e, Exemplaire ex) throws BiblioException {
		if (this.getNbRetard() > 0) {
			throw new BiblioException("Désolez vous ne pouvez pas emprunter car vous avez " + this.getNbRetard()
					+ " emprunts en retard !!!\n");
		}
		if (this.getNbEmpruntEnCours() == nbMaxPrets) {
			throw new BiblioException("Désolez vous ne pouvez pas emprunter car vous avez " + this.getNbEmpruntEnCours()
					+ " emprunts en cours !!!\n");
		} else {
			listeEmpruntEnCours.add(e);
			e.setExemplaire(ex);
			ex.setStatus(EnumStatusExemplaire.PRETE);
			throw new BiblioException("Votre emprunt a été enregistré. \nVous avez " + this.getNbEmpruntEnCours()
					+ "emprunt en cours dont " + this.getNbRetard() + " livre(s) en retard");
		}
	}

	@Override
	public void retourEmprunt(EmpruntEnCours e) {
		e.getExemplaire().setStatus(EnumStatusExemplaire.DISPONIBLE);
		listeEmpruntArchive.add(new EmpruntArchive(new Date(System.currentTimeMillis()), e.getDateEmprunt()));
		this.listeEmpruntEnCours.remove(e);
	}

	public static void main(String[] args) {
		
		Adherent adh = new Adherent();
		System.out.println(adh.getNbMaxPrets());
		System.out.println(adh.getDureeMaxPrets());
	
	}

}
