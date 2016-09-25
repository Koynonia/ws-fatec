package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JLabel;

public class Campeonato extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6628613480798698487L;
	private JPanel contentPane;
	private JTable tbGrupoA;
	private JTable tbGrupoB;
	private JTable tbGrupoC;
	private JTable tbGrupoD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Campeonato frame = new Campeonato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Campeonato() {
		
		setBounds(100, 100, 560, 400);
		setTitle("Geração de Grupos");
		setName("Grupos");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		JButton btnGerarGrupos = new JButton("Gerar Grupos");
		btnGerarGrupos.setBounds(218, 332, 126, 29);
		btnGerarGrupos.setVisible(true);
		contentPane.setLayout(null);
		contentPane.add(btnGerarGrupos);
		
		tbGrupoA = new JTable();
		tbGrupoA.setBounds(22, 48, 206, 105);
		contentPane.add(tbGrupoA);
		
		tbGrupoB = new JTable();
		tbGrupoB.setBounds(330, 48, 206, 105);
		contentPane.add(tbGrupoB);
		
		tbGrupoC = new JTable();
		tbGrupoC.setBounds(22, 201, 206, 105);
		contentPane.add(tbGrupoC);
		
		tbGrupoD = new JTable();
		tbGrupoD.setBounds(330, 201, 206, 105);
		contentPane.add(tbGrupoD);
		
		JLabel lblGrupoA = new JLabel("Grupo A");
		lblGrupoA.setBounds(92, 23, 61, 16);
		contentPane.add(lblGrupoA);
		
		JLabel lblGrupoB = new JLabel("Grupo B");
		lblGrupoB.setBounds(407, 23, 61, 16);
		contentPane.add(lblGrupoB);
		
		JLabel lblGrupoC = new JLabel("Grupo C");
		lblGrupoC.setBounds(92, 173, 61, 16);
		contentPane.add(lblGrupoC);
		
		JLabel lblGrupoD = new JLabel("Grupo D");
		lblGrupoD.setBounds(407, 173, 61, 16);
		contentPane.add(lblGrupoD);
	}
}
