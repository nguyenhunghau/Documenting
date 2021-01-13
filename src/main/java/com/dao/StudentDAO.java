package com.dao;

import com.connection.MyConnection;
import com.document.dto.ConditionDTO;
import com.document.dto.StudentDTO;
import com.document.dto.StudentSearch;
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
    
    public boolean update(StudentDTO user) throws Exception {
        String sql = "update student set FirstName='"+ user.getFirstName()+ "'"
                + ", FamilyName='"+ user.getLastName()
                + "', StudentId='"+ user.getNumber()
                + "', Phone='"+ user.getPhone()
                + "', Created='"+ user.getDate()
                + "' where Id="+ user.getId();
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql)) {
            return ps.executeUpdate(sql) == 1;
        }
    } 

    public List<StudentDTO> getList(StudentSearch studentSearch) throws Exception {
        String sql = "select * from student where FirstName like '%" + studentSearch.getFirstName() + "%'"
                + " and StudentId like '%" + studentSearch.getNumber() + "%'"
                + " and Phone like '%" + studentSearch.getPhone() + "%'"
                + " and Created like '%" + studentSearch.getDate() + "%'";
        List<StudentDTO> list = new ArrayList<>();
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentDTO student = new StudentDTO();
                student.setId(rs.getInt("Id"));
                student.setFirstName(rs.getString("FirstName"));
                student.setLastName(rs.getString("FamilyName"));
                //student.setDescription(rs.getString("Description"));
                student.setDate(rs.getString("Created"));
                student.setNumber(rs.getString("StudentId"));
                student.setPhone(rs.getString("Phone"));
                list.add(student);
            }
            return list;
        }
    }

    public StudentDTO findById(int studentId) throws Exception {
        String sql = "select * from student where id=" + studentId;
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                StudentDTO student = new StudentDTO();
                student.setId(rs.getInt("Id"));
                student.setFirstName(rs.getString("FirstName"));
                student.setLastName(rs.getString("FamilyName"));
                //student.setDescription(rs.getString("Description"));
                student.setDate(rs.getString("Created"));
                student.setNumber(rs.getString("StudentId"));
                student.setPhone(rs.getString("Phone"));
                return student;
            }
            return null;
        }
    }

    public boolean delete(Integer id) throws Exception {
        String sql = "delete from student where Id="+ id;
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql)) {
            return ps.executeUpdate(sql) == 1;
        }
    }
}
