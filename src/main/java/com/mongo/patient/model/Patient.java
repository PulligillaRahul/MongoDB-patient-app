package com.mongo.patient.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongo.patient.validation.UniquePatient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document
@UniquePatient
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
    private String id;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Indexed(unique = true)
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$", message = "Invalid email format")
	private String email;

    private List<String> bloodTestReports;
    private List<String> scanReports;
    private List<String> urineTestReports;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getBloodTestReports() {
		return bloodTestReports;
	}
	public void setBloodTestReports(List<String> bloodTestReports) {
		this.bloodTestReports = bloodTestReports;
	}
	public List<String> getScanReports() {
		return scanReports;
	}
	public void setScanReports(List<String> scanReports) {
		this.scanReports = scanReports;
	}
	public List<String> getUrineTestReports() {
		return urineTestReports;
	}
	public void setUrineTestReports(List<String> urineTestReports) {
		this.urineTestReports = urineTestReports;
	}

    
}
