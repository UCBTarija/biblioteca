/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
