/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 07/05/2016
 */

package boundary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controller.EnderecoController;

public class FrmEndereco extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel painel;
	private JSeparator linha;
	private JLabel lblId;
	private JLabel lblLogo;
	private JLabel lblTituloPagina; 
	private JLabel lblEndereco;
	private JLabel lblLogradouro;
	private JLabel lblCep;
	private JLabel lblComplemento;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblEstado; 
	private JTextField txtEndereco;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField ftxtCep;
	private JComboBox<String> cboTipoEndereco;
	private JComboBox<String> cboEstado; 
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JButton btnVoltar;
	private JButton btnCancelar;
	private JTextField txtTipoEndereco;
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEndereco frame = new FrmEndereco(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FrmEndereco( String cpf ) throws ParseException {
		
		setTitle("Cadastro de Endereços");
		setName("Endereco");
		setResizable(false);
		setAlwaysOnTop (true);
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 480);
		setLocationRelativeTo(null);
		
		painel = new JPanel();
		setContentPane( painel );
		painel.setLayout(null);
		
		lblLogo = new JLabel("LIVRARIA DIGITAL");
		lblLogo.setForeground(new Color(50, 205, 50));
		lblLogo.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		lblLogo.setBounds(36, 36, 314, 45);
		painel.add(lblLogo);
		
		lblTituloPagina = new JLabel("Endereços");
		lblTituloPagina.setEnabled(false);
		lblTituloPagina.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblTituloPagina.setBounds(656, 54, 94, 16);
		painel.add(lblTituloPagina);
		
		linha = new JSeparator();
		linha.setBounds(6, 71, 756, 12);
		painel.add(linha);
		
		lblId = new JLabel();
		lblId.setVisible(false);
		lblId.setEnabled(false);
		lblId.setBounds(491, 151, 219, 16);
		painel.add(lblId);
		
		lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(61, 151, 94, 16);
		painel.add(lblEndereco);
		
		cboTipoEndereco = new JComboBox<String>();
		cboTipoEndereco.setBounds(164, 146, 219, 27);
		painel.add(cboTipoEndereco);
		
		txtTipoEndereco = new JTextField(10);
		txtTipoEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTipoEndereco.setBounds(167, 145, 219, 28);
		painel.add(txtTipoEndereco);
		
		lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(61, 190, 94, 16);
		painel.add(lblLogradouro);
		
		txtEndereco = new JTextField(20);
		txtEndereco.setEditable(true);
		txtEndereco.setBounds(164, 184, 546, 28);
		painel.add(txtEndereco);
	          
		lblCep = new JLabel("CEP");
		lblCep.setBounds(61, 310, 83, 16);
		painel.add(lblCep);
		
		lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(61, 230, 94, 16);
		painel.add(lblComplemento);	
		
		txtComplemento = new JTextField(20);
		txtComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
		txtComplemento.setBounds(164, 224, 219, 28);
		txtComplemento.setColumns(10);
		painel.add(txtComplemento);
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(429, 230, 50, 16);
		painel.add(lblBairro);
		
		txtBairro = new JTextField(20);
		txtBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBairro.setBounds(491, 224, 219, 28);
		txtBairro.setColumns(10);
		painel.add(txtBairro);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(61, 270, 94, 16);
		painel.add(lblCidade);
		
		txtCidade = new JTextField(20);
		txtCidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtCidade.setBounds(164, 264, 219, 28);
		painel.add(txtCidade);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(429, 270, 50, 16);
		painel.add(lblEstado);
		
		cboEstado = new JComboBox<String>();
		cboEstado.setBounds(491, 265, 219, 27);
		painel.add(cboEstado);
		
		ftxtCep = new JTextField(20);
		ftxtCep.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtCep.setBounds(164, 304, 219, 28);
		painel.add(ftxtCep);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(422, 408, 117, 29);
		painel.add(btnCancelar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(36, 408, 117, 29);
		painel.add(btnLimpar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(293, 408, 117, 29);
		painel.add(btnExcluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(164, 408, 117, 29);
		painel.add(btnEditar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(164, 408, 117, 29);
		painel.add(btnSalvar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(593, 408, 117, 29);
		painel.add(btnVoltar);
		
		
		EnderecoController enderecoCtrl = new EnderecoController( 
				this, 
				painel, 
				lblId, 
				txtTipoEndereco, 
				txtEndereco, 
				txtComplemento, 
				txtBairro, 
				txtCidade, 
				ftxtCep, 
				cboTipoEndereco, 
				cboEstado, 
				btnLimpar, 
				btnEditar, 
				btnExcluir, 
				btnSalvar, 
				btnCancelar, 
				btnVoltar 				 
				 );
		
		cboTipoEndereco.addKeyListener(enderecoCtrl.tecla);
		btnExcluir.addActionListener(enderecoCtrl.excluir);
		btnEditar.addActionListener(enderecoCtrl.editar);
		btnSalvar.addActionListener(enderecoCtrl.salvar);
		btnLimpar.addActionListener(enderecoCtrl.limpar);
		btnCancelar.addActionListener(enderecoCtrl.cancelar);
		btnVoltar.addActionListener(enderecoCtrl.janelas);
	}
}
