package ch.fhnw.digibp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {
    private String jobId;
    private String grade;
    private String jobTitle;

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
}
