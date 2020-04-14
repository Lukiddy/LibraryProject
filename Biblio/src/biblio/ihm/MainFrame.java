package biblio.ihm;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import biblio.ctrl.EmprunterCtrl;
import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDAO;
import biblio.dao.ExemplaireDAO;

import biblio.domain.Exemplaire;

public class MainFrame extends JFrame {
	
	private TextPanel textPanel;  
	private Toolbar toolbar;
    private FormPanel formPanel;


public MainFrame() {
	 super("BIBLIOTHEQUE");
	 
	 setLayout( new BorderLayout());
	 toolbar = new Toolbar();
	 textPanel = new TextPanel();
	 formPanel = new FormPanel();
	 
	 setJMenuBar(createMenuBar());  //afficher menuBar
	 
	 toolbar.setStringListener(new StringListener() {

		 public void textEmitted(String text) {
				
				textPanel.appendText(text);
		
		}
		 
	 });

	 add(formPanel,BorderLayout.WEST);
	 add(toolbar, BorderLayout.NORTH);
	 add(textPanel,BorderLayout.CENTER);

	
		setSize (800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	
   }

private JMenuBar createMenuBar() {
	

	JMenuBar menuBar = new JMenuBar();
	JMenu windowMenu = new JMenu("Fenêtre");	

	
	JMenu fileMenu = new JMenu("Fichier");   
	JMenuItem  exportDataItem = new JMenuItem("Export Data...");
	JMenuItem  importDataItem = new JMenuItem("Import Data...");
	JMenuItem  exitItem = new JMenuItem("Quitter");
	
	menuBar.add(fileMenu);
	fileMenu.add(exportDataItem);
	fileMenu.add(importDataItem);
	fileMenu.addSeparator();
	fileMenu.add(exitItem);
	
	
	
	
	
	JMenu findKey    = new JMenu("Chercher");	
	JMenuItem  findByIsbn = new JMenuItem("ISBN");
	JMenuItem  findByTitre= new JMenuItem("Titre");
	JMenuItem  findByAuteur = new JMenuItem("Auteur");
	JMenuItem  findByEditeur= new JMenuItem("Editeur");
	JMenuItem  findByAnneParution= new JMenuItem("Année de parution");
	menuBar.add(findKey);
	findKey.add(findByIsbn);
	findKey.add(findByTitre);
	findKey.add(findByAuteur);
	findKey.add(findByEditeur);
	findKey.add(findByAnneParution);
	
	
	JMenu filterByKey    = new JMenu("Trier");	
	
	JMenu Livre    = new JMenu("Livre");
	JRadioButtonMenuItem  filterByPrete = new JRadioButtonMenuItem("Prete");
	JRadioButtonMenuItem  filterByDiponible = new JRadioButtonMenuItem("Disponible");
	JRadioButtonMenuItem  filterBySupprime = new JRadioButtonMenuItem("Supprimé");
	menuBar.add(filterByKey );
	filterByKey .add(Livre);
	Livre.add(filterByPrete);
	Livre.add(filterByDiponible);
	Livre.add(filterBySupprime);
	Livre.addSeparator();
	
	JMenu Emprunt1 = new JMenu("Emprunt");
	JRadioButtonMenuItem empruntEncours = new JRadioButtonMenuItem("EnCours");
	JRadioButtonMenuItem retard= new JRadioButtonMenuItem("Retard");
	JRadioButtonMenuItem  archive= new JRadioButtonMenuItem("Archivé");
	filterByKey .add(Emprunt1);
	Emprunt1.add(empruntEncours);
	Emprunt1.add(retard);
	Emprunt1.add(archive);




		
	
	
	
empruntEncours.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent ev) {
		JTextArea	textArea = new JTextArea();
		JRadioButtonMenuItem empruntEncours = (JRadioButtonMenuItem)ev.getSource();
	
		for(Exemplaire e :EmprunterCtrl.afficherListeExemplaire())
			{
				textPanel.appendText(e.getIdExemplaire()+" "+e.getIsbn()+"\n");
			}	
		}	
	});







findByIsbn.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent ev) {
	JTextArea	textArea = new JTextArea();
	JMenuItem findByIsbn = (JMenuItem)ev.getSource();
	
	for(Exemplaire e :EmprunterCtrl.afficherListeExemplaire())
		{
			textPanel.appendText(e.getIdExemplaire()+" "+e.getStatus()+"()\n");
		}	
	}	
});


	
	
	
	menuBar.add(windowMenu);
	JMenu showMenu = new JMenu("Show");
	JCheckBoxMenuItem showformItem = new JCheckBoxMenuItem("Person Form");
	showformItem.setSelected(true);
	showMenu.add(showformItem);
	windowMenu.add(showMenu);	
	
	
	

	
	showformItem.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent ev) {
		
		JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
		formPanel.setVisible(menuItem.isSelected());
			
			
		}
		
	});
	
  //  fileMenu.setMnemonic(KeyEvent.VK_F);
   // exitItem.setMnemonic(KeyEvent.VK_X);
    
    exitItem.addActionListener(new ActionListener() {
    	
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
    	
    });
	
	return menuBar;
	
	
}

	
}


