/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
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
		
		setBounds(100, 100, 826, 432);
		setTitle("Grupos do Campeonato");
		setName("Grupos");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGrupoA = new JLabel("Grupo A");
		lblGrupoA.setBounds(55, 36, 51, 16);
		contentPane.add(lblGrupoA);
		
		JLabel lblGrupoB = new JLabel("Grupo B");
		lblGrupoB.setBounds(444, 36, 49, 16);
		contentPane.add(lblGrupoB);
		
		JLabel lblGrupoC = new JLabel("Grupo C");
		lblGrupoC.setBounds(55, 190, 51, 16);
		contentPane.add(lblGrupoC);
		
		JLabel lblGrupoD = new JLabel("Grupo D");
		lblGrupoD.setBounds(444, 190, 52, 16);
		contentPane.add(lblGrupoD);
		
		tbGrupoA = new JTable();
		tbGrupoA.setBorder(null);
		
		tbGrupoB = new JTable();
		tbGrupoB.setBorder(null);
		
		tbGrupoC = new JTable();
		tbGrupoC.setBorder(null);
		
		tbGrupoD = new JTable();
		tbGrupoD.setBorder(null);
		
		JScrollPane spGrupoA = new JScrollPane();
		spGrupoA.setBounds(55, 64, 331, 116);
		spGrupoA.setViewportView(tbGrupoA);
		contentPane.add(spGrupoA);
		
		JScrollPane spGrupoB = new JScrollPane();
		spGrupoB.setBounds(444, 64, 331, 116);
		spGrupoB.setViewportView(tbGrupoB);
		contentPane.add(spGrupoB);
		
		JScrollPane spGrupoC = new JScrollPane();
		spGrupoC.setBounds(55, 218, 331, 116);
		spGrupoC.setViewportView(tbGrupoC);
		contentPane.add(spGrupoC);
		
		JScrollPane spGrupoD = new JScrollPane();
		spGrupoD.setBounds(444, 218, 331, 116);
		spGrupoD.setViewportView(tbGrupoD);
		contentPane.add(spGrupoD);
		
		JButton btnApagarGrupos = new JButton("Apagar Grupos");
		btnApagarGrupos.setBounds(346, 361, 126, 29);
		contentPane.add(btnApagarGrupos);
		
		JButton btnGerarGrupos = new JButton("Gerar Grupos");
		btnGerarGrupos.setBounds(498, 361, 126, 29);
		contentPane.add(btnGerarGrupos);
		
		JButton btnFechar = new JButton("Voltar");
		btnFechar.setBounds(649, 361, 126, 29);
		contentPane.add(btnFechar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(JogoView.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 826, 432);
		contentPane.add(lblBackground);
		
		GrupoCtrl g = new GrupoCtrl(this, tbGrupoA, tbGrupoB, tbGrupoC, tbGrupoD);
		btnApagarGrupos.addActionListener(g.apagar);
		btnGerarGrupos.addActionListener(g.preencherTabela);
		btnFechar.addActionListener(g.fechar);
	}
}
