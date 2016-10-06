package ucb.taller2.biblioteca.model;

import java.util.List;
import ucb.taller2.biblioteca.model.dao.DAOFactory;

/**
 *
 * @author admin
 */
public class Libro {
    private String codigo;
    private String titulo;

    public static Libro getById(String codigo) throws Exception{
        return DAOFactory.getLibroDAO().getById(codigo);
    }
    
    public static List<Libro> getLibros() throws Exception{
        return DAOFactory.getLibroDAO().getLibros();
    }
    
    public boolean guardar() throws Exception{
        return DAOFactory.getLibroDAO().nuevoLibro(this);
    }
    
    public boolean eliminar() throws Exception{
        return DAOFactory.getLibroDAO().eliminarLibro(this);
    }
    
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
