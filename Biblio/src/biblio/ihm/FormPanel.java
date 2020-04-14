package biblio.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import biblio.ctrl.AuthentifierCtrl;
import biblio.ctrl.EmprunterCtrl;
import biblio.ctrl.RetourCtrl;
import biblio.domain.BiblioException;

public class FormPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel pseudonymeLabel;
	private JLabel motDePasseLabel;
	private JTextField pseudonymeField;
	private JPasswordField motDePasseField;
	private JButton okBtn;
	private JButton BtnConnection;
	private JButton BtnDeconnection;
	private FormListener formListener;
	private JComboBox empCombo;
	private JTextField idExemplairesTextField;
	private JTextField IdUtilisateurField;

	// private JCheckBox emprunterCheck;
	/// private JCheckBox retourCheck;

	private JRadioButton emprunterRadio;
	private JRadioButton retourRadio;
	private ButtonGroup empruntGroup;

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);

		pseudonymeLabel = new JLabel("Pseudonyme");
		motDePasseLabel = new JLabel("Mot de passe");
		pseudonymeField = new JTextField(10);
		motDePasseField = new JPasswordField(10);
		empCombo = new JComboBox();
		IdUtilisateurField = new JTextField(10);
		idExemplairesTextField = new JTextField(10);

		emprunterRadio = new JRadioButton("Emprunter");
		retourRadio = new JRadioButton("Retour");

		emprunterRadio.setActionCommand(" : Emprunt");
		empruntGroup = new ButtonGroup();

		emprunterRadio.setSelected(true);
		retourRadio.setActionCommand(" : Retour");

		// set up gender radio
		empruntGroup.add(emprunterRadio);
		empruntGroup.add(retourRadio);

		// set up comboBox

		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("ISBN");
		empModel.addElement("TITRE");
		empModel.addElement("AUTEUR");
		empModel.addElement("EDITEUR");
		empModel.addElement("IDEXEMPLAIRE");
		empCombo.setModel(empModel);

		empCombo.setSelectedIndex(0);
		empCombo.setEditable(true);

		okBtn = new JButton("OK");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String IdUtilisateur = IdUtilisateurField.getText();
				String chercherParCombox = (String) empCombo.getSelectedItem();
				String idExemplaire = idExemplairesTextField.getText();
				Action emprunter = emprunterRadio.getAction();
				Action retour = retourRadio.getAction();
				FormEvent ev = new FormEvent(this, chercherParCombox, idExemplaire);
				boolean EmprunterIsTicked = emprunterRadio.isSelected();
				boolean RetourIsTicked = retourRadio.isSelected();

				if (EmprunterIsTicked) {
					///////////////// saisie Utilisateur////////////////////////////////////:
//					try {
//						EmprunterCtrl.entrerUtilisateur(Integer.parseInt(IdUtilisateur));
//					} catch (NumberFormatException e1) {
//						e1.printStackTrace();
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
					//boolean verifCondition = false;
				
					EmprunterCtrl.chargerConditionsPret(); 
					try {
						if(EmprunterCtrl.verifUser(Integer.parseInt(IdUtilisateur))) {

							
							/////////////////////////// saisie idExemplaire ////////////////////////////

							if ((chercherParCombox.equals("IDEXEMPLAIRE")) && idExemplaire != null) {
								EmprunterCtrl.entrerExemplaire(idExemplaire);
								try {
									EmprunterCtrl.updateStatusExemplaireEmprunt(idExemplaire);
								} catch (BiblioException e1) {
									e1.printStackTrace();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								System.out.println("ok");

							}

						} else
							System.out.println("vous n'avez pas le droit d'emprunter");
					} catch (HeadlessException e1) {
						
						e1.printStackTrace();
					} catch (NumberFormatException e1) {
						
						e1.printStackTrace();
					} catch (BiblioException e1) {
						
						e1.printStackTrace();
					}
	} 
				//	else
//					System.out.println("error1");
				
					else if(RetourIsTicked) {
					try {
						RetourCtrl.retournerExemplaire(idExemplaire, Integer.parseInt(IdUtilisateur));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BiblioException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
					else System.out.println("prob");
			}
		});

		BtnConnection = new JButton("Connection");
		BtnDeconnection = new JButton("Deconnection");

		BtnConnection.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String pseudonyme = pseudonymeField.getText(); // le nom est celui qu'on entre dans le champs
				char[] motDePasse = motDePasseField.getPassword();

				try {
					AuthentifierCtrl.sauthentifier(pseudonyme, motDePasse);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				
			}

		});

		BtnDeconnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}

		});

		// setBorder(BorderFactory.createTitledBorder("Add Person")); // pour afficher
		// add person dans border
		Border innerBorder = BorderFactory.createTitledBorder(" ");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();

	}

	public void layoutComponents() {

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// /////// Utilisateur////////////
		gc.gridy = 0;

		gc.weightx = 2;
		gc.weighty = 0.5;
		gc.gridx = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(pseudonymeLabel, gc);

		gc.gridx = 1; // set textField
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(pseudonymeField, gc);

		// /////// mot de passe////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(motDePasseLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(motDePasseField, gc);
		//////////////// button connection et deconnection//////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(BtnConnection, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 20, 10);
		add(BtnDeconnection, gc);

		// /////// ////////////

		/*
		 * gc.gridy++; gc.weightx =1; gc.weighty =0.2;
		 * 
		 * gc.gridx = 0; gc.insets = new Insets(0,0,0,5); gc.anchor =
		 * GridBagConstraints.FIRST_LINE_END; add(new JLabel("Employé "), gc);
		 * 
		 * gc.gridx = 1; gc.anchor = GridBagConstraints.FIRST_LINE_START; gc.insets =
		 * new Insets(0,0,0,0); add(employeCheck,gc);
		 */
		// /////// next row////////////

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.8;

		gc.gridx = 0;
		gc.insets = new Insets(2, 0, 0, 20);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Chercher par "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);

		/////////////// chercher un livre par mot cles ///////////////////::

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.insets = new Insets(2, 0, 0, 20);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Id Exemplaire"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(idExemplairesTextField, gc);

		/////////////////// id Utilisateur//////////////////////////////////:

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.insets = new Insets(2, 0, 0, 20);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Id Utilisateur"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(IdUtilisateurField, gc);

		// /////// next row////////////

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(emprunterRadio, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(retourRadio, gc);

		// /////// next row////////////

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 2;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);

	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

}

class AgeCategory {
	private int id;
	private String text;

	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;

	}

	public String toString() {
		return text;
	}

	public int getId() {
		return id;
	}

}
