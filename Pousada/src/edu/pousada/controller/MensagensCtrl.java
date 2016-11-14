package edu.pousada.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MensagensCtrl {

	public MensagensCtrl() {}

	// MENSAGENS //////////////////////////////


	public static boolean msg( String tipo, String mensagem ) {

		switch ( tipo ) {

		case "erroCampo":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nCampo(s) não preenchido(s)."
							+ "\nPor favor, digite o dado solicitado por cada campo.", 
							"Erro", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( LogonCtrl.getCaminho() + "/icons/write.png" ));
			return false;

		case "erroVazio":
			JOptionPane.showMessageDialog(null, 
					"CAMPOS NÃO PREENCHIDOS.\n\nPor favor, verifique sua digitação \ne digite o Usuário e a Senha.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/error.png" ));
			return false;

		case "erroLinha":
			JOptionPane.showMessageDialog(null, 
					"Por favor, selecione uma Reserva para cancelar.", 
					"Reserva não selecionada", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/error.png" ));
			break;

		case "erroDataInicial":
			JOptionPane.showMessageDialog(null, 
					"ERRO nas datas!\n\n" 
							+ "Por favor, digite uma data de Chegada\nigual ou superior à data de atual.", 
							"Erro", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( LogonCtrl.getCaminho() + "/icons/error.png" ));
			return false;

		case "erroDataFinal":
			JOptionPane.showMessageDialog(null, 
					"ERRO nas datas!\n\n" 
							+ "Por favor, digite uma data de Chegada\nanterior ou igual à data de Partida.", 
							"Erro", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( LogonCtrl.getCaminho() + "/icons/error.png" ));
			return false;

		case "erroSelecao":
			JOptionPane.showMessageDialog(null, 
					"Seleção inválida!\n\n" 
							+ "Por favor, selecione uma categoria.", 
							"Erro", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( LogonCtrl.getCaminho() + "/icons/error.png" ));
			return false;
			
		case "erroCadastro":
			Object[] inicio = { "Seu Cadastro", "Sua Reserva" };
			int iniciar = JOptionPane.showOptionDialog(null, 
					"Seja bem-vindo " + mensagem + "\nO que você gostaria de realizar neste momento?", 
					"Sem Cadastro",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/quest.png" ), inicio, inicio[1]);
			if (iniciar == 0) {
				return true;
			} else {
				return false;
			}
			
		case "errorsession":
			JOptionPane.showMessageDialog(null, 
					"ACESSO NEGADO!\n\nPor favor, solicite a autorização de um administrador.", 
					"Bloqueado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/warning.png"));
			break;
			
		case "erroSenha":
			JOptionPane.showMessageDialog(null, 
					"USUÁRIO OU SENHA ERRADOS.\n\nO usuário '" + mensagem + "' ou sua senha não conferem!\n"
							+ "Verifique sua digitação e tente novamente.",
							"Erro", 
							JOptionPane.PLAIN_MESSAGE, 
							new ImageIcon( LogonCtrl.getCaminho() + "/icons/error.png" ));
			return false;
			
		case "erroSenhaDifere":
			JOptionPane.showMessageDialog(null, 
					"CAMPOS SENHA DIFEREM.\n\nA senha e a sua confirmação estão diferentes!\n"
							+ "Verifique sua digitação e tente novamente.",
							"Erro", 
							JOptionPane.PLAIN_MESSAGE, 
							new ImageIcon( LogonCtrl.getCaminho() + "/icons/error.png" ));
			return false;

		case "erroChale":
			JOptionPane.showMessageDialog(null, 
					"INDISPONÍVEL\n\nOs Chalés da categoria " + mensagem
					+ "\nnão estão disponíveis neste período.\n\nPor favor, selecione um período ou categoria diferente.", 
					"Atenção", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/info.png" ));
			return false;

		case "reservaAtiva":
			JOptionPane.showMessageDialog(null, 
					"A Reserva " + mensagem 
					+ " não pode ser cancelada pois está ativa.\n"
					+ "É necessário a sua desativação para efetivar seu cancelamento", 
					"Reserva Ativa", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/alert.png" ));
			return false;

		case "autorizado":
			JOptionPane.showMessageDialog(null, 
					"Seja bem-vindo, " + mensagem 
					+ "!\n\nDúvidas, elogios e sugestões "
					+ "\npodem ser enviadas através da guia Contato.", 
					"Acesso", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/user.png" ));
			return true;
			
		case "sucesso":
			JOptionPane.showMessageDialog(null, 
					"Reserva(s) " + mensagem + " cancelada(s).", 
					"Cancelamento Efetuado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/confirm.png" ));
			break;
			
		case "mensagemEnviada":

			JOptionPane.showMessageDialog(null, 
					"Sua mensagem já foi recebida e logo entraremos em contato!", 
					"Mensagem Enviada", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/mail.png" ));
			return true;
			
		case "adicionaCadastro":
			JOptionPane.showMessageDialog(null, 
					"O cadastro de " + mensagem 
					+ " foi realizado com sucesso.", 
					"Cadastro Realizado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/user.png" ));
			return true;
			
		case "atualizaCadastro":
			JOptionPane.showMessageDialog(null, 
					"Feito! A atualização do cadastro de " + mensagem 
					+ " \nfoi realizado com sucesso.", 
					"Cadastro Atualizado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/user.png" ));
			return true;

		case "limpar":
			Object[] l = { "Confirmar", "Cancelar" };
			int limpar = JOptionPane.showOptionDialog(null, mensagem +
					"Deseja cancelar todas as Reservas?",
					"Cancelar Reservas", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/alert.png" ), l, l[1]);
			if (limpar == 0) {
				return true;
			} else {
				return false;
			}

		case "cancelar":
			Object[] opt = { "Confirmar", "Cancelar" };
			int retirar = JOptionPane.showOptionDialog(null, 
					"Deseja cancelar a Reserva " + mensagem + "?",
					"Cancelar Reserva", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/alert.png" ), opt, opt[1]);
			if (retirar == 0) {
				return true;
			} else {
				return false;
			}

		case "excluirReservas":
			Object[] excluir = { "Cancelar Reservas", "Apenas Sair" };  
			int fechar = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nExistem reservas não finalizadas" 
					+ " no sistema.\nDeseja cancelar a Reserva(s)?",
					"Atenção", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/alert.png" ), excluir, excluir[1] );
			if ( fechar != 0 ) {
				return true;
			} else {
				return false;
			}

		case "reservaCancelar":
			int cancelar = JOptionPane.showOptionDialog(null, 
					"Deseja editar ou excluir a Reserva " + mensagem + "?",
					"Editar ou Excluir Reserva", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/alert.png" ), 
					new String[]{"Excluir", "Editar","Cancelar"}, "Cancelar");
			if(cancelar == JOptionPane.YES_OPTION) {
				return true;
			}
			else if(cancelar == JOptionPane.NO_OPTION) {
				msg( "construir", "" );
				return false;
			}
			else if(cancelar == JOptionPane.CANCEL_OPTION) {
				return false;
			}
			break;

		case "construir":
			JOptionPane.showMessageDialog(null, 
					mensagem + 
					"\nEm construção!\n\nEsta função ainda não foi implementada.", 
					"Sem implementação", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/builder.png" ));
			break;
				
		case "inicio":
			JOptionPane.showMessageDialog(null, 
					"Este é o primeiro acesso ao sistema "
					+ "\ne um " + mensagem + " foi criado.\n\nUsuário: admin\nSenha: admin", 
					"Primeiro Acesso", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/adm.png" ));
			return true;

		case "sair":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int sair = JOptionPane.showOptionDialog( null, "ATENÇÃO!\n\nChamada para o fechamento" 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon( LogonCtrl.getCaminho() + "/icons/warning.png" ), exit, exit[1] );
			if ( sair == 0 ) {
				System.exit(0);
				return true;
			} else {
				return false;
			}

		default:
			JOptionPane.showMessageDialog(null, 
					"ERRO de sistema!\n\n" 
							+ mensagem, 
							"Erro", 
							JOptionPane.PLAIN_MESSAGE,
							new ImageIcon( LogonCtrl.getCaminho() + "/icons/error.png"));
		}
		return false;
	}
}