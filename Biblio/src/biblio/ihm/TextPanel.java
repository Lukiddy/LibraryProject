package biblio.ihm;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel{
	
	private static JTextArea textArea;
	
	public TextPanel() {
		textArea = new JTextArea();
		setLayout (new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);	
	}

	public static void appendText(String text) {
		textArea.append(text);
	}


}
