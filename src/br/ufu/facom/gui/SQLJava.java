package br.ufu.facom.gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufu.facom.connection.ConnectionDB;

public class SQLJava {
	private final JFrame frmControlePracas = new JFrame();
	private final JPanel panel = new JPanel();
	private final JPanel panelPracas = new JPanel();
	private final JButton btnAddPraca = new JButton("Adicionar");
	private final JButton btnRemover = new JButton("Remover");
	private final JComboBox<Object> comboBox = new JComboBox<Object>();
	private final JPanel panelInfo = new JPanel();
	private final JLabel lblId = new JLabel("ID:");
	private final JLabel lblNewLabel = new JLabel("Área:");
	private final JLabel lblArea = new JLabel("Não definido");
	private final JLabel lblQuantidadeCadeiras = new JLabel("Cadeiras:");
	private final JButton btnRestaurantes = new JButton("Restaurantes");
	private final JLabel label = new JLabel("0");
	private final JLabel lblMesas = new JLabel("Mesas:");
	private final JButton btnEditar = new JButton("Editar");
	private final JButton btnAdicionar = new JButton("Adicionar");
	
	public static void main (String[] args) {
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
		panelInfo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Pra\u00E7as", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		panel.setBounds(12, 0, 766, 558);
		panelPracas.setBounds(12, 12, 260, 124);
		btnAddPraca.setBounds(12, 54, 103, 25);
		btnRemover.setBounds(127, 54, 114, 25);
		comboBox.setBounds(12, 18, 229, 24);
		panelInfo.setBounds(284, 12, 482, 534);
		lblId.setBounds(15, 244, 31, 15);
		lblNewLabel.setBounds(52, 15, 43, 15);
		lblArea.setBounds(103, 15, 92, 15);
		btnRestaurantes.setBounds(33, 82, 136, 25);
		lblQuantidadeCadeiras.setBounds(25, 35, 66, 15);
		label.setBounds(103, 35, 66, 15);
		lblMesas.setBounds(43, 55, 66, 15);
		btnEditar.setBounds(65, 87, 114, 25);
		btnAdicionar.setBounds(43, 271, 136, 25);
		
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
					ConnectionDB.removeShopping(comboBox.getSelectedItem().toString());
					comboBox.removeItem(comboBox.getSelectedItem());
					if(comboBox.getItemCount() <= 0) 
						comboBox.setEnabled(false);
				}
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getItemCount() <= 0) {
					JOptionPane.showMessageDialog(null, "Ao menos uma praça deve estar cadastrado", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panelInfo.setLayout(null);
		panel.add(panelPracas);
		panel.add(panelInfo);
		panelInfo.add(lblNewLabel);
		panelInfo.add(lblArea);
		panelInfo.add(btnRestaurantes);
		panelInfo.add(lblQuantidadeCadeiras);
		panelInfo.add(label);
		panelInfo.add(lblMesas);
		panelInfo.add(btnAdicionar);
		panelInfo.add(lblId);
		panelPracas.add(btnRemover);
		panelPracas.add(comboBox);
		panelPracas.add(btnAddPraca);
		panelPracas.add(btnEditar);

		loadComboboxItems(comboBox);
		
		
	}
	
	private void loadComboboxItems(JComboBox<Object> cb) {
		ArrayList<String> shoppings = ConnectionDB.getShoppings();
		for(String e : shoppings) {
			cb.addItem(e);
		}
		cb.setEnabled(true);
	}
}
