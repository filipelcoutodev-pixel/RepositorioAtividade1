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


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
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

    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

