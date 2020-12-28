package com.business;

import com.dao.UserDAO;
import com.document.dto.UserDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AccountBS {
    
    UserDAO userDAO = new UserDAO();
    
    public UserDTO getUser(int id) {
        try {
            return userDAO.getUser(id);
        } catch (Exception ex) {
            Logger.getLogger(AccountBS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public UserDTO getUser(String username, String password) {
        try {
            return userDAO.getUser(username, password);
        } catch (Exception ex) {
            Logger.getLogger(AccountBS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean save(String username, String password, String name, boolean isAdmin) {
        try {
            UserDTO user = new UserDTO();
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setIsAdmin(isAdmin);
            return userDAO.save(user);
        } catch (Exception ex) {
            Logger.getLogger(AccountBS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean update(int id, String username, String password, String name, boolean isAdmin) {
        try {
            UserDTO user = new UserDTO();
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setIsAdmin(isAdmin);
            user.setId(id);
            return userDAO.update(user);
        } catch (Exception ex) {
            Logger.getLogger(AccountBS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
