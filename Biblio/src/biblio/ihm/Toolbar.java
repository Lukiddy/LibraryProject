package biblio.ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar  extends JPanel implements ActionListener{
	private JButton helloButton;
	private JButton goodbyeButton;
	private StringListener textListener;
	
	
	public Toolbar () {
		
		setBorder(BorderFactory.createEtchedBorder());  //create title border
	
	}
	
	public void setStringListener (StringListener listener) {
		this.textListener= listener;
	}
	



@Override
public void actionPerformed(ActionEvent e) {

JButton clicked = (JButton) e.getSource();
if(clicked == helloButton) {
	if(textListener !=null) {
		textListener.textEmitted("hello\n");
	}
    // textPanel.appendText("Hello\n");
   }
	else if(clicked == goodbyeButton) {
		if (textListener !=null) {
				textListener.textEmitted("GoodBye\n");
		
	}

	
}}

}
