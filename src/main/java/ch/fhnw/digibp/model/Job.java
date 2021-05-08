package ch.fhnw.digibp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {
    private String jobId;

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
}
