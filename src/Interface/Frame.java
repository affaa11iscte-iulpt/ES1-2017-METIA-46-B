package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import components.Reader;

public abstract class Frame extends JFrame {

	protected DefaultTableModel tableModel;

	public Frame(){
		setSize(650,650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addComponentes();
		setVisible(true);
	}

	private void addComponentes() {
		setLayout(new BorderLayout());
		add(filePanel(), BorderLayout.NORTH);
		add(southPanel(), BorderLayout.SOUTH);

	}


	private JPanel filePanel(){
		JPanel filePanel = new JPanel();
		filePanel.setLayout(new GridLayout(3,2));

		JPanel subPanel1= new JPanel();
		JLabel rules = new JLabel("rules.cf");
		JButton carregar = new JButton("ok");
		JTextField text1 = new JTextField("files\\rules.cf");
		subPanel1.add(rules);
		subPanel1.add(text1);
		subPanel1.add(carregar);
		carregar.addActionListener(new ActionListener(
				) {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reader.readRules(text1.getText());
				addRules();
			}
		});

		JPanel subPanel2= new JPanel();
		JLabel spam = new JLabel("spam.log");
		JTextField text2 = new JTextField("files\\spam.log");
		JButton carregar2 = new JButton("ok");
		subPanel2.add(spam);
		subPanel2.add(text2);
		subPanel2.add(carregar2);
		carregar2.addActionListener(new ActionListener(
				) {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reader.readEmails(text2.getText(), true);

			}
		});



		JPanel subPanel3= new JPanel();
		JLabel ham = new JLabel("ham.log");
		JButton carregar3 = new JButton("ok");
		JTextField text3 = new JTextField("files\\ham.log");
		subPanel3.add(ham);
		subPanel3.add(text3);
		subPanel3.add(carregar3);
		carregar3.addActionListener(new ActionListener(
				) {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reader.readEmails(text3.getText(), false);

			}
		});



		filePanel.add(subPanel1);
		filePanel.add(subPanel2);
		filePanel.add(subPanel3);

		return filePanel;

	}




	public JPanel southPanel(){
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new GridLayout(5,1));


		JPanel subPanel2 = new JPanel();
		JLabel fp = new JLabel(" Falsos Positivos Gerados:  ");

		subPanel2.add(fp);

		JPanel subPanel3 = new JPanel();
		JLabel fn = new JLabel(" Falsos Negativos Gerados:  ");

		subPanel3.add(fn);


		JPanel subPanel4 = new JPanel();
		JButton guardar = new JButton ("Guardar Configuração");
		subPanel4.add(guardar);

		JPanel subPanel5 = new JPanel();
		JButton reset = new JButton ("Reset Configuração");
		subPanel5.add(reset);

		panelSouth.add(subPanel2);
		panelSouth.add(subPanel3);
		panelSouth.add(subPanel4);
		panelSouth.add(subPanel5);


		return panelSouth;
	}
	/**
	 * Obtenção do HashMap de regras (com pesos associados) lidas na classe Reader e listagem das mesmas na interface manual
	 */
	public void addRules() {
		Map<String,String> rules = Reader.readRules("files/rules.cf");

		for(Map.Entry<String, String> entry: rules.entrySet()) {
			tableModel.addRow(new String[] {entry.getKey(), entry.getValue()});
		}
	}


}
