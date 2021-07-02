package qa.ui.swing;

import java.awt.BorderLayout;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import qa.ui.controller.Controller;

public class JMainFrame extends JFrame
// NB: non è presente "implements ActionListener" perché i listener sono realizzati come lambda expression, dunque come ascoltatori esterni
// Si può naturalmente realizzarli in modo classico
{

	private static final long serialVersionUID = 1L;

	private Controller controller;
	private JPanel mainPanel;
	private JComboBox<String> combo;
	private JTextArea areaTabella, areaDettaglio;
	private JTextField text1, text2;
	private Histogram graph;
	private JButton button;
	
	public JMainFrame(Controller controller) {
		this.controller = controller;
		initGUI();
		setSize(390, 420);
	}

	private void initGUI() {	
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		setTitle("Quality assurance");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//
		JLabel label = new JLabel("Selezionare prodotto per i dettagli");
		mainPanel.add(label, BorderLayout.NORTH);
		//
		combo = new JComboBox<>(controller.getDescrizioni().toArray(new String[1])); 
		combo.setSelectedIndex(0);
		mainPanel.add(combo, BorderLayout.NORTH);
		combo.addActionListener(e-> this.aggiornaDettaglio());
		//
		areaDettaglio = new JTextArea(6,30); areaDettaglio.setEditable(false);
		mainPanel.add(areaDettaglio, BorderLayout.CENTER);	
		//
		button = new JButton("Nuova misura");
		text1 = new JTextField(10); text1.setEditable(false);
		text2 = new JTextField(5);  text2.setEditable(false);
		mainPanel.add(button,BorderLayout.CENTER);	
		mainPanel.add(text1,BorderLayout.CENTER);	
		mainPanel.add(text2,BorderLayout.CENTER);
		button.addActionListener(e->this.nuovaMisura());
		text2.addActionListener(e->this.aggiungiMisuraInLista());
		//
		areaTabella = new JTextArea(2,30); areaTabella.setEditable(false);
		areaTabella.setText(controller.getChecker().printTabellaPercentuali());		
		JLabel label2 = new JLabel("Situazione complessiva");
		mainPanel.add(label2, BorderLayout.NORTH);
		mainPanel.add(areaTabella, BorderLayout.CENTER);
		//
		JLabel label3 = new JLabel("Istogramma");
		mainPanel.add(label3, BorderLayout.SOUTH);
		graph = new Histogram();
		graph.showHistogram(controller.getTabellaPercentuali());
		mainPanel.add(graph,BorderLayout.SOUTH);
	}

	private void nuovaMisura() {
		text1.setText((String)combo.getSelectedItem()); text2.setEditable(true); button.setEnabled(false);
	}
	
	private void aggiungiMisuraInLista() {
		controller.addMisura(text1.getText(), Double.parseDouble(text2.getText())); 
		text2.setEditable(false);
		text2.setText(""); 
		text1.setText("");
		graph.showHistogram(controller.getTabellaPercentuali());
		areaTabella.setText(controller.getChecker().printTabellaPercentuali());
		aggiornaDettaglio();
		button.setEnabled(true);
	}

	private void aggiornaDettaglio(){
		String desc = (String) combo.getSelectedItem();
		StringBuilder sb = new StringBuilder();
		sb.append("Dettaglio " + desc + System.lineSeparator());
		sb.append("peso atteso: " + controller.getMisure(desc).get(0).getExpected() + System.lineSeparator());
		sb.append("# misure: " + controller.getMisure(desc).size() + System.lineSeparator());
		sb.append("# prodotti entro il range: " + controller.getListaProdottiEntroRange(desc).size() + System.lineSeparator());
		sb.append("% prodotti entro il range: " + NumberFormat.getPercentInstance().format(controller.getPercentualeProdottiEntroRange(desc)) + System.lineSeparator());
		areaDettaglio.setText(sb.toString());
	}
	
}
