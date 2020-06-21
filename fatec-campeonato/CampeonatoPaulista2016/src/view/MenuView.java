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
import java.awt.Font;
import javax.swing.SwingConstants;

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
		
		setBounds(100, 100, 826, 480);
		setTitle("Campeonato Paulista 2016");
		setName("Menu");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGrupo = new JLabel("Grupos");
		lblGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupo.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGrupo.setBounds(64, 182, 165, 16);
		contentPane.add(lblGrupo);
		
		JLabel lblJogo = new JLabel("Jogos");
		lblJogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogo.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblJogo.setBounds(64, 379, 165, 16);
		contentPane.add(lblJogo);
		
		JLabel lblResultado = new JLabel("Resutlado dos Grupos");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblResultado.setBounds(326, 182, 165, 16);
		contentPane.add(lblResultado);
		
		JLabel lblGeral = new JLabel("Resultado Geral");
		lblGeral.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeral.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGeral.setBounds(326, 379,165, 16);
		contentPane.add(lblGeral);
		
		JLabel lblQuartas = new JLabel("Quartas de Final");
		lblQuartas.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuartas.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblQuartas.setBounds(589, 182, 165, 16);
		contentPane.add(lblQuartas);
		
		JLabel lblBackup = new JLabel("Backup");
		lblBackup.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackup.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblBackup.setBounds(589, 379,165, 16);
		contentPane.add(lblBackup);
		
		JButton btnGrupo = new JButton("");
		btnGrupo.setBackground(SystemColor.window);
		btnGrupo.setIcon(new ImageIcon(MenuView.class.getResource("/resources/groups.png")));
		btnGrupo.setBounds(64, 10, 165, 165);
		contentPane.add(btnGrupo);
		
		JButton btnJogo = new JButton("");
		btnJogo.setIcon(new ImageIcon(MenuView.class.getResource("/resources/ball.png")));
		btnJogo.setBounds(64, 210, 165, 165);
		contentPane.add(btnJogo);
		
		JButton btnResultado = new JButton("");
		btnResultado.setBackground(SystemColor.window);
		btnResultado.setIcon(new ImageIcon(MenuView.class.getResource("/resources/ball.png")));
		btnResultado.setBounds(326, 10, 165, 165);
		contentPane.add(btnResultado);
		
		JButton btnGeral = new JButton("");
		btnGeral.setIcon(new ImageIcon(MenuView.class.getResource("/resources/ball.png")));
		btnGeral.setBounds(326, 210, 165, 165);
		contentPane.add(btnGeral);
		
		JButton btnQuartas = new JButton("");
		btnQuartas.setIcon(new ImageIcon(MenuView.class.getResource("/resources/ball.png")));
		btnQuartas.setBounds(589, 10, 165, 165);
		contentPane.add(btnQuartas);
		
		JButton btnBackup = new JButton("");
		btnBackup.setIcon(new ImageIcon(MenuView.class.getResource("/resources/floppy.png")));
		btnBackup .setBounds(589, 210, 165, 165);
		contentPane.add(btnBackup );
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(350, 410, 117, 29);
		contentPane.add(btnFechar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MenuView.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 826, 458);
		contentPane.add(lblBackground);
		
		MenuCtrl m = new MenuCtrl( this, btnGrupo, btnJogo, btnResultado, btnGeral, btnQuartas, btnBackup, btnFechar );
		
		btnGrupo.addActionListener(m.janelas);
		btnJogo.addActionListener(m.janelas);
		btnResultado.addActionListener(m.janelas);
		btnGeral.addActionListener(m.janelas);
		btnQuartas.addActionListener(m.janelas);
		btnBackup.addActionListener(m.janelas);
		btnFechar.addActionListener(m.janelas);
	}
}
