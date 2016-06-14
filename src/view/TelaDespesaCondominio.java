/**
 * @author Fernando Moraes Oliveira
 * MatÃ©ria 4716 - Engenharia de Software 2
 * 3Âº ADS - Tarde
 * Iniciado em 04/05/2016
 */

package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;

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

import controller.DespesasCondominioController;

public class TelaDespesaCondominio extends JFrame {

	private static final long serialVersionUID = 6992276966995839544L;
	
	private JPanel painel;
	private JScrollPane spDespesas;
	private JSeparator linha;
	private JLabel lblLogo;
	private JLabel lblPagina;
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
	private JComboBox<String> cboDespesa;
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
					TelaDespesaCondominio frame = new TelaDespesaCondominio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaDespesaCondominio() throws ParseException {
		
<<<<<<< HEAD
		setTitle("Despesas do Condomínio");
		setName("CondDesp");
=======
		setTitle("Despesas do CondomÃ­nio");
		setName("AptoDespesa");
>>>>>>> eac9c82e59a9437b048973dcdf6a18d4f970775b
		setResizable(false);
		setSize( 1024, 710 );
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		
		painel = new JPanel();
		setContentPane( painel );
		painel.setName("Despesa");
		painel.setLayout(null);
		
		lblLogo = new JLabel("CONDOMÃ�NIO DIGITAL");
		lblLogo.setForeground(Color.GRAY);
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(48, 36, 410, 45);
		painel.add(lblLogo);
		
		lblPagina = new JLabel("Cadastro de Despesas do CondomÃ­nio");
		lblPagina.setEnabled(false);
		lblPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPagina.setBounds(656, 76, 316, 22);
		painel.add(lblPagina);
		
		linha = new JSeparator();
		linha.setBounds(6, 93, 1012, 12);
		painel.add(linha);
		
		lblDtVenc = new JLabel("Data de Vencimento");
		lblDtVenc.setBounds(48, 151, 128, 16);
		painel.add(lblDtVenc);
		
		maskData = new MaskFormatter("##/##/####");
		ftxtDtVenc = new JFormattedTextField(maskData);
		ftxtDtVenc.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDtVenc.setBounds(187, 145, 142, 28);
		ftxtDtVenc.setColumns(10);
		painel.add(ftxtDtVenc);
		
		lblId = new JLabel("CÃ³digo");
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
		
		maskData = new MaskFormatter("##/##/####");
		ftxtDtReg = new JFormattedTextField(maskData);
		ftxtDtReg.setEnabled(false);
		ftxtDtReg.setEditable(false);
		ftxtDtReg.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDtReg.setBounds(834, 179, 142, 28);
		ftxtDtReg.setColumns(10);
		painel.add(ftxtDtReg);
		
		lblDtAlt = new JLabel("Data de AlteraÃ§Ã£o");
		lblDtAlt.setBounds(719, 219, 117, 16);
		painel.add(lblDtAlt);
		
		maskData = new MaskFormatter("##/##/####");
		ftxtDtAlt = new JFormattedTextField(maskData);
		ftxtDtAlt.setEnabled(false);
		ftxtDtAlt.setEditable(false);
		ftxtDtAlt.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDtAlt.setBounds(834, 213, 142, 28);
		ftxtDtAlt.setColumns(10);
		painel.add(ftxtDtAlt);
		
		lblReferencia = new JLabel("MÃªs de ReferÃªncia");
		lblReferencia.setBounds(59, 185, 117, 16);
		painel.add(lblReferencia);
		
		cboReferencia = new JComboBox<String>();
		cboReferencia.setBounds(187, 179, 160, 28);
		painel.add(cboReferencia);
		
		txtReferencia = new JTextField(20);
		txtReferencia.setBounds(187, 179, 142, 28);
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
		txtDespesa.setToolTipText("Digite aqui o termo que deseja pesquisarâ€¦");
		txtDespesa.setBounds(187, 247, 254, 28);
		painel.add(txtDespesa);
		
		cboDespesa = new JComboBox<String>();
		cboDespesa.setBounds(187, 247, 254, 28);
		painel.add(cboDespesa);
		
		btnPesquisar = new JButton("Pesquisa");
		btnPesquisar.setBounds(453, 247, 95, 29);
		painel.add(btnPesquisar);
		
		spDespesas = new JScrollPane();
		spDespesas.setToolTipText("Selecione uma despesa para alterar"
<<<<<<< HEAD
				+ " ou clique 2 vezes para excluir…");
		spDespesas.setBounds(48, 298, 928, 241);
=======
				+ " ou clique 2 vezes para excluirâ€¦");
		spDespesas.setBounds(48, 298, 928, 250);
>>>>>>> eac9c82e59a9437b048973dcdf6a18d4f970775b
		painel.add(spDespesas);
		
		tabDespesa = new JTable();
		tabDespesa.setToolTipText("clique 2 vezes para excluirâ€¦");
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
		
		
		DespesasCondominioController despesasCtrl = new DespesasCondominioController(
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
				cboDespesa, 
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
		cboDespesa.addActionListener(despesasCtrl.adicionar);
		btnPesquisar.addActionListener(despesasCtrl.pesquisar);
		btnLimpar.addActionListener(despesasCtrl.limpar);
		btnEditar.addActionListener(despesasCtrl.editar);
		btnExcluir.addActionListener(despesasCtrl.excluir);
		btnCancelar.addActionListener(despesasCtrl.cancelar);
		btnSalvar.addActionListener(despesasCtrl.inserir);
		btnVoltar.addActionListener(despesasCtrl.voltar);
	}
}