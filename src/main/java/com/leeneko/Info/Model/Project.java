package com.leeneko.Info.Model;

import java.util.Date;

import lombok.Data;

@Data
public class Project {
	public String projectName;
	public String author;
	public Date currentDate;
	public String projectDesc;
}
