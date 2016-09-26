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

import controller.JogoCtrl;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class JogosView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974202157363751649L;
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
					JogosView frame = new JogosView();
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
	public JogosView() {
		
		setBounds(100, 100, 560, 400);
		setTitle("Jogos do Campeonato");
		setName("Jogos");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		JLabel lblData = new JLabel("Digite uma data de Jogo:");
		lblData.setBounds(22, 31, 164, 16);
		contentPane.add(lblData);
		
		MaskFormatter maskData;
		try {
			maskData = new MaskFormatter("##/##/####");
			ftxtData = new JFormattedTextField(maskData);
			ftxtData.setHorizontalAlignment(SwingConstants.CENTER);
			ftxtData.setBounds(192, 25, 134, 29);
			contentPane.add(ftxtData);
			ftxtData.setColumns(10);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblJogos = new JLabel("Jogos do Campeonato:");
		lblJogos.setBounds(22, 70, 147, 16);
		contentPane.add(lblJogos);
		
		JScrollPane spJogos = new JScrollPane();
		spJogos.setBounds(22, 90, 515, 194);
		spJogos.setViewportView(tabJogos);
		contentPane.add(spJogos);
		
		tabJogos = new JTable();
		tabJogos.setBorder(null);
		
		JButton btnGerarJogos = new JButton("Gerar Jogos");
		btnGerarJogos.setBounds(218, 332, 126, 29);
		btnGerarJogos.setVisible(true);
		contentPane.setLayout(null);
		contentPane.add(btnGerarJogos);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.setBounds(338, 26, 117, 29);
		contentPane.add(btnVerificar);
		
		JogoCtrl j = new JogoCtrl(ftxtData, tabJogos);
		btnVerificar.addActionListener(j.preencherTabela);
		btnGerarJogos.addActionListener(j.preencherTabela);
	}
}
