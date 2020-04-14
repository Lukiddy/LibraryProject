package biblio.domain;

import java.util.ArrayList;
import java.util.Date;

public abstract class Utilisateur extends Personne {

	private int idUtilisateur;
	private String pwd;
	private String pseudonyme;
	protected ArrayList<EmpruntEnCours> listeEmpruntEnCours = new ArrayList<>();
	protected ArrayList<EmpruntArchive> listeEmpruntArchive = new ArrayList<>();
	private EnumCategorieUtilisateur categorieUtilisateur;
	
	
	
	
	public Utilisateur(String nom, String prenom, Date dateNaissance, String sexe, int idUtilisateur, String pwd, String pseudonyme, EnumCategorieUtilisateur categorieUtilisateur) {
		super(nom, prenom, dateNaissance, sexe);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
		this.categorieUtilisateur = categorieUtilisateur;
	}

	

	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}



	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPseudonyme() {
		return pseudonyme;
	}

	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}

	public ArrayList<EmpruntEnCours> getListeEmprunt() {
		return listeEmpruntEnCours;
	}
	

	public void setListeEmprunt(ArrayList<EmpruntEnCours> listeEmpruntEnCours) {
		this.listeEmpruntEnCours = listeEmpruntEnCours;
	}

	public ArrayList<EmpruntArchive> getListeEmpruntArchive() {
		return listeEmpruntArchive;
	}

	public void setListeEmpruntArchive(ArrayList<EmpruntArchive> listeEmpruntArchive) {
		this.listeEmpruntArchive = listeEmpruntArchive;
	}
	
	
	public EnumCategorieUtilisateur getCategorieUtilisateur() {
		return categorieUtilisateur;
	}

	public void setCategorieUtilisateur(EnumCategorieUtilisateur categorieUtilisateur) {
		this.categorieUtilisateur = categorieUtilisateur;
	}

	public int getNbEmpruntEnCours() {
		return listeEmpruntEnCours.size();

	}

	public void retourEmprunt(EmpruntEnCours e) throws BiblioException {

	}

	public boolean isConditionPretAcceptees() throws BiblioException {
		return true;

	}

	public void addEmpruntEnCours(EmpruntEnCours e) throws BiblioException {
	}

	public void addEmpruntEnCours(EmpruntEnCours e, Exemplaire ex) throws BiblioException {

	}

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", pwd=" + pwd + ", pseudonyme=" + pseudonyme
				+ ", listeEmpruntEnCours=" + listeEmpruntEnCours + ", listeEmpruntArchive=" + listeEmpruntArchive
				+ ", categorieUtilisateur=" + categorieUtilisateur + "]\n";
	}

	

}



