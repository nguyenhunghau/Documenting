/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connection.MyConnection;
import com.document.dto.ConditionDTO;
import com.document.dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ConditionDAO {
    
    public boolean save(ConditionDTO condition) throws Exception {
        String sql = "insert into `condition` (`Practitioner`,"
                   + " `Address`, "
                   + " `Diagnosis`, "
                   + " `Condition`, "
                   + " `Duration`, "
                   + " `Impact`, "
                   + " `ExtraInfo`, "
                   + " `StudentId`) "
                   + " VALUES ( "
                + "'" + condition.getPractitioner()+ "',"
                + "'" + condition.getAddress() + "',"
                + "'" + condition.getDiagnosis()+ "',"
                + "'" + condition.getCondition()+ "',"
                + "'" + condition.getDuration()+ "',"
                + "'" + condition.getImpact()+ "',"
                + "'" + condition.getExtraInfo()+ "',"
                + "" + condition.getStudentId()+ ")";
        try (Connection con = MyConnection.get();
                PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.println(ps.toString());
            return ps.executeUpdate(sql) == 1;
        }
    }

    public List<ConditionDTO> getList() throws Exception {
        String sql = "select * from condition";
        List<ConditionDTO> list = new ArrayList<>();
        try (Connection con = MyConnection.get();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ConditionDTO condition = new ConditionDTO();
//                user.setId(rs.getInt("Id"));
//                user.setUsername(rs.getString("Username"));
//                user.setName(rs.getString("Name"));
//                user.setIsAdmin(rs.getBoolean("Admin"));
                list.add(condition);
            }
            return list;
        }
    }
}
