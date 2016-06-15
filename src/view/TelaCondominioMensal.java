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
	private JLabel lblApto;
	private JLabel lblDtVenc;
	private JLabel lblId;
	private JLabel lblDtReg;
	private JLabel lblDtAlt;
	private JLabel lblReferencia;
	private JLabel lblVlr;
	private JLabel lblDespesa;
	private JLabel lblQtd;
	private JLabel lblVlrTotal;
	private JTextField txtId;
	private JTextField txtReferencia;
	private JTextField txtDespesa;
	private JFormattedTextField ftxtDtVenc;
	private JFormattedTextField ftxtDtReg;
	private JFormattedTextField ftxtDtAlt;
	private JFormattedTextField ftxtValor;
	private JFormattedTextField ftxtQtd;
	private JFormattedTextField ftxtVlrTotal;
	private JComboBox<String> cboReferencia;
	private JComboBox<String> cboApto;
	private JButton btnPesquisar;
	private JButton btnLimpar;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnCancelar;
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
		
		lblApto = new JLabel("Apartamento");
		lblApto.setBounds(95, 118, 81, 16);
		painel.add(lblApto);
		
		cboApto = new JComboBox<String>();
		cboApto.setBounds(187, 112, 160, 28);
		painel.add(cboApto);
		
		lblDtVenc = new JLabel("Data de Vencimento");
		lblDtVenc.setBounds(48, 151, 128, 16);
		painel.add(lblDtVenc);
		
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ftxtDtVenc = new JFormattedTextField(maskData);
		ftxtDtVenc.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDtVenc.setBounds(187, 145, 142, 28);
		ftxtDtVenc.setColumns(10);
		painel.add(ftxtDtVenc);
		
		lblId = new JLabel("Código");
		lblId.setBounds(778, 151, 45, 16);
		painel.add(lblId);
		
		txtId = new JTextField(20);
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(834, 145, 142, 28);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		painel.add(txtId);
		
		lblDtReg = new JLabel("Data de Registro");
		lblDtReg.setBounds(719, 185, 104, 16);
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
		ftxtDtReg.setBounds(834, 179, 142, 28);
		ftxtDtReg.setColumns(10);
		painel.add(ftxtDtReg);
		
		lblDtAlt = new JLabel("Data de Alteração");
		lblDtAlt.setBounds(719, 219, 117, 16);
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
		ftxtDtAlt.setBounds(834, 213, 142, 28);
		ftxtDtAlt.setColumns(10);
		painel.add(ftxtDtAlt);
		
		lblReferencia = new JLabel("Mês de Referência");
		lblReferencia.setBounds(59, 185, 117, 16);
		painel.add(lblReferencia);
		
		cboReferencia = new JComboBox<String>();
		cboReferencia.setBounds(187, 179, 160, 28);
		painel.add(cboReferencia);
		
		txtReferencia = new JTextField(20);
		txtReferencia.setBounds(387, 179, 142, 28);
		txtReferencia.setHorizontalAlignment(SwingConstants.CENTER);
		painel.add(txtReferencia);
		
		lblVlr = new JLabel("Valor");
		lblVlr.setBounds(144, 219, 32, 16);
		painel.add(lblVlr);
		
		maskValor = new DecimalFormat("#,###,##0.00");
		NumberFormatter valor = new NumberFormatter(maskValor);
		valor.setFormat(maskValor);
		valor.setAllowsInvalid(false);
		DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(valor);
		ftxtValor = new JFormattedTextField(dfFactory);
		ftxtValor.setBackground(Color.WHITE);
		ftxtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtValor.setBounds(187, 213, 142, 28);
		ftxtValor.setColumns(10);
		painel.add(ftxtValor);
		
		lblDespesa = new JLabel("Despesa");
		lblDespesa.setBounds(123, 253, 53, 16);
		painel.add(lblDespesa);
		
		txtDespesa = new JTextField(20);
		txtDespesa.setName("despesa");
		txtDespesa.setToolTipText("Digite aqui o termo que deseja pesquisar…");
		txtDespesa.setBounds(187, 247, 254, 28);
		painel.add(txtDespesa);
		
		btnPesquisar = new JButton("Pesquisa");
		btnPesquisar.setBounds(453, 247, 95, 29);
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
		
		lblVlrTotal = new JLabel("Valor Total");
		lblVlrTotal.setBounds(750, 553, 73, 16);
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
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(689, 625, 117, 29);
		painel.add(btnCancelar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(303, 625, 117, 29);
		painel.add(btnLimpar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(560, 625, 117, 29);
		painel.add(btnExcluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(431, 625, 117, 29);
		painel.add(btnEditar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(431, 625, 117, 29);
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
				lblDtVenc, 
				lblVlr, 
				lblId, 
				lblDtReg, 
				lblDtAlt, 
				txtId, 
				txtReferencia,  
				txtDespesa, 
				ftxtDtVenc, 
				ftxtDtReg, 
				ftxtDtAlt, 
				ftxtValor, 
				ftxtQtd, 
				ftxtVlrTotal, 
				cboReferencia,
				cboApto, 
				btnPesquisar, 
				btnLimpar, 
				btnEditar, 
				btnExcluir, 
				btnSalvar, 
				btnCancelar );
		
		tabDespesa.addMouseListener(despesasCtrl.clique);
		tabDespesa.addKeyListener(despesasCtrl.tecla);
		txtDespesa.addActionListener(despesasCtrl.pesquisar);
		txtDespesa.addFocusListener(despesasCtrl.foco);
		ftxtDtVenc.addFocusListener(despesasCtrl.foco);
		cboReferencia.addActionListener(despesasCtrl.filtrar);
		btnPesquisar.addActionListener(despesasCtrl.pesquisar);
		btnLimpar.addActionListener(despesasCtrl.limpar);
		btnEditar.addActionListener(despesasCtrl.editar);
		btnExcluir.addActionListener(despesasCtrl.excluir);
		btnCancelar.addActionListener(despesasCtrl.cancelar);
		btnSalvar.addActionListener(despesasCtrl.inserir);
		btnVoltar.addActionListener(despesasCtrl.voltar);
	}
}