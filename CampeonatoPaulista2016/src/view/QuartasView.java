/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/10/2016
 */

package view;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import controller.QuartasCtrl;

import java.awt.Font;
import javax.swing.SwingConstants;

public class QuartasView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4529951623192410136L;
	private JPanel contentPane;
	private JTable tabGrupoA;
	private JTable tabGrupoB;
	private JTable tabGrupoC;
	private JTable tabGrupoD;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuartasView frame = new QuartasView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public QuartasView() throws SQLException {
		
		setBounds(100, 100, 826, 432);
		setTitle("Quartas de Final");
		setName("quartas");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGrupoA = new JLabel("Jogo 1");
		lblGrupoA.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupoA.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupoA.setBounds(20, 29, 783, 16);
		contentPane.add(lblGrupoA);
		
		tabGrupoA = new JTable();
		tabGrupoA.setBorder(null);
		
		JScrollPane spGrupoA = new JScrollPane();
		spGrupoA.setBounds(20, 53, 783, 48);
		spGrupoA.setViewportView(tabGrupoA);
		contentPane.add(spGrupoA);
		
		JLabel lblGrupoB = new JLabel("Jogo 2");
		lblGrupoB.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupoB.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupoB.setBounds(20, 113, 783, 16);
		contentPane.add(lblGrupoB);
		
		tabGrupoB = new JTable();
		tabGrupoB.setBorder(null);
		
		JScrollPane spGrupoB = new JScrollPane();
		spGrupoB.setBounds(20, 137, 783, 48);
		spGrupoB.setViewportView(tabGrupoB);
		contentPane.add(spGrupoB);
		
		JLabel lblGrupoC = new JLabel("Jogo 3");
		lblGrupoC.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupoC.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupoC.setBounds(20, 197, 783, 16);
		contentPane.add(lblGrupoC);
		
		tabGrupoC = new JTable();
		tabGrupoC.setBorder(null);
		
		JScrollPane spGrupoC = new JScrollPane();
		spGrupoC.setBounds(20, 221, 783, 48);
		spGrupoC.setViewportView(tabGrupoC);
		contentPane.add(spGrupoC);
		
		JLabel lblGrupoD = new JLabel("Jogo 4");
		lblGrupoD.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupoD.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupoD.setBounds(20, 281, 783, 16);
		contentPane.add(lblGrupoD);
		
		tabGrupoD = new JTable();
		tabGrupoD.setBorder(null);
		
		JScrollPane spGrupoD = new JScrollPane();
		spGrupoD.setBounds(20, 305, 783, 48);
		spGrupoD.setViewportView(tabGrupoD);
		contentPane.add(spGrupoD);
		
//		JButton btnGerar = new JButton("Atualizar");
//		btnGerar.setBounds(547, 365, 126, 29);
//		contentPane.add(btnGerar);
		
		JButton btnFechar = new JButton("Voltar");
		btnFechar.setBounds(685, 365, 117, 29);
		contentPane.add(btnFechar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(QuartasView.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 1176, 532);
		contentPane.add(lblBackground);
		
		QuartasCtrl ctrl = new QuartasCtrl(
				this,
				tabGrupoA, 
				tabGrupoB, 
				tabGrupoC,
				tabGrupoD);

//		btnGerar.addActionListener(ctrl.preencherTabela);
		btnFechar.addActionListener(ctrl.fechar);
	}
}