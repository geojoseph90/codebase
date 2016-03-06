package com.gradebook.factories;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.gradebook.DO.AssignedWork;
import com.gradebook.DO.GradeBook;
import com.gradebook.DO.GeneralGradeItem;
import com.gradebook.DO.GradedWork;
import com.gradebook.DO.GeneralGradingSchema;
import com.gradebook.DO.Student;


public class HTMLGradebookGenerator implements GradebookGenerator {

	@Override
	public boolean generateGradebook(GradeBook generalGradeBook) {
		String userHomeFolder = System.getProperty("user.home");	
		String fileName = "/Desktop/GradeBook.html";
		StringBuilder outputString = new StringBuilder();
		outputString.append("<html>");
		outputString.append("<head>");
		outputString.append("</head>");
		outputString.append("<table>");
		outputString.append("<tr style=\"font-weight: bold;height=\"20\";\">");
		outputString.append(appendHeader(generalGradeBook));
		outputString.append("</tr>");
		outputString.append("</br>");
		for(Student generalStudent : generalGradeBook.getGeneralGrades().getStudent()) {
			outputString.append("<tr height=\"20\">");
			outputString.append("<td width=\"100\">");
			outputString.append(generalStudent.getStudentName());
			outputString.append("</td>");
			outputString.append("<td width=\"100\">");
			outputString.append(generalStudent.getStudentId());
			outputString.append("</td>");
			outputString.append(appendColumn(generalStudent));
			outputString.append("</tr>");
			outputString.append("</br>");
		}
		outputString.append("</table>");
		
		
	    try {
	    	FileWriter fstream = new FileWriter(userHomeFolder.concat(fileName),false);
		    BufferedWriter out = new BufferedWriter(fstream);
			out.write(outputString.toString());
			out.close();
			System.out.println("The HTML gradebook has been generated in the location"+fileName);
		} catch (IOException e) {
			System.out.println("Cannot write gradebook to the location");
			e.printStackTrace();
		}
	    
		return true;
	}
	
	public StringBuilder appendColumn(Student generalStudent) {
		StringBuilder contentRow = new StringBuilder();
		
		for(AssignedWork assignedWork : generalStudent.getAssignedWork()) {
			for(GradedWork gradedWork : assignedWork.getGradedWork()) {
				contentRow.append("<td width=\"100\">");
				contentRow.append(gradedWork.getGrade());
				contentRow.append("</td>");
			}
			
		}
		contentRow.append("<td width=\"100\">");
		contentRow.append(generalStudent.getLetterGrade());
		contentRow.append("</td>");
		return contentRow;
	}
	
	public StringBuilder appendHeader(GradeBook generalGradeBook) {
		StringBuilder headerRow = new StringBuilder();
		headerRow.append("<td width=\"1\200\">");
		headerRow.append("Name");
		headerRow.append("</td>");
		headerRow.append("<td width=\"150\">");
		headerRow.append("ID");
		headerRow.append("</td>");
		
		Student genStudent = generalGradeBook.getGeneralGrades().getStudent().get(0);
		for(AssignedWork generalAssignedWork : genStudent.getAssignedWork()) {
			
			for(GradedWork gradedWork : generalAssignedWork.getGradedWork()) {
				headerRow.append("<td width=\"100\">");
				headerRow.append(gradedWork.getName());
				headerRow.append("</td>");
			}
		}
		
		headerRow.append("<td width=\"100\">");
		headerRow.append("Grade");
		headerRow.append("</td>");
		
		
		return headerRow;
		
	}

	
}