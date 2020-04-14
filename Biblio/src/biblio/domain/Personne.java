package biblio.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Personne {
	private String name;
	private String prenom;
	private Date dateNaissance;
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private String sexe;
	
	
	

	public Personne() {
		super();
	}




	public Personne(String name, String prenom, Date dateNaissance, String sexe) {
		super();
		this.name = name;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return "Personne  [name=" + name + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe
				+ "]";
	}



	public static void main(String[] args) throws ParseException {
		Personne p1 = new Personne();
		Personne p2 = new Personne("SUPER", "popo",sdf.parse("26/05/1980"),"masc");
System.out.println(p1.toString());
System.out.println(p2.toString());
	}




	public void addEmpruntEnCours(Exemplaire e) throws BiblioException {
		// TODO Auto-generated method stub
		
	}




	
}
