package primeiro_projeto_p2;

import primeiro_projeto_p2.controller.ClienteController;

public class Main {
    public static void main(String[] args) {
        ClienteController clienteController = new ClienteController();
        boolean resultado = clienteController.pesquisar();
        if (resultado) {
            System.out.println("Pesquisa realizada com sucesso!");
        } else {
            System.out.println("Erro ao realizar pesquisa!");
        }
        // Connection conn = null;
        // try {
        // // Pegou a conexão com o banco de dados
        // conn = Conexao.getConnection();
        // System.out.println("Conectado com sucesso!");
        // // Criou o statment, que é o objeto que representa o banco de dados dentro do
        // // Java
        // Statement stmt = conn.createStatement();
        // // Executou um comando SQL
        // ResultSet rs = stmt.executeQuery("SELECT * from produtor");
        // // Vai ler o retorno do banco de dados
        // if (rs.next()) {
        // System.out.println("Dados do banco: " + rs.getString(1) + " " +
        // rs.getString(5));
        // }

        // // int variavel = stmt.executeUpdate("AQUI VAI UM COMANDO DE INSERT, UPDATE
        // OU
        // // DELETE");
        // // if (variavel == 1) {
        // // System.out.println("Comando executado com sucesso!");
        // // } else {
        // // System.out.println("Erro ao executar comando!");
        // // }
        // rs.close();
        // stmt.close();

        // } catch (Exception e) {
        // e.printStackTrace();
        // } finally {
        // Conexao.fecharConexao();
        // }
    }
}