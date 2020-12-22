package com.dao;

import com.connection.MyConnection;
import com.document.dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class UserDAO {
    
    public UserDTO getUser(String username, String password) throws Exception {
        String sql = "select * from user where username=? and password=?";
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getInt("Id"));
                user.setUsername(rs.getString("Username"));
                user.setName(rs.getString("Name"));
                user.setIsAdmin(rs.getBoolean("Admin"));
                return user;
            }
            return null;
        }
    } 
    
//    public boolean save(UserDTO user) throws Exception {
//        String sql = "select * from user where username=? and password=?";
//        try (Connection con = MyConnection.get();
//                PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()) {
//                UserDTO user = new UserDTO();
//                user.setId(rs.getInt("Id"));
//                user.setUsername(rs.getString("Username"));
//                user.setName(rs.getString("Name"));
//                user.setIsAdmin(rs.getBoolean("Admin"));
//                return user;
//            }
//            return null;
//        }
//    } 
}
