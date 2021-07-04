package edicecream.ui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import edicecream.controller.Controller;

public class IceCreamFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private FlavorPanel icePanel;
	private JComboBox<String> kindCombo;
	private JButton conferma, stampa;
	private JTextArea output;

	public IceCreamFrame(Controller controller) {
		this.controller = controller;
		initGui();
		bindData();
	}

	private void bindData() {
		Iterator<String> kinds = controller.getKindNames().iterator();
		while (kinds.hasNext()) {
			kindCombo.addItem(kinds.next());
		}
	}

	private void initGui() {
		setTitle("ED Creams & Dreams");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout(5, 5));
		setSize(800, 400);
		
		JPanel left = new JPanel();
		{
		    left.setLayout(new BoxLayout(left,BoxLayout.PAGE_AXIS));
		    left.setBackground(Color.WHITE);
		    left.add(Box.createVerticalGlue());
			left.add(new JLabel("Tipo:"));
			left.add(Box.createVerticalStrut(10));
			kindCombo = new JComboBox<String>();
			kindCombo.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	setFlavorMaxNumber(controller.getMaxFlavors((String)kindCombo.getSelectedItem()));
			    }
			});
			kindCombo.setMaximumSize(new Dimension(150,50));  
			left.add(kindCombo);
			left.add(Box.createVerticalStrut(10));
			
			icePanel = new FlavorPanel(controller.getFlavors());
			left.add(icePanel);
			left.add(Box.createVerticalStrut(10));

			JPanel buttonBox = new JPanel();
			{    
				buttonBox.setLayout(new BoxLayout(buttonBox,BoxLayout.LINE_AXIS));
				buttonBox.setBackground(Color.WHITE);

				conferma = new JButton("Conferma");
				buttonBox.add(conferma);
				conferma.addActionListener(this::myConfirmHandle);

				buttonBox.add(Box.createHorizontalStrut(20));

				stampa = new JButton("Stampa");
				buttonBox.add(stampa);
				stampa.addActionListener(event ->  controller.printReport());
			}
			left.add(buttonBox);
			left.add(Box.createVerticalGlue());
		}
		

		JPanel right = new JPanel();
		{
			right.setLayout(new BoxLayout(right,BoxLayout.PAGE_AXIS));
		    right.setBackground(Color.WHITE);
		    
			right.add(new JLabel("Situazione attuale: "));
			right.add(Box.createVerticalStrut(10));

			JScrollPane scrolPanel = new JScrollPane();
			{
				output = new JTextArea();
				output.setEditable(false);
				scrolPanel.setViewportView(output);
			}
			
			
			right.add(scrolPanel);
		}
		right.setPreferredSize(new Dimension(300, 300));

		
		getContentPane().add(left, BorderLayout.CENTER);	
		getContentPane().add(right, BorderLayout.EAST);
	}

	private void setFlavorMaxNumber(int flavorNumber) {
		icePanel.setMaxSelected(flavorNumber);
		
	}

	private void myConfirmHandle(ActionEvent e) 
	{
		boolean result = controller.addIceCream((String)kindCombo.getSelectedItem(), icePanel.getSelected());
		if (result)
		{
			JOptionPane.showMessageDialog(null, "Complimenti per la scelta del gelato");
			kindCombo.setSelectedItem(null);
			icePanel.reset();
			output.setText(controller.getIceCreamStatus());
			
		} else {
			JOptionPane.showMessageDialog(null, "Uno dei gusti da te selezionato non è disponibile");
		}
	}
}
