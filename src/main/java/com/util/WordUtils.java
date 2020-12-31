package com.util;
import com.aspose.words.*;
import com.document.dto.StudentDTO;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;

public class WordUtils {

    private static final String FIRST_NAME = "First Name:";
    private static final String FAMILY_NAME = "Family Name:";
    private static final String STUDENT_NUMBER = "Student number:";
    private static final String TELEPHONE = "Telephone:";
    private static final String DATE = "Date:";
    private static final String PRACTITIONER = "Practitioner’s name:";
    private static final String ADDRESS = "Address:";
    private static final String DIAGNOSIS = "mental health condition:";

    public static void main(String[] args) throws Exception {
        Document doc = new Document("D:\\work\\demo.docx");
        StudentDTO student = new StudentDTO();
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
            } else if (text.contains(DATE)) {
                String date = getString(text, DATE);
                student.setDate(date.trim().replace("_", ""));
                System.out.println(student.getDate());
            } else if (text.contains(PRACTITIONER)) {
                student.setPractitioner(getString(text, PRACTITIONER).trim().replace("_", ""));
                System.out.println(student.getPractitioner());
            } else if (text.contains(ADDRESS)) {
                student.setAddress(getString(text, ADDRESS).trim().replace("_", ""));
                System.out.println(student.getAddress());
            } else if (text.contains(DIAGNOSIS)) {
                nextIsDiagnosis = true;
                continue;
            } else if (text.contains("information if required.")) {
                nextIsDesc = true;
                continue;
            } else if (text.contains("writing time for exams).")) {
                student.setDescription(StringUtils.join(description, "\n"));
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
                student.setDiagnosis(text.trim().replace("_", ""));
                System.out.println(student.getDiagnosis());
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
    }

    private static String getString(String text, String name) {
        return text.substring(text.indexOf(name) + name.length());
    }
}
