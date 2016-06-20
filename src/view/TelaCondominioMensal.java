/**
 * @author Fernando Moraes Oliveira
 * Matéria 4716 - Engenharia de Software 2
 * 3º ADS - Tarde
 * Iniciado em 12/06/2016
 */

package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.CondominioMensalController;

public class TelaCondominioMensal extends JFrame {

	private static final long serialVersionUID = 1231426486405818296L;

	private JPanel painel;
	private JScrollPane spDespesas;
	private JSeparator linha;
	private JLabel lblLogo;
	private JLabel lblBackground; 
	private JLabel lblPagina;
	private JLabel lblFiltro;
	private JLabel lblApto;
	private JLabel lblDtVenc;
	private JLabel lblDtPagto;
	private JLabel lblId;
	private JLabel lblDtReg;
	private JLabel lblDtAlt;
	private JLabel lblReferencia;
	private JLabel lblMulta;
	private JLabel lblVlr;
	private JLabel lblNome;
	private JLabel lblDespesa;
	private JLabel lblQtd;
	private JLabel lblVlrTotal;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtPesquisa;
	private JFormattedTextField ftxtDtVenc;
	private JFormattedTextField ftxtDtPagto;
	private JFormattedTextField ftxtDtReg;
	private JFormattedTextField ftxtDtAlt;
	private JFormattedTextField ftxtMulta;
	private JFormattedTextField ftxtValor;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtVlrTotal;
	private JComboBox<String> cboFiltro;
	private JComboBox<String> cboApto;
	private JComboBox<String> cboReferencia;
	private JCheckBox chkMulta; 
	private JButton btnPesquisar;
	private JButton btnLimpar;
	private JButton btnSalvar;
	private JButton btnVoltar;
	private JTable tabDespesa;
	private MaskFormatter maskData;
	private DecimalFormat maskValor;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCondominioMensal frame = new TelaCondominioMensal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaCondominioMensal()  {
		
		setTitle("Condomínio Mensal");
		setName("CondMes");
		setResizable(false);
		setSize( 1024, 710 );
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		
		painel = new JPanel();
		setContentPane( painel );
		painel.setName("Despesa");
		painel.setLayout(null);
		
		lblLogo = new JLabel("CONDOMÍNIO DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(48, 36, 410, 45);
		painel.add(lblLogo);
		
		lblPagina = new JLabel("Condomínio Mensal");
		lblPagina.setEnabled(false);
		lblPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPagina.setBounds(808, 76, 164, 22);
		painel.add(lblPagina);
		
		linha = new JSeparator();
		linha.setBounds(6, 93, 1012, 12);
		painel.add(linha);
		
		lblFiltro = new JLabel("Visão");
		lblFiltro.setBounds(131, 139, 34, 16);
		painel.add(lblFiltro);
		
		cboFiltro = new JComboBox<String>();
		cboFiltro.setBounds(176, 133, 160, 28);
		painel.add(cboFiltro);
		
		lblApto = new JLabel("Apartamento");
		lblApto.setBounds(84, 206, 81, 16);
		painel.add(lblApto);
		
		cboApto = new JComboBox<String>();
		cboApto.setBounds(176, 200, 160, 28);
		painel.add(cboApto);
		
		lblDtVenc = new JLabel("Data de Vencimento");
		lblDtVenc.setBounds(688, 139, 128, 16);
		painel.add(lblDtVenc);
		
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ftxtDtVenc = new JFormattedTextField(maskData);
		ftxtDtVenc.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDtVenc.setBounds(827, 133, 142, 28);
		ftxtDtVenc.setColumns(10);
		painel.add(ftxtDtVenc);
		
		lblDtPagto = new JLabel("Data de Pagamento");
		lblDtPagto.setBounds(688, 174, 128, 16);
		painel.add(lblDtPagto);
		
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ftxtDtPagto = new JFormattedTextField(maskData);
		ftxtDtPagto.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDtPagto.setBounds(827, 168, 142, 28);
		ftxtDtPagto.setColumns(10);
		painel.add(ftxtDtPagto);
		
		lblId = new JLabel("Código");
		lblId.setBounds(107, 587, 45, 16);
		painel.add(lblId);
		
		txtId = new JTextField(20);
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(163, 581, 142, 28);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		painel.add(txtId);
		
		lblDtReg = new JLabel("Data de Registro");
		lblDtReg.setBounds(48, 622, 104, 16);
		painel.add(lblDtReg);
		
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ftxtDtReg = new JFormattedTextField(maskData);
		ftxtDtReg.setEnabled(false);
		ftxtDtReg.setEditable(false);
		ftxtDtReg.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDtReg.setBounds(163, 616, 142, 28);
		ftxtDtReg.setColumns(10);
		painel.add(ftxtDtReg);
		
		lblDtAlt = new JLabel("Data de Alteração");
		lblDtAlt.setBounds(48, 654, 117, 16);
		painel.add(lblDtAlt);
		
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ftxtDtAlt = new JFormattedTextField(maskData);
		ftxtDtAlt.setEnabled(false);
		ftxtDtAlt.setEditable(false);
		ftxtDtAlt.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDtAlt.setBounds(163, 648, 142, 28);
		ftxtDtAlt.setColumns(10);
		painel.add(ftxtDtAlt);
		
		lblReferencia = new JLabel("Mês de Referência");
		lblReferencia.setBounds(48, 174, 117, 16);
		painel.add(lblReferencia);
		
		cboReferencia = new JComboBox<String>();
		cboReferencia.setBounds(176, 168, 160, 28);
		painel.add(cboReferencia);
		
		lblNome = new JLabel("Morador");
		lblNome.setBounds(104, 240, 61, 16);
		painel.add(lblNome);
		
		txtNome = new JTextField(20);
		txtNome.setBounds(176, 234, 254, 28);
		painel.add(txtNome);
		
		lblVlr = new JLabel("Valor Total");
		lblVlr.setBounds(748, 240, 68, 16);
		painel.add(lblVlr);
		
		maskValor = new DecimalFormat("#,###,##0.00");
		NumberFormatter multa = new NumberFormatter(maskValor);
		multa.setFormat(maskValor);
		multa.setAllowsInvalid(false);
		DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(multa);
		ftxtMulta = new JFormattedTextField(dfFactory);
		ftxtMulta.setBackground(Color.WHITE);
		ftxtMulta.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtMulta.setBounds(830, 234, 142, 28);
		ftxtMulta.setColumns(10);
		painel.add(ftxtMulta);
		
		lblMulta = new JLabel("Multa");
		lblMulta.setBounds(781, 206, 35, 16);
		painel.add(lblMulta);
		
		maskValor = new DecimalFormat("#,###,##0.00");
		NumberFormatter valor = new NumberFormatter(maskValor);
		valor.setFormat(maskValor);
		valor.setAllowsInvalid(false);
		DefaultFormatterFactory dff = new DefaultFormatterFactory(valor);
		ftxtValor = new JFormattedTextField(dff);
		ftxtValor.setBackground(Color.WHITE);
		ftxtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtValor.setBounds(827, 200, 142, 28);
		ftxtValor.setColumns(10);
		painel.add(ftxtValor);
		
		lblDespesa = new JLabel("Pesquisa");
		lblDespesa.setBounds(100, 553, 68, 16);
		painel.add(lblDespesa);
		
		txtPesquisa = new JTextField(20);
		txtPesquisa.setName("despesa");
		txtPesquisa.setToolTipText("Digite aqui o termo que deseja pesquisar…");
		txtPesquisa.setBounds(164, 547, 254, 28);
		painel.add(txtPesquisa);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(430, 547, 95, 29);
		painel.add(btnPesquisar);
		
		spDespesas = new JScrollPane();
		spDespesas.setBounds(48, 298, 928, 241);
		painel.add(spDespesas);
		
		tabDespesa = new JTable();
		spDespesas.setViewportView(tabDespesa);
		tabDespesa.setBorder(null);
		
		lblQtd = new JLabel("Quantidade");
		lblQtd.setBounds(576, 553, 73, 16);
		painel.add(lblQtd);
		
		ftxtQtd = new JFormattedTextField((Format) null);
		ftxtQtd.setBackground(SystemColor.window);
		ftxtQtd.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtQtd.setEditable(false);
		ftxtQtd.setColumns(10);
		ftxtQtd.setBounds(661, 547, 58, 28);
		painel.add(ftxtQtd);
		
		lblVlrTotal = new JLabel("Valor Mensal");
		lblVlrTotal.setBounds(742, 553, 81, 16);
		painel.add(lblVlrTotal);
		
		maskValor = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter total = new NumberFormatter(maskValor);
		total.setFormat(maskValor);
		total.setAllowsInvalid(false);
		ftxtVlrTotal = new JFormattedTextField(maskValor);
		ftxtVlrTotal.setBackground(SystemColor.window);
		ftxtVlrTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtVlrTotal.setEditable(false);
		ftxtVlrTotal.setBounds(834, 547, 142, 28);
		ftxtVlrTotal.setColumns(10);
		painel.add(ftxtVlrTotal);
		
		chkMulta = new JCheckBox("Prorrogar para o próximo Mês - ");
		chkMulta.setBounds(548, 203, 235, 23);
		painel.add(chkMulta);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(474, 625, 117, 29);
		painel.add(btnLimpar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(602, 625, 117, 29);
		painel.add(btnSalvar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(860, 625, 117, 29);
		painel.add(btnVoltar);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(TelaMenu.class.getResource("/resources/back.jpg")));
		lblBackground.setBounds(0, 0, 1024, 710);
		painel.add(lblBackground);
		
		CondominioMensalController despesasCtrl = new CondominioMensalController(
				this, 
				painel, 
				tabDespesa, 
				lblId, 
				lblDtReg, 
				lblDtAlt, 
				lblMulta, 
				txtId, 
				txtNome,  
				txtPesquisa, 
				ftxtDtVenc, 
				ftxtDtPagto, 
				ftxtDtReg, 
				ftxtDtAlt, 
				ftxtValor, 
				ftxtMulta, 
				ftxtQtd, 
				ftxtVlrTotal, 
				cboFiltro, 
				cboApto,
				cboReferencia, 
				chkMulta, 
				btnLimpar );
		
		JLabel label = new JLabel("Apartamento");
		label.setBounds(220, 206, 81, 16);
		painel.add(label);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(312, 200, 160, 28);
		painel.add(comboBox);
		
		tabDespesa.addMouseListener(despesasCtrl.clique);
		tabDespesa.addKeyListener(despesasCtrl.tecla);
		txtPesquisa.addActionListener(despesasCtrl.pesquisar);
		ftxtDtPagto.addActionListener(despesasCtrl.atualizar);
		ftxtDtPagto.addFocusListener(despesasCtrl.foco);
		chkMulta.addActionListener(despesasCtrl.atualizar);
		cboFiltro.addActionListener(despesasCtrl.filtrar);
		cboApto.addActionListener(despesasCtrl.filtrar);
		cboReferencia.addActionListener(despesasCtrl.filtrar);
		cboReferencia.addActionListener(despesasCtrl.atualizar);
		btnPesquisar.addActionListener(despesasCtrl.pesquisar);
		btnLimpar.addActionListener(despesasCtrl.limpar);
		btnSalvar.addActionListener(despesasCtrl.inserir);
		btnVoltar.addActionListener(despesasCtrl.voltar);
	}
}