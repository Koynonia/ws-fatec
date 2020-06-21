package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class TelaMenu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	
	private JSeparator separator;
	
	private JButton btnCadApartamentos;
	private JButton btnDespApto;
	private JButton btnDespCond;
	private JButton btnCondMensal;
	
	private JLabel lblControleDeApartamentos;
	private JLabel lblControleDeDespesas;
	private JLabel lblControleDeDespesas_1;
	private JLabel lblCondomnioMensal;
	private JLabel lblLogo;
	private JLabel lblBackground;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenu frame = new TelaMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaMenu() {
		setTitle("Condom\u00EDnio Digital");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 672);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogo = new JLabel("CONDOMINIO DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 42));
		lblLogo.setBounds(32, 42, 469, 45);
		contentPane.add(lblLogo);

		separator = new JSeparator();
		separator.setBounds(10, 101, 819, 16);
		contentPane.add(separator);

		btnCadApartamentos = new JButton("");
		btnCadApartamentos.setBackground(SystemColor.window);
		btnCadApartamentos.setIcon(new ImageIcon(TelaMenu.class.getResource("/resources/cadApart.png")));
		btnCadApartamentos.setBounds(95, 256, 142, 112);

		contentPane.add(btnCadApartamentos);

		btnDespApto = new JButton("");
		btnDespApto.setBackground(Color.WHITE);
		btnDespApto.setIcon(new ImageIcon(TelaMenu.class.getResource("/resources/depApart.png")));
		btnDespApto.setBounds(258, 256, 142, 112);
		contentPane.add(btnDespApto);

		btnDespCond = new JButton("");
		btnDespCond.setBackground(Color.WHITE);
		btnDespCond.setIcon(new ImageIcon(TelaMenu.class.getResource("/resources/depCond.png")));
		btnDespCond.setBounds(420, 256, 142, 112);
		contentPane.add(btnDespCond);

		btnCondMensal = new JButton("");
		btnCondMensal.setBackground(Color.WHITE);
		btnCondMensal.setIcon(new ImageIcon(TelaMenu.class.getResource("/resources/conMensal.png")));
		btnCondMensal.setBounds(589, 256, 142, 112);
		contentPane.add(btnCondMensal);

		lblControleDeApartamentos = new JLabel("<html>Controle <br>de Apartamentos</html>");
		lblControleDeApartamentos.setVerticalAlignment(SwingConstants.TOP);
		lblControleDeApartamentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblControleDeApartamentos.setFont(new Font("Arial", Font.BOLD, 15));
		lblControleDeApartamentos.setBounds(95, 379, 142, 70);
		contentPane.add(lblControleDeApartamentos);

		lblControleDeDespesas = new JLabel("<html>Despesas  por  Apartamento</html>");
		lblControleDeDespesas.setFont(new Font("Arial", Font.BOLD, 15));
		lblControleDeDespesas.setHorizontalAlignment(SwingConstants.CENTER);
		lblControleDeDespesas.setVerticalAlignment(SwingConstants.TOP);
		lblControleDeDespesas.setBounds(258, 379, 142, 45);
		contentPane.add(lblControleDeDespesas);

		lblControleDeDespesas_1 = new JLabel("<html>Despesas do Condom\u00EDnio</html>");
		lblControleDeDespesas_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblControleDeDespesas_1.setVerticalAlignment(SwingConstants.TOP);
		lblControleDeDespesas_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblControleDeDespesas_1.setBounds(420, 379, 142, 45);
		contentPane.add(lblControleDeDespesas_1);

		lblCondomnioMensal = new JLabel("Condom\u00EDnio Mensal");
		lblCondomnioMensal.setFont(new Font("Arial", Font.BOLD, 15));
		lblCondomnioMensal.setVerticalAlignment(SwingConstants.TOP);
		lblCondomnioMensal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCondomnioMensal.setBounds(589, 379, 142, 45);
		contentPane.add(lblCondomnioMensal);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(TelaMenu.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 855, 672);
		contentPane.add(lblBackground);

		btnCadApartamentos.addActionListener(this);
		btnCondMensal.addActionListener(this);
		btnDespApto.addActionListener(this);
		btnDespCond.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCadApartamentos) {
			TelaApartamento tApto = new TelaApartamento();
			tApto.setVisible(true);
			this.dispose();
		} else if (e.getSource() == btnDespCond) {

			try {
				
				TelaDespesaCondominio tDCond = new TelaDespesaCondominio();
				tDCond.setVisible(true);
				this.dispose();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == btnDespApto) {
			TelaDespesaApartamento tDApto = new TelaDespesaApartamento();
			tDApto.setVisible(true);
			this.dispose();
		} else if (e.getSource() == btnCondMensal) {
			TelaCondominioMensal tMensal = new TelaCondominioMensal();
			tMensal.setVisible(true);
			this.dispose();
		}
	}
}
