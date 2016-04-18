
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

import java.sql.*;


public class Paginacao extends JFrame {

    JPanel folha = new JPanel();
    JPanel dados = new JPanel();
    JPanel select = new JPanel();
    JPanel botoes = new JPanel();
    JPanel botoes1 = new JPanel();
    JPanel botoes2 = new JPanel();

    Button prim = new Button("Primeiro");
    Button ante = new Button("Anterior");
    Button prox = new Button("Próximo");
    Button ulti = new Button("Último");

    Button novo = new Button("Novo");
    Button atual = new Button("Atualizar");
    Button dele = new Button("Remover");

    Choice lista = new Choice();

    Label indicador = new Label("",Label.RIGHT);
    Label nomeLabel = new Label("Nome:",Label.RIGHT);
    Label emailLabel = new Label("Email:",Label.RIGHT);
    Label irpara = new Label("Ir para:",Label.RIGHT);

    TextField NomeBanco = new TextField("",6);
    TextField EmailBanco = new TextField("",6);

    int comprimentoJanela = 400;
    int alturaJanela = 170;

    static int posicao = 1;
    static int registros = 1;
    static int codigo = 0;

    String driverBanco = "sun.jdbc.odbc.JdbcOdbcDriver";
    String url = "jdbc:odbc:ACCESS";
    String usuario = "usuario";
    String senha = "senha";
    String sql = "SELECT * FROM LISTA ORDER BY NOME";

    static String nome = "";
    static String email = "";

    public Paginacao(){

        dados.setLayout(new GridLayout(2,2));
        dados.add(nomeLabel);
        dados.add(NomeBanco);
        dados.add(emailLabel);
        dados.add(EmailBanco);

        select.setLayout(new FlowLayout(FlowLayout.RIGHT));
        select.add(irpara);
        select.add(lista);

        botoes1.setLayout(new FlowLayout(FlowLayout.CENTER));
        botoes1.add(prim);
        botoes1.add(ante);
        botoes1.add(prox);
        botoes1.add(ulti);

        botoes2.setLayout(new FlowLayout(FlowLayout.CENTER));
        botoes2.add(novo);
        botoes2.add(atual);
        botoes2.add(dele);

        botoes.setLayout(new BorderLayout(5,5));
        botoes.add("North",botoes1);
        botoes.add("South",botoes2);

        folha.setLayout(new BorderLayout());
        folha.add("North",dados);
        folha.add("Center",select);
        folha.add("South",botoes);

        getContentPane().add("North",indicador);
        getContentPane().add("Center",folha);

        setTitle("Paginando em Java");
        setSize(comprimentoJanela, alturaJanela);

        Dimension dimJanela = Toolkit.getDefaultToolkit().getScreenSize();
        int eixoX = Math.round((dimJanela.width / 2) - (comprimentoJanela / 2));
        int eixoY = Math.round((dimJanela.height / 2) - (alturaJanela / 2));

        setLocation(eixoX, eixoY);
        setResizable(false);

        show();

        NavegarRegistros();
    }

    public boolean handleEvent(Event e){
        if(e.id == Event.WINDOW_DESTROY)
          System.exit(0);

        return super.handleEvent(e);
    }

    public boolean action(Event e, Object o){

        if(e.target == prim){
           posicao = 1;
           NavegarRegistros();
        }

        if(e.target == ante){
           if(posicao == 0)
             posicao = 1;
           else
             posicao--;
           NavegarRegistros();
        }

        if(e.target == prox){
           posicao++;
           NavegarRegistros();
        }

        if(e.target == ulti){
           posicao = registros;
           NavegarRegistros();
        }

        if(e.target == novo){
           posicao++;
           if(Novo())
             NavegarRegistros();
        }

        if(e.target == atual){
           if(Atualizar())
             NavegarRegistros();
        }

        if(e.target == dele){
           posicao = 1;
           if(Remover())
             NavegarRegistros();
        }

        return true;
    }

    public static void main(String args[]){
        Paginacao pag = new Paginacao();
        WindowListener wl = new WindowAdapter(){
           public void windowClosing(WindowEvent we){
               System.exit(0);
           }
        };
        pag.addWindowListener(wl);

        try{
          if(System.getProperty("os.name").indexOf("Windows") >=0){
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          } else {
             UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
          }
        }
        catch(Exception e){
             System.err.println("Erro de interfaces GUI. ");
        }
    }

    public void NavegarRegistros(){
        Connection con = null;

        boolean status = true;

        try{
           Class.forName(driverBanco);
        }
        catch(Exception e){
           NomeBanco.setText("O driver do banco não foi encontrado.");
           status = false;
        }

        if(status){
           try{
              con = DriverManager.getConnection(url, usuario, senha);
              Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
              ResultSet rs = stmt.executeQuery(sql);
              Vector vet = new Vector();
              registros = 0;
              while(rs.next()){
                lista.add(rs.getString("NOME"));
                registros++;
              }

              rs.absolute(posicao);
              prim.setEnabled(true);
              ante.setEnabled(true);
              prox.setEnabled(true);
              ulti.setEnabled(true);

              if(rs.isFirst()){
                posicao = 1;
                rs.absolute(posicao);
                prim.setEnabled(false);
                ante.setEnabled(false);
              }
              if(rs.isLast()){
                rs.absolute(posicao);
                prox.setEnabled(false);
                ulti.setEnabled(false);
              }

              indicador.setText(posicao + " de " + registros);

              nome = rs.getString("NOME");
              email = rs.getString("EMAIL");
              codigo = rs.getInt("CODIGO");

              NomeBanco.setText(nome);
              EmailBanco.setText(email);
              con.close();
           }
           catch(SQLException e){
              NomeBanco.setText(e.toString());
           }
        }
    }

    public boolean Novo(){
        String n = NomeBanco.getText();
        String e = EmailBanco.getText();

        String sql = "INSERT INTO LISTA(NOME, EMAIL) VALUES('" + n + "','" + e + "')";

        try{
           Class.forName(driverBanco);
        }
        catch(Exception e1){
           NomeBanco.setText(e1.toString());
        }

        try{
           Connection con = DriverManager.getConnection(url, usuario, senha);
           Statement stmt =  con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
           int reg = stmt.executeUpdate(sql);
        }
        catch(SQLException e2){
           NomeBanco.setText(e2.toString());
           return false;
        }
        return true;
    }

    public boolean Atualizar(){
        String n = NomeBanco.getText();
        String e = EmailBanco.getText();

        String sql = "";
        sql += "UPDATE LISTA SET";
        sql += " NOME = '" + n + "',";
        sql += " EMAIL = '" +  e + "'";
        sql += " WHERE CODIGO = " + codigo;

        try{
           Class.forName(driverBanco);
        }
        catch(Exception e1){
           NomeBanco.setText(e1.toString());
        }

        try{
           Connection con = DriverManager.getConnection(url, usuario, senha);
           Statement stmt =  con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
           int reg = stmt.executeUpdate(sql);
        }
        catch(SQLException e2){
           NomeBanco.setText(e2.toString());
           return false;
        }
        return true;
    }

    public boolean Remover(){
        String n = NomeBanco.getText();
        String e = EmailBanco.getText();

        String sql = "";
        sql += "DELETE * FROM LISTA WHERE CODIGO = " + codigo;

        try{
           Class.forName(driverBanco);
        }
        catch(Exception e1){
           NomeBanco.setText(e1.toString());
        }

        try{
           Connection con = DriverManager.getConnection(url, usuario, senha);
           Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
           int reg = stmt.executeUpdate(sql);
        }
        catch(SQLException e2){
           NomeBanco.setText(e2.toString());
           return false;
        }
        return true;
    }
}
