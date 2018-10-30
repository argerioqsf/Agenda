/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Calendar;

/**
 *
 * @author aluno
 */
public class JDBCExemplo {
    
    static Date Data(int dia ,int mes, int ano){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        return new Date(calendar.getTimeInMillis());
    }
    
    static void remove(Connection con, int id) throws SQLException{
        String sql = "delete from contatos where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
    }
    
    static void insert(Connection con) throws SQLException{
        String sql = "insert into contatos (nome,endereco,email,data_nascimento) values(?,?,?,?)";
        PreparedStatement instrucao = con.prepareStatement(sql);
        instrucao.setString(1, "Argério");
        instrucao.setString(2, "Rua Euclides Rodrigues");
        instrucao.setString(3, "argerioaf@gmail.com");
        instrucao.setDate(4,Data(14, 05, 1998));
        instrucao.execute();
    }
    
    static void update(Connection con,String id) throws SQLException{
            String sql = "update contatos set nome=?,endereco=?,email=?,data_nascimento=? where id = "+id;
            PreparedStatement instrucao = con.prepareStatement(sql);
            instrucao.setString(1, "Argério 2");
            instrucao.setString(2, "Rua Euclides Rodrigues, 388");
            instrucao.setString(3, "argerioaf3@gmail.com");
            instrucao.setDate(4,Data(14, 05, 1998));
            instrucao.execute();
            
            
    }
    
    static void Select(Connection conn, String tabela) throws SQLException{
            String sql = "Select * from " +tabela;
            PreparedStatement instrucao = conn.prepareStatement(sql);
            ResultSet rs = instrucao.executeQuery();
            while (rs.next()) {
                System.out.println("id:" + rs.getString("id"));
                System.out.println("nome:" + rs.getString("nome"));
                System.out.println("endereço:" + rs.getString("endereco"));
                System.out.println("data de nascimento:" + rs.getString("data_nascimento"));
            }
    }
    
    public static void main(String[] args){
        String conexao = "valor";
        
        try{
            Connection mysql = DriverManager.getConnection(
                    "jdbc:mysql://localhost/agenda",
                    "root",
                    "1q"
            );
            System.out.println("Conectado!");
            //insert(mysql);
            //remove(mysql,1);
            //update(mysql,"3");
            //Select(mysql, "contatos");
        } catch(SQLException e){
            System.out.println("Ocorreu um erro!");
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        }
    
}
