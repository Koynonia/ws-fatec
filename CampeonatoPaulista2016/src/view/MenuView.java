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
		
		setBounds(100, 100, 826, 432);
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
		lblGrupo.setBounds(64, 186, 165, 16);
		contentPane.add(lblGrupo);
		
		JLabel lblJogo = new JLabel("Jogos");
		lblJogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogo.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblJogo.setBounds(64, 379, 165, 16);
		contentPane.add(lblJogo);
		
		JLabel lblResultado = new JLabel("Resutlado dos Grupos");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblResultado.setBounds(326, 186, 165, 16);
		contentPane.add(lblResultado);
		
		JLabel lblGeral = new JLabel("Resultado Geral");
		lblGeral.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeral.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGeral.setBounds(326, 379,165, 16);
		contentPane.add(lblGeral);
		
		JLabel lblQuartas = new JLabel("Quartas de Final");
		lblQuartas.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuartas.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblQuartas.setBounds(589, 186, 165, 16);
		contentPane.add(lblQuartas);
		
		JButton btnGrupo = new JButton("");
		btnGrupo.setBackground(SystemColor.window);
		btnGrupo.setIcon(new ImageIcon(MenuView.class.getResource("/resources/btnGrupo.png")));
		btnGrupo.setBounds(64, 10, 165, 165);
		contentPane.add(btnGrupo);
		
		JButton btnJogo = new JButton("");
		btnJogo.setIcon(new ImageIcon(MenuView.class.getResource("/resources/btnJogo.png")));
		btnJogo.setBounds(64, 210, 165, 165);
		contentPane.add(btnJogo);
		
		JButton btnResultado = new JButton("");
		btnResultado.setBackground(SystemColor.window);
		btnResultado.setIcon(new ImageIcon(MenuView.class.getResource("/resources/btnJogo.png")));
		btnResultado.setBounds(326, 10, 165, 165);
		contentPane.add(btnResultado);
		
		JButton btnGeral = new JButton("");
		btnGeral.setIcon(new ImageIcon(MenuView.class.getResource("/resources/btnJogo.png")));
		btnGeral.setBounds(326, 210, 165, 165);
		contentPane.add(btnGeral);
		
		JButton btnQuartas = new JButton("");
		btnQuartas.setIcon(new ImageIcon(MenuView.class.getResource("/resources/btnJogo.png")));
		btnQuartas.setBounds(589, 10, 165, 165);
		contentPane.add(btnQuartas);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(612, 337, 117, 29);
		contentPane.add(btnFechar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MenuView.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 820, 404);
		contentPane.add(lblBackground);
		
		MenuCtrl m = new MenuCtrl( this, btnGrupo, btnJogo, btnResultado, btnGeral, btnQuartas, btnFechar );
		
		btnGrupo.addActionListener(m.janelas);
		btnJogo.addActionListener(m.janelas);
		btnResultado.addActionListener(m.janelas);
		btnGeral.addActionListener(m.janelas);
		btnQuartas.addActionListener(m.janelas);
		btnFechar.addActionListener(m.janelas);
	}
}
