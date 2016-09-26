/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import controller.GrupoCtrl;

public class GrupoView extends JFrame {

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
					GrupoView frame = new GrupoView();
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
	public GrupoView() {
		
		setBounds(100, 100, 560, 400);
		setTitle("Grupos do Campeonato");
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
		
		JScrollPane spGrupoA = new JScrollPane();
		spGrupoA.setBounds(22, 48, 206, 105);
		spGrupoA.setViewportView(tbGrupoA);
		contentPane.add(spGrupoA);
		
		JScrollPane spGrupoB = new JScrollPane();
		spGrupoB.setBounds(330, 48, 206, 105);
		spGrupoB.setViewportView(tbGrupoB);
		contentPane.add(spGrupoB);
		
		JScrollPane spGrupoC = new JScrollPane();
		spGrupoC.setBounds(22, 201, 206, 105);
		spGrupoC.setViewportView(tbGrupoC);
		contentPane.add(spGrupoC);
		
		JScrollPane spGrupoD = new JScrollPane();
		spGrupoD.setBounds(330, 201, 206, 105);
		spGrupoD.setViewportView(tbGrupoD);
		contentPane.add(spGrupoD);
		
		tbGrupoA = new JTable();
		tbGrupoA.setBorder(null);
		
		tbGrupoB = new JTable();
		tbGrupoB.setBorder(null);
		
		tbGrupoC = new JTable();
		tbGrupoC.setBorder(null);
		
		tbGrupoD = new JTable();
		tbGrupoD.setBorder(null);
		
		GrupoCtrl g = new GrupoCtrl(tbGrupoA, tbGrupoB, tbGrupoC, tbGrupoD);
		btnGerarGrupos.addActionListener(g.preencherTabela);
	}
}
