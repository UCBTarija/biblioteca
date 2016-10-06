/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucb.taller2.biblioteca.model.dao;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author ronal
 */
public class DAOFactory {

    public static Connection getConnection() throws Exception{
       
        // Inicializa un objeto de conexto
        Context ctx = new InitialContext();

        // Utiliza el objeto de contexto para buscar el recurso DataSource
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/Biblioteca");
        
        // Devuelve la conexión a la base de datos
        return ds.getConnection();
    }
    
    public static LibroDAO getLibroDAO(){
        // Devuelve una instancia del DAO específico
        return new LibroDAO();
    }
}
