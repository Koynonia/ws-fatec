/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 07/05/2016
 */

package boundary;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;

@SuppressWarnings("serial")
public class FrmCliente extends JFrame {

	private JPanel painel;
	private JSeparator linha;
	private JLabel lblId;
	private JLabel lblPesquisar;
	private JLabel lblLogo;
	private JLabel lblTituloPagina; 
	private JLabel lblImagem;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblTipoEndereco;
	private JLabel lblEmail;
	private JLabel lblDtNac;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblCpf;
	private JLabel lblEndereco;
	private JLabel lblSenha2;
	private JLabel lblCep;
	private JLabel lblComplemento;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblEstado; 
	private JTextField txtPesquisar;
	private JTextField txtUsuario;
	private JTextField ftxtCpf;
	private JTextField txtEndereco;
	private JTextField ftxtTelefone;
	private JTextField txtCidade;
	private JTextField txtNome;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField ftxtEmail;
	private JTextField ftxtCep;
	private JFormattedTextField ftxtDtNasc;
	private JPasswordField pwdSenha;
	private JPasswordField pwdSenha2;
	private JComboBox<String> cboTipoEndereco;
	private JComboBox<String> cboEstado; 
	private JButton btnPesquisar;
	private JButton btnLimpar;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JButton btnVoltar;
	private JButton btnImagem;
	private MaskFormatter maskData;
	private JButton btnPedidos;
	
	
	public FrmCliente( String cpf ) throws ParseException {
		
		setTitle("Cadastro de Cliente");
		setName("Cliente");
		setResizable(false);
		setSize( 1024, 710 );
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		painel = new JPanel();
		setContentPane( painel );
		painel.setLayout(null);
		
		lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(new Color(50, 205, 50));
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(36, 36, 314, 45);
		painel.add(lblLogo);
		
		linha = new JSeparator();
		linha.setBounds(6, 93, 1012, 12);
		painel.add(linha);
			
		lblPesquisar = new JLabel("Pesquisa");
		lblPesquisar.setBounds(429, 29, 66, 16);
		painel.add(lblPesquisar);
		
		txtPesquisar = new JTextField(20);
		txtPesquisar.setToolTipText("Digite aqui o termo que deseja pesquisar…");
		txtPesquisar.setBounds(427, 44, 254, 28);
		painel.add(txtPesquisar);
		
		btnPesquisar = new JButton("Ir");
		btnPesquisar.setBounds(693, 44, 75, 29);
		painel.add(btnPesquisar);
		
		lblId = new JLabel();
		lblId.setVisible(false);
		lblId.setEnabled(false);
		lblId.setBounds(758, 146, 219, 16);
		painel.add(lblId);
		
		lblTituloPagina = new JLabel("Página do Cliente");
		lblTituloPagina.setEnabled(false);
		lblTituloPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblTituloPagina.setBounds(830, 76, 147, 21);
		painel.add(lblTituloPagina);
		
		lblImagem = new JLabel();
		lblImagem.setName( "lblCapa" );
		lblImagem.setToolTipText("Capa");
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.setBorder(new LineBorder(new Color(128, 128, 128), 0, true));
		lblImagem.setBounds(44, 140, 163, 155);
		painel.add(lblImagem);
		
		txtUsuario = new JTextField(20);
		txtUsuario.setBounds(431, 140, 219, 28);
		painel.add(txtUsuario);
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(328, 146, 94, 16);
		painel.add(lblUsuario);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(328, 186, 94, 16);
		painel.add(lblSenha);
		
		pwdSenha = new JPasswordField(20);
		pwdSenha.setBounds(431, 180, 219, 28);
		painel.add(pwdSenha);
		
		lblSenha2 = new JLabel("Repetir");
		lblSenha2.setBounds(328, 228, 66, 16);
		painel.add(lblSenha2);
		
		pwdSenha2 = new JPasswordField(20);
		pwdSenha2.setBounds(431, 222, 219, 28);
		painel.add(pwdSenha2);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(328, 270, 94, 16);
		painel.add(lblEmail);
		
		lblDtNac = new JLabel("Data de Nascimento");
		lblDtNac.setBounds(696, 270, 128, 16);
		painel.add(lblDtNac);
		
		maskData = new MaskFormatter("##/##/####");
		ftxtDtNasc = new JFormattedTextField(maskData);
		ftxtDtNasc.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDtNasc.setBounds(835, 264, 142, 28);
		ftxtDtNasc.setColumns(10);
		painel.add(ftxtDtNasc);	
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(328, 307, 94, 16);
		painel.add(lblNome);
		
		txtNome = new JTextField(20);
		txtNome.setBounds(431, 301, 546, 28);
		painel.add(txtNome);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(328, 347, 94, 16);
		painel.add(lblCpf);
		
		ftxtCpf = new JTextField(20);
		ftxtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtCpf.setBounds(431, 341, 219, 28);
		painel.add(ftxtCpf);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(696, 347, 66, 16);
		painel.add(lblTelefone);
		
		ftxtTelefone = new JTextField(20);
		ftxtTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtTelefone.setBounds(758, 341, 219, 28);
		painel.add(ftxtTelefone);
		
		lblTipoEndereco = new JLabel("Selecione");
		lblTipoEndereco.setBounds(328, 387, 94, 16);
		painel.add(lblTipoEndereco);
		
		cboTipoEndereco = new JComboBox<String>();
		cboTipoEndereco.setBounds(431, 382, 219, 27);
		painel.add(cboTipoEndereco);
		
		lblEndereco = new JLabel("Logradouro");
		lblEndereco.setBounds(328, 426, 94, 16);
		painel.add(lblEndereco);
		
		txtEndereco = new JTextField(20);
		txtEndereco.setEditable(true);
		txtEndereco.setBounds(431, 420, 546, 28);
		painel.add(txtEndereco);
		
		lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(328, 466, 94, 16);
		painel.add(lblComplemento);	
		
		txtComplemento = new JTextField(20);
		txtComplemento.setHorizontalAlignment(SwingConstants.CENTER);
		txtComplemento.setBounds(431, 460, 219, 28);
		txtComplemento.setColumns(10);
		painel.add(txtComplemento);
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(696, 466, 50, 16);
		painel.add(lblBairro);
		
		txtBairro = new JTextField(20);
		txtBairro.setHorizontalAlignment(SwingConstants.CENTER);
		txtBairro.setBounds(758, 460, 219, 28);
		txtBairro.setColumns(10);
		painel.add(txtBairro);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(328, 506, 94, 16);
		painel.add(lblCidade);
		
		txtCidade = new JTextField(20);
		txtCidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtCidade.setBounds(431, 500, 219, 28);
		painel.add(txtCidade);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(696, 506, 50, 16);
		painel.add(lblEstado);
		
		cboEstado = new JComboBox<String>();
		cboEstado.setBounds(758, 501, 219, 27);
		painel.add(cboEstado);
		
		lblCep = new JLabel("CEP");
		lblCep.setBounds(328, 546, 83, 16);
		painel.add(lblCep);
		
		ftxtCep = new JTextField(20);
		ftxtCep.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtCep.setBounds(431, 540, 219, 28);
		painel.add(ftxtCep);
		
		btnImagem = new JButton("Carregar Imagem");
		btnImagem.setBounds(44, 307, 163, 29);
		painel.add(btnImagem);
		
		btnPedidos = new JButton("Meus Pedidos");
		btnPedidos.setBounds(67, 420, 117, 29);
		painel.add(btnPedidos);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(689, 634, 117, 29);
		painel.add(btnCancelar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(303, 634, 117, 29);
		painel.add(btnLimpar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(560, 634, 117, 29);
		painel.add(btnExcluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(431, 634, 117, 29);
		painel.add(btnEditar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(431, 634, 117, 29);
		painel.add(btnSalvar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(860, 634, 117, 29);
		painel.add(btnVoltar);
		
		ftxtEmail = new JTextField(20);
		ftxtEmail.setEditable(true);
		ftxtEmail.setBounds(431, 264, 219, 28);
		painel.add(ftxtEmail);
		
		
		ClienteController clienteCtrl = new ClienteController( 
				this, 
				painel, 
				cpf, 
				lblId, 
				lblPesquisar, 
				lblTipoEndereco, 
				lblImagem, 
				lblSenha, 
				lblSenha2, 
				txtPesquisar,
				txtUsuario, 
				txtNome, 
				txtEndereco, 
				txtComplemento, 
				txtBairro, 
				txtCidade, 
				ftxtEmail, 
				ftxtCpf, 
				ftxtTelefone, 
				ftxtCep, 
				ftxtDtNasc, 
				pwdSenha, 
				pwdSenha2, 
				cboTipoEndereco, 
				cboEstado, 
				btnPesquisar, 
				btnImagem, 
				btnPedidos, 
				btnLimpar, 
				btnEditar, 
				btnExcluir, 
				btnSalvar, 
				btnCancelar, 
				btnVoltar 				 
				 );
		
		
		txtPesquisar.addActionListener(clienteCtrl.pesquisar);
		btnPesquisar.addActionListener(clienteCtrl.pesquisar);
		txtPesquisar.addKeyListener(clienteCtrl.tecla);
		txtUsuario.addKeyListener(clienteCtrl.tecla);
		pwdSenha2.addFocusListener(clienteCtrl.foco);
		cboTipoEndereco.addActionListener(clienteCtrl.adicionar);
		btnImagem.addActionListener(clienteCtrl.carregarImagem);
		btnPedidos.addActionListener(clienteCtrl.pedidos);
		btnExcluir.addActionListener(clienteCtrl.excluir);
		btnEditar.addActionListener(clienteCtrl.editar);
		btnSalvar.addActionListener(clienteCtrl.salvar);
		btnLimpar.addActionListener(clienteCtrl.limpar);
		btnCancelar.addActionListener(clienteCtrl.cancelar);
		btnVoltar.addActionListener(clienteCtrl.janelas);
	}
}