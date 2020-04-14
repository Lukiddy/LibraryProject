package biblio.domain;

import java.util.Date;

public abstract class Emprunt {

	private Date dateEmprunt;

	public Emprunt(Date dateEmprunt) {
		super();
		this.dateEmprunt = dateEmprunt;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	
}
