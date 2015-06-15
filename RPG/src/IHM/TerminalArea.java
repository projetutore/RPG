package IHM;

import java.io.IOException;
import java.io.OutputStream;
	 
import javax.swing.JTextArea;
	 
public class TerminalArea extends OutputStream {
	private JTextArea textArea;
	      
	public TerminalArea(JTextArea textArea) {
		this.textArea = textArea;
		}
	      
	public void write(int b) throws IOException {
		// redirects data to the text area
		textArea.append(String.valueOf((char)b));
		// scrolls the text area to the end of data
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

}