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
    
    public UserDTO getUser(int id) throws Exception {
        String sql = "select * from user where id=?";
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
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
    
    public boolean save(UserDTO user) throws Exception {
        String sql = "insert into `user` (`Username`, `Password`, `Name`, `Admin`) values ('"
                + user.getUsername() + "','"
                + user.getPassword()+ "','"
                + user.getName()+ "',"
                + user.getIsAdmin()+ ")";
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ps.setString(3, user.getName());
            //ps.setBoolean(4, user.getIsAdmin());
            System.out.println(ps.toString());
            return ps.executeUpdate(sql) == 1;
        }
    }
    
    public boolean update(UserDTO user) throws Exception {
        String sql = "update user set Username='"+ user.getUsername() + "'"
                + ", Password='"+ user.getPassword()+ "', Name='"+ user.getName()+ "', Admin="+ user.getIsAdmin()
                + " where Id="+ user.getId();
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ps.setString(3, user.getName());
//            ps.setBoolean(4, user.getIsAdmin());
//            ps.setInt(5, user.getId());
            return ps.executeUpdate(sql) == 1;
        }
    } 
}
