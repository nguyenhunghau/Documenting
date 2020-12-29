package com.business;

import com.dao.UserDAO;
import com.document.dto.UserDTO;
import com.table.UserTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AccountBS {
    public JTable jtblAccount;
            
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

    public JScrollPane createJTable() throws Exception {
        List<UserDTO> userDTOList = userDAO.getUserList();
        //UserTableModel model = new UserTableModel(userDTOList);
        DefaultTableModel model = new DefaultTableModel();
        jtblAccount = new JTable(model);
        model.addColumn("Id");
        model.addColumn("Username");
        model.addColumn("Name");
        model.addColumn("Admin");
        for(UserDTO user: userDTOList) {
            model.addRow(new Object[]{user.getId(), user.getUsername(), user.getName(), user.getIsAdmin()? "Yes": "No"});
        }
        return new JScrollPane(jtblAccount);
        //https://stackoverflow.com/questions/27815400/retrieving-data-from-jdbc-database-into-jtable
    }
    
    public boolean delete(int id) throws Exception {
        return userDAO.delete(id);
    }
}
