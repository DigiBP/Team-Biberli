package ch.fhnw.digibp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {
    private String jobId;
    private String jobTitle;
    private String tasks;
    private String experience;
    private String salary;
    private String supervisor;
    private String grade;

    public Job() {
    }

    public Job(String jobId) {
        this.jobId = jobId;
    }

    @JsonProperty("0")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @JsonProperty("6")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @JsonProperty("1")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @JsonProperty("4")
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @JsonProperty("2")
    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    @JsonProperty("3")
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @JsonProperty("5")
    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
