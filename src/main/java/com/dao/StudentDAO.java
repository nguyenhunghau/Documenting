/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connection.MyConnection;
import com.document.dto.ConditionDTO;
import com.document.dto.StudentDTO;
import com.document.dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                int result = -1;
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            System.out.println(ps.toString());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    result = rs.getInt(1);
                }
                rs.close();
                ps.close();
                return result;
            }
        }
    }

    public List<StudentDTO> getList() throws Exception {
        String sql = "select * from student";
        List<StudentDTO> list = new ArrayList<>();
        try (Connection con = MyConnection.get();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                StudentDTO student = new StudentDTO();
                student.setFirstName(rs.getString("FirstName"));
                student.setLastName(rs.getString("FamilyName"));
                student.setDescription(rs.getString("Description"));
                student.setDate(rs.getString("Created"));
                student.setNumber(rs.getString("StudentId"));
                student.setPhone(rs.getString("Phone"));
                list.add(student);
            }
            return list;
        }
    }
}
