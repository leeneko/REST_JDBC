package com.leeneko.Info.Service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.leeneko.Info.Model.Project;

@Service
public class InfoService {
	
	public Project getInfo() {
		
		Project proj = new Project();
		proj.projectName = "PJTApplication";
		proj.author = "leeneko";
		proj.currentDate = new Date();
		proj.projectDesc = "Spring Boot + JDBC Template + Postgre SQL";
		
		return proj;
		
	}
	
}
