package GUI;

import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {

		// could only be necessary for multithreading
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new MyFrame();

			}
		});
	}
}