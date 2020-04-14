package biblio.domain;


import java.util.Date;

public class Employe extends Utilisateur {
	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;


	public String getCodeMatricule() {
		return codeMatricule;
	}

	public void setCodeMatricule(String codeMatricule) {
		this.codeMatricule = codeMatricule;
	}

	public EnumCategorieEmploye getCategorieEmploye() {
		return categorieEmploye;
	}

	public void setCategorieEmploye(EnumCategorieEmploye categorieEmploye) {
		this.categorieEmploye = categorieEmploye;
	}
	
	
	public Employe(String nom, String prenom, Date dateNaissance, String sexe, int idUtilisateur, String pwd, String pseudonyme, EnumCategorieUtilisateur categorieUtilisateur) {
		super(nom, prenom,dateNaissance, sexe, idUtilisateur, pwd, pseudonyme, categorieUtilisateur);
		
	}


	public Employe(String nom, String prenom, Date dateNaissance, String sexe, int idUtilisateur, String pwd, String pseudonyme, EnumCategorieUtilisateur categorieUtilisateur, EnumCategorieEmploye categorieEmploye,
			String codeMatricule) {
		super(nom, prenom,dateNaissance, sexe, idUtilisateur, pwd, pseudonyme, categorieUtilisateur);
		this.categorieEmploye = categorieEmploye;
		this.codeMatricule = codeMatricule;
	}
	

//	public Employe(String idUtilisateurPwd, String pwd, String pseudonyme, ArrayList<EmpruntEnCours> listeEmprunt,
//			ArrayList<EmpruntArchive> listeEmpruntArchive, String codeMatricule,
//			EnumCategorieEmploye categorieEmploye) {
//		super(idUtilisateurPwd, pwd, pseudonyme, listeEmprunt);
//		this.codeMatricule = codeMatricule;
//		this.categorieEmploye = categorieEmploye;
//	}
//
//	public Employe(String codeMatricule, EnumCategorieEmploye categorieEmploye) {
//		super();
//		this.codeMatricule = codeMatricule;
//		this.categorieEmploye = categorieEmploye;
//	}
//
//	public Employe() {
//		super();
//	}

	
	@Override
	public String toString() {
		return super.toString()+"[codeMatricule=" + codeMatricule + ", categorieEmploye=" + categorieEmploye + "]";
	}

	@Override
	public void addEmpruntEnCours(EmpruntEnCours e) throws BiblioException {
		listeEmpruntEnCours.add(e);
		throw new BiblioException("Votre emprunt a été enregistré. \nVous avez " + this.getNbEmpruntEnCours()
				+ "emprunt en cours dont ");
	}

	

	@Override
	public void addEmpruntEnCours(EmpruntEnCours e, Exemplaire ex) throws BiblioException {
		listeEmpruntEnCours.add(e);
		e.setExemplaire(ex);
		ex.setStatus(EnumStatusExemplaire.PRETE);
		throw new BiblioException("Votre emprunt a été enregistré. \nVous avez " + this.getNbEmpruntEnCours()
				+ "emprunt en cours dont ");
	}

	@Override
	public void retourEmprunt(EmpruntEnCours e) {
		e.getExemplaire().setStatus(EnumStatusExemplaire.DISPONIBLE);
		listeEmpruntArchive.add(new EmpruntArchive(new Date(System.currentTimeMillis()), e.getDateEmprunt()));
		this.listeEmpruntEnCours.remove(e);
	}

//	@Override
//	public boolean isConditionPretAcceptees() {
//		return true;
//
//	}
	
	

	

	public static void main(String[] args) {
	
	}

}
