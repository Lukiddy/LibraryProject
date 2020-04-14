package biblio.domain;

import java.util.Date;

public class EmpruntEnCoursDB extends EmpruntEnCours {
	private int idUtilisateur;
	private int idExemplaire; 
	
	

	public int getIdUtilisateur() {
		return idUtilisateur;
	}



	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}



	public int getIdExemplaire() {
		return idExemplaire;
	}



	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}
	
	
	public EmpruntEnCoursDB( Utilisateur user, Exemplaire e, Date dateEmprunt, int idUtilisateur, int idExemplaire) {
		super(user, e, dateEmprunt);
		this.idUtilisateur = idUtilisateur;
		this.idExemplaire = idExemplaire;
	}



	public EmpruntEnCoursDB(Date dateEmprunt, int idUtilisateur, int idExemplaire) {
		super(dateEmprunt);
		this.idUtilisateur = idUtilisateur;
		this.idExemplaire = idExemplaire;
	}



	@Override
	public String toString() {
		return super.toString()+"EmpruntEnCoursDB [idUtilisateur=" + idUtilisateur + ", idExemplaire=" + idExemplaire + "]";
	}



	public static void main(String[] args) {
		

	}

}
