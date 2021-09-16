package thread.starvation;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * questa classe, semplicemente conta fino a 50
 */
public class Avanzamento extends Thread {
	private JLabel label;
	private String risorsaCondivisa;
	
	public Avanzamento(String risorsaCondivisa, String nomeBarra) {
		this.risorsaCondivisa = risorsaCondivisa;
		this.setName(nomeBarra);

		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public JLabel conta() {
		return label;
	}

	@Override
	public void run() {
		int contatore = 0;
		while (true) {
			synchronized (risorsaCondivisa) {
				if (contatore == 50) {
					contatore = 0;
				}

				label.setText(" - " + this.getName() + ": " + ++contatore+" - ");

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
