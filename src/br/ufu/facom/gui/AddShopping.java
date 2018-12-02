package br.ufu.facom.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufu.facom.connection.ConnectionDB;

public class AddShopping {
	private final JButton btnContinuar = new JButton("Continuar");
	private final JButton btnCancelar = new JButton("Cancelar");
	private final JComboBox<Object> comboBox;
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNome = new JLabel("Nome:");
	private final JLabel lblEndereo = new JLabel("Endereço:");
	private final JLabel lblTelefone = new JLabel("Telefone:");
	private final JTextField txtNome = new JTextField();
	private final JTextField txtEndereco = new JTextField();
	private final JTextField txtTelefone = new JTextField();
	private final JDialog dialog = new JDialog();
	public AddShopping(JComboBox<Object> comboBox) {
		this.comboBox = comboBox;
		
		txtNome.setColumns(10);
		txtEndereco.setColumns(10);
		txtTelefone.setColumns(10);		
		
		dialog.setTitle("Adicionar Shopping");
		dialog.setVisible(true);
		dialog.setSize(320, 180);
		dialog.setResizable(false);
		dialog.setAutoRequestFocus(false);
		dialog.getContentPane().setLayout(null);
		dialog.setLocationRelativeTo(null);
		dialog.getContentPane().add(panel_1);
		
		panel_1.setLayout(null);
		
		txtNome.setBounds(92, 10, 192, 19);
		txtEndereco.setBounds(92, 35, 192, 19);
		txtTelefone.setBounds(92, 62, 192, 19);
		panel_1.setBounds(12, 12, 296, 125);
		lblNome.setBounds(12, 12, 66, 15);
		lblEndereo.setBounds(12, 37, 77, 15);
		lblTelefone.setBounds(12, 64, 66, 15);
		btnCancelar.setBounds(12, 91, 114, 25);
		btnContinuar.setBounds(170, 91, 114, 25);
		
		txtNome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addEvent();
			}
		});
		
		txtEndereco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addEvent();
			}
		});
		
		txtTelefone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addEvent();
			}
		});
		
		btnContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addEvent();
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		
		panel_1.add(lblNome);
		panel_1.add(txtNome);
		panel_1.add(lblEndereo);
		panel_1.add(txtEndereco);
		panel_1.add(lblTelefone);
		panel_1.add(txtTelefone);
		panel_1.add(btnCancelar);
		panel_1.add(btnContinuar);
	}
	
	private void addEvent() {
		if(txtNome.getText().isEmpty())
			JOptionPane.showMessageDialog(null, "O shopping deve ter um nome", "ERRO", JOptionPane.ERROR_MESSAGE);
		else {
			if(((DefaultComboBoxModel<Object>) comboBox.getModel()).getIndexOf(txtNome.getText()) < 0) {
				  comboBox.addItem(txtNome.getText());
				  if(comboBox.isEnabled() == false)
					  comboBox.setEnabled(true);
				  ConnectionDB.insertShopping(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText());
				  dialog.dispose();
			} else {
				JOptionPane.showMessageDialog(null, txtNome.getText() + " já está cadastrado", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
