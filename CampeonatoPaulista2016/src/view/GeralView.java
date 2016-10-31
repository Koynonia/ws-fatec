/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/10/2016
 */

package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import controller.GeralCtrl;

public class GeralView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7800115633462294103L;
	private JPanel contentPane;
	private JTable tabGeral;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeralView frame = new GeralView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GeralView() {
		
		setBounds(100, 100, 826, 432);
		setTitle("Resultado Geral do Campeonato");
		setName("Grupos");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGrupoA = new JLabel("Resultado Geral");
		lblGrupoA.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupoA.setBounds(42, 8, 746, 16);
		contentPane.add(lblGrupoA);
		
		tabGeral = new JTable();
		tabGeral.setBorder(null);
		
		JScrollPane spGrupoA = new JScrollPane();
		spGrupoA.setBounds(38, 25, 752, 340);
		spGrupoA.setViewportView(tabGeral);
		contentPane.add(spGrupoA);
		
		JButton btnGerar = new JButton("Atualizar");
		btnGerar.setBounds(511, 375, 126, 29);
		contentPane.add(btnGerar);
		
		JButton btnFechar = new JButton("Voltar");
		btnFechar.setBounds(662, 375, 126, 29);
		contentPane.add(btnFechar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(JogoView.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 826, 420);
		contentPane.add(lblBackground);
		
		GeralCtrl ctrl = new GeralCtrl(this, tabGeral);
		
		btnGerar.addActionListener(ctrl.preencherTabela);
		btnFechar.addActionListener(ctrl.fechar);
	}
}
