package br.ufu.facom.connection;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SQLJava {
	private final JFrame frmControlePracas = new JFrame();
	private final JPanel panel = new JPanel();
	private final JPanel panelPracas = new JPanel();
	private final JButton btnAddPraca = new JButton("Adicionar");
	private final JButton btnRemover = new JButton("Remover");
	private final JComboBox<Object> comboBox = new JComboBox<Object>();
	
	public static void main(String[] args) {
		new SQLJava();
	}
	
	public SQLJava() {
		start();
	}
	
	public void start() {
		frmControlePracas.setIconImage(Toolkit.getDefaultToolkit().getImage(SQLJava.class.getResource("/br/ufu/facom/images/ufu_logo.png")));
		frmControlePracas.setResizable(false);
		frmControlePracas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmControlePracas.getContentPane().setLayout(null);
		frmControlePracas.setVisible(true);
		frmControlePracas.setSize(800,600);
		frmControlePracas.setTitle("Controle de praças de alimentação");
		frmControlePracas.setLocationRelativeTo(null);
		frmControlePracas.getContentPane().add(panel);

		panel.setLayout(null);
		
		panelPracas.setLayout(null);
		panelPracas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Shopping", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		panel.setBounds(12, 0, 766, 558);
		panelPracas.setBounds(12, 12, 260, 91);
		btnAddPraca.setBounds(12, 54, 103, 25);
		btnRemover.setBounds(127, 54, 114, 25);
		comboBox.setBounds(12, 18, 229, 24);
		
		comboBox.setEnabled(false);
		
		btnAddPraca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddShopping(comboBox);
			}
		});
		
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getItemCount() <= 0) {
					JOptionPane.showMessageDialog(null, "Ao menos um shopping deve estar cadastrado", "ERRO", JOptionPane.ERROR_MESSAGE);
				} else if(JOptionPane.showConfirmDialog(null, "Deseja realmente deletar " + comboBox.getSelectedItem().toString() + "?", "Confirmar", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					comboBox.removeItem(comboBox.getSelectedItem());
					if(comboBox.getItemCount() <= 0) 
						comboBox.setEnabled(false);
				}
			}
		});
		
		panel.add(panelPracas);
		panelPracas.add(btnRemover);
		panelPracas.add(comboBox);
		panelPracas.add(btnAddPraca);

	}
}
