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
}
