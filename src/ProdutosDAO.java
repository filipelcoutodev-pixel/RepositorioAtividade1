/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    //Método Cadastrar Produtos
    public void cadastrarProduto(ProdutosDTO produto) {
        Connection conn = new conectaDAO().connectDB();
        PreparedStatement pstm = null;

        try {
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getValor());
            pstm.setString(3, produto.getStatus());

            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    //Método Listar produtos
    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        Connection conn = new conectaDAO().connectDB();
        PreparedStatement pstm;
        ResultSet rs;

        try {
            String sql = "SELECT * FROM produtos";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return lista;
    }

   //Método Vender produto
    public void venderProduto(int idProduto) {
        Connection conn = new conectaDAO().connectDB();
        try {
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Produto vendido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao vender produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Método Listar produtos vendidos
    public List<ProdutosDTO> listarProdutosVendidos() {
        List<ProdutosDTO> vendidos = new ArrayList<>();
        Connection conn = new conectaDAO().connectDB();
        try {
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                vendidos.add(p);
            }

            rs.close();
            pstm.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos vendidos: " + e.getMessage());
            e.printStackTrace();
        }
        return vendidos;
    }

}
