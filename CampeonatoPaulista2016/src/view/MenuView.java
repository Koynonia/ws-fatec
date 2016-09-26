/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package view;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;

import controller.MenuCtrl;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class MenuView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8734424305792034378L;
	private JPanel contentPane;
	private JTable tabJogos;
	private JFormattedTextField ftxtData;
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
		
		setBounds(100, 100, 516, 293);
		setTitle("Campeonato Paulista 2016");
		setName("Menu");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGrupo = new JButton("Grupos");
		btnGrupo.setBounds(51, 29, 126, 62);
		contentPane.add(btnGrupo);
		
		JButton btnJogo = new JButton("Jogos");
		btnJogo.setBounds(51, 112, 126, 62);
		contentPane.add(btnJogo);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(347, 200, 117, 29);
		contentPane.add(btnFechar);
		
		MenuCtrl m = new MenuCtrl( this, btnGrupo, btnJogo, btnFechar );
		btnGrupo.addActionListener(m.janelas);
		btnJogo.addActionListener(m.janelas);
		btnFechar.addActionListener(m.janelas);
	}
}
