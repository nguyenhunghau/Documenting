/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.table;

import com.document.dto.UserDTO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class UserTableModel extends AbstractTableModel
{
    private final List<UserDTO> userList;
     
    private final String[] columnNames = new String[] {
            "Id", "Username", "Name", "Admin"
    };
    private final Class[] columnClass = new Class[] {
        Integer.class, String.class, String.class, Boolean.class
    };
 
    public UserTableModel(List<UserDTO> userList)
    {
        this.userList = userList;
    }
     
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        return userList.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        UserDTO row = userList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            return row.getUsername();
        }
        else if(2 == columnIndex) {
            return row.getName();
        }
        else if(3 == columnIndex) {
            return row.getIsAdmin();
        }
        return null;
    }
}
