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

import controller.ResultadoCtrl;
import java.awt.Font;

public class ResultadoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5523273025075516754L;	
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
					ResultadoView frame = new ResultadoView();
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
	public ResultadoView() throws SQLException {
		
		setBounds(100, 100, 826, 610);
		setTitle("Resultado dos Grupos");
		setName("resultGrupos");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGrupoA = new JLabel("Resultados do Grupo A:");
		lblGrupoA.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupoA.setBounds(21, 21, 783, 16);
		contentPane.add(lblGrupoA);
		
		tabGrupoA = new JTable();
		tabGrupoA.setBorder(null);
		
		JScrollPane spGrupoA = new JScrollPane();
		spGrupoA.setBounds(21, 45, 783, 100);
		spGrupoA.setViewportView(tabGrupoA);
		contentPane.add(spGrupoA);
		
		JLabel lblGrupoB = new JLabel("Resultados do Grupo B:");
		lblGrupoB.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupoB.setBounds(21, 154, 783, 16);
		contentPane.add(lblGrupoB);
		
		tabGrupoB = new JTable();
		tabGrupoB.setBorder(null);
		
		JScrollPane spGrupoB = new JScrollPane();
		spGrupoB.setBounds(21, 178, 783, 100);
		spGrupoB.setViewportView(tabGrupoB);
		contentPane.add(spGrupoB);
		
		JLabel lblGrupoC = new JLabel("Resultados do Grupo C:");
		lblGrupoC.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupoC.setBounds(21, 287, 783, 16);
		contentPane.add(lblGrupoC);
		
		tabGrupoC = new JTable();
		tabGrupoC.setBorder(null);
		
		JScrollPane spGrupoC = new JScrollPane();
		spGrupoC.setBounds(21, 311, 783, 100);
		spGrupoC.setViewportView(tabGrupoC);
		contentPane.add(spGrupoC);
		
		JLabel lblGrupoD = new JLabel("Resultados do Grupo D:");
		lblGrupoD.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupoD.setBounds(21, 420, 783, 16);
		contentPane.add(lblGrupoD);
		
		tabGrupoD = new JTable();
		tabGrupoD.setBorder(null);
		
		JScrollPane spGrupoD = new JScrollPane();
		spGrupoD.setBounds(21, 444, 783, 100);
		spGrupoD.setViewportView(tabGrupoD);
		contentPane.add(spGrupoD);
		
		JButton btnGerar = new JButton("Atualizar");
		btnGerar.setBounds(550, 553, 126, 29);
		contentPane.add(btnGerar);
		
		JButton btnFechar = new JButton("Voltar");
		btnFechar.setBounds(688, 553, 117, 29);
		contentPane.add(btnFechar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(ResultadoView.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 1176, 532);
		contentPane.add(lblBackground);
		
		ResultadoCtrl ctrl = new ResultadoCtrl(
				this,
				tabGrupoA, 
				tabGrupoB, 
				tabGrupoC,
				tabGrupoD);

		btnGerar.addActionListener(ctrl.preencherTabela);
		btnFechar.addActionListener(ctrl.fechar);
	}
}