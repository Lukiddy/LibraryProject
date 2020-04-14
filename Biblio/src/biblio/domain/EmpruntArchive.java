package biblio.domain;

import java.util.Date;

public class EmpruntArchive extends Emprunt{
	private Date dateRestitutionEff;
	private Date dateEmprunt;
	private Utilisateur user;
	private Exemplaire examplary;
	
	public EmpruntArchive(Date dateEmprunt, Date dateRestitutionEff, Utilisateur user, Exemplaire examplary) {
		super(dateEmprunt);
		this.dateRestitutionEff = dateRestitutionEff;
		this.user = user;
		this.examplary = examplary;
		// this.idEmpruntArchive = idEmpruntArchive;
	}

	public Date getDateRestitutionEff() {
		return dateRestitutionEff;
	}

	public void setDateRestitutionEff(Date dateRestitutionEff) {
		this.dateRestitutionEff = dateRestitutionEff;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Exemplaire getExamplary() {
		return examplary;
	}

	public void setExamplary(Exemplaire examplary) {
		this.examplary = examplary;
	}

	@Override
	public String toString() {
		return "EmpruntArchive [dateRestitutionEff=" + dateRestitutionEff + ", dateEmprunt=" + dateEmprunt + ", user="
				+ user + ", examplary=" + examplary + "]";
	}

	
	



}
