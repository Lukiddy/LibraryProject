package biblio.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exemplaire {
	private String idExemplaire;
	private Date dateAchat;
	private EnumStatusExemplaire status;
	private String isbn;
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private EmpruntEnCours empruntEnCours;

	public String getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(String idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public EnumStatusExemplaire getStatus() {
		return status;
	}

	public void setStatus(EnumStatusExemplaire status) {
		this.status = status;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public EmpruntEnCours getEmpruntEnCours() {
		return empruntEnCours;
	}

	public void setEmpruntEnCours(EmpruntEnCours empruntEnCours) {
		this.empruntEnCours = empruntEnCours;
	}

	public Exemplaire(String idExemplaire, Date dateAchat, EnumStatusExemplaire status, String isbn) {
		super();
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.status = status;
		this.isbn = isbn;
	}

	public Exemplaire() {
		super();
	}

	



	@Override
	public String toString() {
		return "Exemplaire [idExemplaire=" + idExemplaire + ", dateAchat=" + dateAchat + ", status=" + status
				+ ", isbn=" + isbn + "]";
	}


}
