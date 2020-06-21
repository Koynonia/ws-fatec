/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 26/11/2016
 */

package view;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import controller.BackupCtrl;

public class BackupView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1680248143504167107L;
	private JPanel contentPane;
	private JTable tabela;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackupView frame = new BackupView();
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
	public BackupView() {
		
		setBounds(100, 100, 826, 480);
		setTitle("Backup da Base de Dados");
		setName("Backup");
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInfo = new JLabel("Bases de Dados encontradas:");
		lblInfo.setBounds(53, 40, 722, 16);
		contentPane.add(lblInfo);
		
		tabela = new JTable();
		tabela.setBorder(null);
		tabela.setAutoCreateRowSorter(true);
		
		JScrollPane spJogos = new JScrollPane();
		spJogos.setBounds(53, 58, 722, 265);
		spJogos.setViewportView(tabela);
		contentPane.add(spJogos);
		
		JButton btnRestaurar = new JButton("Restaurar");
		btnRestaurar.setBounds(520, 392, 126, 29);
		contentPane.add(btnRestaurar);
		
		JButton btnBackup = new JButton("Backup");
		btnBackup.setBounds(382, 392, 126, 29);
		contentPane.add(btnBackup);
		
		JButton btnFechar = new JButton("Voltar");
		btnFechar.setBounds(658, 392, 117, 29);
		contentPane.add(btnFechar);
		
		JRadioButton rdbtnBkp = new JRadioButton("Base selecionada");
		buttonGroup.add(rdbtnBkp);
		rdbtnBkp.setBounds(53, 334, 302, 23);
		contentPane.add(rdbtnBkp);
		
		JRadioButton rdbtnBkpSGDB = new JRadioButton("SGDB todo");
		buttonGroup.add(rdbtnBkpSGDB);
		rdbtnBkpSGDB.setBounds(53, 359, 302, 23);
		rdbtnBkpSGDB.setSelected(true);
		contentPane.add(rdbtnBkpSGDB);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(BackupView.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 826, 458);
		contentPane.add(lblBackground);
		
		BackupCtrl ctrl = new BackupCtrl( this, lblInfo, rdbtnBkp, rdbtnBkpSGDB, tabela );
		
		tabela.addMouseListener( ctrl.clicar );
		btnBackup.addActionListener(ctrl.backupDB);
		btnRestaurar.addActionListener(ctrl.restaurar);
		btnFechar.addActionListener(ctrl.fechar);
	}
}
