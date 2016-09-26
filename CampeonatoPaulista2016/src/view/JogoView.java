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

public class JogoView extends JFrame {

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
					JogoView frame = new JogoView();
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
	public JogoView() {
		
		setBounds(100, 100, 826, 432);
		setTitle("Jogos do Campeonato");
		setName("Jogos");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblData = new JLabel("Digite uma data de Jogo:");
		lblData.setBounds(53, 37, 164, 16);
		contentPane.add(lblData);
		
		MaskFormatter maskData;
		try {
			maskData = new MaskFormatter("##/##/####");
			ftxtData = new JFormattedTextField(maskData);
			ftxtData.setHorizontalAlignment(SwingConstants.CENTER);
			ftxtData.setBounds(223, 31, 134, 29);
			contentPane.add(ftxtData);
			ftxtData.setColumns(10);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblJogos = new JLabel("Jogos do Campeonato:");
		lblJogos.setBounds(53, 95, 147, 16);
		contentPane.add(lblJogos);
		
		JScrollPane spJogos = new JScrollPane();
		spJogos.setBounds(53, 119, 722, 194);
		spJogos.setViewportView(tabJogos);
		contentPane.add(spJogos);
		
		tabJogos = new JTable();
		tabJogos.setBorder(null);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.setBounds(369, 32, 117, 29);
		contentPane.add(btnVerificar);
		
		JButton btnGerarJogos = new JButton("Gerar Jogos");
		btnGerarJogos.setBounds(512, 352, 126, 29);
		contentPane.add(btnGerarJogos);
		
		JButton btnFechar = new JButton("Voltar");
		btnFechar.setBounds(658, 352, 117, 29);
		contentPane.add(btnFechar);
		
		JogoCtrl j = new JogoCtrl( this, ftxtData, tabJogos );
		
		btnVerificar.addActionListener(j.preencherTabela);
		btnGerarJogos.addActionListener(j.preencherTabela);
		btnFechar.addActionListener(j.fechar);
	}
}
