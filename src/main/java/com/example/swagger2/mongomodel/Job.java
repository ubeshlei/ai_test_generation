package com.example.swagger2.mongomodel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Job {
    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @Min(value = 0, message = "Salary must be non-negative")
    private double jobSalary;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Company industry is required")
    private String companyIndustry;

    public Job() {}

    public Job(String jobTitle, double jobSalary, String companyName, String companyIndustry) {
        this.jobTitle = jobTitle;
        this.jobSalary = jobSalary;
        this.companyName = companyName;
        this.companyIndustry = companyIndustry;
    }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public double getJobSalary() { return jobSalary; }
    public void setJobSalary(double jobSalary) { this.jobSalary = jobSalary; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyIndustry() { return companyIndustry; }
    public void setCompanyIndustry(String companyIndustry) { this.companyIndustry = companyIndustry; }
}