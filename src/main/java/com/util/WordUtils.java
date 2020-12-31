package com.util;
import com.aspose.words.*;
import com.dao.ConditionDAO;
import com.dao.StudentDAO;
import com.document.dto.ConditionDTO;
import com.document.dto.StudentDTO;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class WordUtils {

    private static final String FIRST_NAME = "First Name:";
    private static final String FAMILY_NAME = "Family Name:";
    private static final String STUDENT_NUMBER = "Student number:";
    private static final String TELEPHONE = "Telephone:";
    private static final String DATE = "Date:";
    private static final String PRACTITIONER = "Practitioner’s name:";
    private static final String ADDRESS = "Address:";
    private static final String DIAGNOSIS = "mental health condition:";

    public StudentDTO readStudentDoc(String pathFile) throws Exception {
        Document doc = new Document(pathFile);
        StudentDTO student = new StudentDTO();
        ConditionDTO condition = new ConditionDTO();
        boolean nextIsDiagnosis = false, nextIsDesc = false, nextIsComment = false;
        java.util.List<String> description = new ArrayList<String>();

        for (Paragraph para : (Iterable<Paragraph>) doc.getChildNodes(NodeType.PARAGRAPH,true)) {
            String text = para.toString(SaveFormat.TEXT).trim();
            //System.out.println(text);
            if(text.contains(FIRST_NAME)) {
                String firstName = text.substring(text.indexOf(FIRST_NAME) + FIRST_NAME.length(), text.indexOf(FAMILY_NAME));
                String lastname = getString(text, FAMILY_NAME);
                student.setFirstName(firstName.trim().replace("_", ""));
                student.setLastName(lastname.trim().replace("_", ""));
                System.out.println(student.getFirstName() + " " + student.getLastName());
            } else if (text.contains(STUDENT_NUMBER)) {
                String number = text.substring(text.indexOf(STUDENT_NUMBER) + STUDENT_NUMBER.length(), text.indexOf(TELEPHONE));
                String telephone = getString(text, TELEPHONE);
                student.setNumber(number.trim().replace("_", ""));
                student.setPhone(telephone.trim().replace("_", ""));
                System.out.println(student.getNumber() + " " + student.getPhone());
            } else if (text.contains(DATE) && student.getDate() == null) {
                String date = getString(text, DATE);
                student.setDate(date.trim().replace("_", ""));
                System.out.println(student.getDate());
            } else if (text.contains(PRACTITIONER)) {
                condition.setPractitioner(getString(text, PRACTITIONER).trim().replace("_", ""));
                System.out.println(condition.getPractitioner());
            } else if (text.contains(ADDRESS)) {
                condition.setAddress(getString(text, ADDRESS).trim().replace("_", ""));
                System.out.println(condition.getAddress());
            } else if (text.contains(DIAGNOSIS)) {
                nextIsDiagnosis = true;
                continue;
            } else if (text.contains("information if required.")) {
                nextIsDesc = true;
                continue;
            } else if (text.contains("writing time for exams).")) {
                condition.setExtraInfo(StringUtils.join(description, "\n"));
                System.out.println(student.getDescription());
                description.clear();
                nextIsDesc = false;
                nextIsComment = true;
                continue;
            } else if (text.contains("Practitioner’s signature:")) {
                student.setComment(StringUtils.join(description, "\n"));
                System.out.println(student.getComment());
                nextIsComment = false;
                continue;
            }

            if(nextIsDiagnosis && !text.trim().isEmpty()) {
                condition.setDiagnosis(text.trim().replace("_", ""));
                System.out.println(condition.getDiagnosis());
                nextIsDiagnosis = false;
                continue;
            }

            if(nextIsDesc && !text.trim().isEmpty()) {
                description.add(text.trim().replace("_", ""));
                continue;
            }

            if(nextIsComment && !text.trim().isEmpty()) {
                description.add(text.trim().replace("_", ""));
            }
        }
        int studentID = new StudentDAO().save(student);
        condition.setStudentId(studentID);
        new ConditionDAO().save(condition);
        return student;
    }

    private static String getString(String text, String name) {
        return text.substring(text.indexOf(name) + name.length());
    }
}
