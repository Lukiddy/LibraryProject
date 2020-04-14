package biblio.ctrl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDAO;
import biblio.dao.ExemplaireDAO;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.Utilisateur;

public class AuthentifierCtrl {
	private static Adherent unAdherent = null;
	//private static Employe unEmploye = null;
	private static Utilisateur user = null;
	private static Connection appConnect = ConnectionFactory.getConnectionSansAutocommit();
	private static UtilisateursDao userdao = new UtilisateursDao(appConnect);
	private static EmpruntEnCoursDAO empdao = new EmpruntEnCoursDAO(appConnect);
	public static ExemplaireDAO exdao = new ExemplaireDAO(appConnect);

public static boolean sauthentifier(String pseudonyme, char[] motDePasse) throws SQLException {
		
		String mdp = userdao.authentifier(pseudonyme);
		
		if (mdp.equals(new String(motDePasse))) {
			JOptionPane.showMessageDialog(null, "Connexion effectuée");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "invallid utilisateur", "erreur de connection",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
}
