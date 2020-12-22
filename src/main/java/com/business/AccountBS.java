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
    
    
    public UserDTO getUser(String username, String password) {
        try {
            return userDAO.getUser(username, password);
        } catch (Exception ex) {
            Logger.getLogger(AccountBS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
