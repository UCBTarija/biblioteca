/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucb.taller2.biblioteca.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ucb.taller2.biblioteca.model.Libro;

/**
 *
 * @author ronal
 */
public class LibroDAO {

    public boolean nuevoLibro(Libro libro) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into libro (codigo,titulo) ");
        sql.append("values(?,?)");

        try (Connection conn = DAOFactory.getConnection();
                PreparedStatement stm = conn.prepareStatement(sql.toString())) {

            stm.setString(1, libro.getCodigo());
            stm.setString(2, libro.getTitulo());
            return stm.execute();
        }
    }

    public Libro getById(String codigo) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select codigo,titulo from libro ");
        sql.append(" where codigo = ?");

        Libro libro = null;

        try (Connection conn = DAOFactory.getConnection();
                PreparedStatement stm = conn.prepareStatement(sql.toString())) {

            stm.setString(1, codigo);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                libro = new Libro();
                libro.setCodigo(rs.getString("codigo"));
                libro.setTitulo(rs.getString("titulo"));
            }
        }

        return libro;
    }

    public boolean modificarLibro(Libro libro) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update libro set");
        sql.append(" titulo = ?");
        sql.append(" where codigo = ?");

        try (Connection conn = DAOFactory.getConnection();
                PreparedStatement stm = conn.prepareStatement(sql.toString())) {

            stm.setString(1, libro.getTitulo());
            stm.setString(2, libro.getCodigo());
            return stm.execute();
        }
    }

    public boolean eliminarLibro(Libro libro) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from libro ");
        sql.append("where codigo = ?");

        try (Connection conn = DAOFactory.getConnection();
                PreparedStatement stm = conn.prepareStatement(sql.toString())) {

            stm.setString(1, libro.getCodigo());
            return stm.execute();
        }
    }

    public List<Libro> getLibros() throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from libro");

        List<Libro> libros = new ArrayList();

        try (Connection conn = DAOFactory.getConnection();
                Statement stm = conn.createStatement()) {

            ResultSet rs = stm.executeQuery(sql.toString());
            Libro libro;
            while (rs.next()) {
                libro = new Libro();
                libro.setCodigo(rs.getString("codigo"));
                libro.setTitulo(rs.getString("titulo"));
                libros.add(libro);
            }
        }
        return libros;
    }
}
