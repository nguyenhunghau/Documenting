package com.business;

import com.dao.ConditionDAO;
import com.dao.StudentDAO;
import com.document.dto.ConditionDTO;
import com.document.dto.StudentDTO;
import com.document.dto.UserDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StudentBS {
    public JTable jtblStudent;
    private StudentDAO studentDAO = new StudentDAO();
    private ConditionDAO conditionDAO = new ConditionDAO();

    public List<StudentDTO> getList() throws Exception {
        List<StudentDTO> studentList = studentDAO.getList();
        List<ConditionDTO> conditionList = conditionDAO.getList();
        studentList.forEach(item -> {
            conditionList.forEach(condition -> {
                if(item.getId() == condition.getStudentId()) {
                    item.setCondition(condition);
                }
            });
        });
        return studentList;
    }

    public JScrollPane createJTable() throws Exception {
        List<StudentDTO> studentDTOList = getList();
        //UserTableModel model = new UserTableModel(userDTOList);
        DefaultTableModel model = new DefaultTableModel();
        jtblStudent = new JTable(model);
        model.addColumn("Id");
        model.addColumn("firstName");
        model.addColumn("FamilyName");
        model.addColumn("number");
        model.addColumn("phone");
        model.addColumn("date");
        model.addColumn("practitioner");
        model.addColumn("address");
        model.addColumn("diagnosis");
        model.addColumn("condition");
        model.addColumn("duration");
        model.addColumn("impact");
        model.addColumn("extraInfo");
        for(StudentDTO user: studentDTOList) {
            model.addRow(new Object[]{user.getId(), user.getFirstName(), user.getLastName(),
                    user.getNumber(), user.getPhone(), user.getDate(),
                    user.getCondition().getPractitioner(), user.getCondition().getAddress(),
                    user.getCondition().getDiagnosis(), user.getCondition().getCondition(),
                    user.getCondition().getDuration(), user.getCondition().getImpact(), user.getCondition().getExtraInfo()});
        }
        jtblStudent.setPreferredScrollableViewportSize(jtblStudent.getPreferredSize());
        jtblStudent.setFillsViewportHeight(true);
        return new JScrollPane(jtblStudent);
    }
}
