package ucb.taller2.biblioteca.model;

/**
 *
 * @author ronal
 */
public class Authenticator {
    
    public String authenticate(String username, String password){
           
        if("admin".equalsIgnoreCase(username) 
                && "admin".equalsIgnoreCase(password)){
            return "success";
        } else {
            return "error";
        }
    }
}
