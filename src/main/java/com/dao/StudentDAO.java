/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connection.MyConnection;
import com.document.dto.StudentDTO;
import com.document.dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class StudentDAO {

    public int save(StudentDTO student) throws Exception {
        String sql = "insert into `student` (`FirstName`, `FamilyName`, `StudentId`, `Phone`, `Created`)"
                + " values ('"
                + student.getFirstName() + "','"
                + student.getLastName() + "','"
                + student.getNumber() + "','"
                + student.getPhone() + "','"
                + student.getDate() + "')";
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            System.out.println(ps.toString());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                rs.close();
                return -1;
            }
        }
    }
}
