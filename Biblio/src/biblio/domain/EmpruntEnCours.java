package biblio.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpruntEnCours {
	private Date dateEmprunt;
	private Exemplaire exemplaire;
	private Utilisateur utilisateur;
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	

	public EmpruntEnCours(Utilisateur user, Exemplaire exemplaire ,Date dateEmprunt ) {
		
		this.utilisateur=user;
		this.exemplaire = exemplaire;
		this.dateEmprunt = dateEmprunt;
		
	}
	
public EmpruntEnCours(Date dateEmprunt ) {
		
	this(null,null,dateEmprunt);
		
	}
	

//	@Override
//	public String toString() {
//		return "EmpruntEnCours [dateEmprunt=" + dateEmprunt + "]";
//	}

	
	public void addExemplaire(Exemplaire e) {
		this.setExemplaire(e);
		
	}
	
	
	
	
	@Override
	public String toString() {
		return "EmpruntEnCours [dateEmprunt=" + dateEmprunt + ", exemplaire=" + exemplaire + ", utilisateur="
				+ utilisateur + "]";
	}

	public static void main(String[] args) {

	
		

	
	}

}
