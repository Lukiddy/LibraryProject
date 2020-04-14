package biblio.ihm;

import java.awt.event.ActionListener;
import java.util.EventObject;

public class FormEvent extends EventObject {
	private String IdUtilisateur;
	private char[] motDePasse;
    private String empCat ;
    private boolean emprunt;
    private String group;
    private String motCle;
    private String pseudonyme;
    
	public String getEmpCat() {
		return empCat;
	}

	public void setEmpCat(String empCat) {
		this.empCat = empCat;
	}

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public String getIdUtilisateur() {
		return IdUtilisateur;
	}

	public void setIdUtilisateur(String IdUtilisateur) {
		this.IdUtilisateur = IdUtilisateur;
	}
    public void setPseudonyme (String pseudonyme) {
    	this.pseudonyme=pseudonyme;
    }
    public String pseudonyme (){
    	return pseudonyme;
    }
    
	public char[] getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(char[] MotDePasse) {
		this.motDePasse = MotDePasse;

	}

	
	public FormEvent( Object source,  boolean emprunt,String group) {
		super(source);
		this.emprunt= emprunt;
		this.group = group;
	}
	



	public FormEvent(Object source, String pseudonyme, char [] motDePasse ){
		super(source);
		this.pseudonyme= pseudonyme;
		this.motDePasse= motDePasse;
		
	}

	public FormEvent(Object source, String empCat, String motCle) {
	super(source);
	this.empCat=empCat;
	this.motCle=motCle;
		
	}

	public boolean isEmprunt() {
		return emprunt;
	}

	
	public String getEmploymentCategory() {
		return empCat;
	}

	public String getGroup() {
		return group;
	}
	
	

}
