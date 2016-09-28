/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package view;

import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.MenuCtrl;

public class MenuView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8734424305792034378L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
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
	public MenuView() {
		
		setBounds(100, 100, 580, 360);
		setTitle("Campeonato Paulista 2016");
		setName("Menu");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGrupo = new JLabel("Grupos");
		lblGrupo.setBounds(112, 59, 45, 16);
		contentPane.add(lblGrupo);
		
		JLabel lblJogo = new JLabel("Jogos");
		lblJogo.setBounds(428, 59, 35, 16);
		contentPane.add(lblJogo);
		
		JButton btnGrupo = new JButton("");
		btnGrupo.setBackground(SystemColor.window);
		btnGrupo.setIcon(new ImageIcon(MenuView.class.getResource("/resources/btnGrupo.png")));
		btnGrupo.setBounds(19, 43, 230, 205);
		contentPane.add(btnGrupo);
		
		JButton btnJogo = new JButton("");
		btnJogo.setIcon(new ImageIcon(MenuView.class.getResource("/resources/btnJogo.png")));
		btnJogo.setBounds(330, 43, 230, 205);
		contentPane.add(btnJogo);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(231, 279, 117, 29);
		contentPane.add(btnFechar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(JogoView.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 580, 360);
		contentPane.add(lblBackground);
		
		MenuCtrl m = new MenuCtrl( this, btnGrupo, btnJogo, btnFechar );
		btnGrupo.addActionListener(m.janelas);
		btnJogo.addActionListener(m.janelas);
		btnFechar.addActionListener(m.janelas);
	}
}
