package ch.fhnw.digibp.model;

public class Candidate {
    private long id;
    private String name;
    private String jobDescriptionId;

    public Candidate() {
    }

    public Candidate(long id, String name, String jobDescriptionId) {
        this.id = id;
        this.name = name;
        this.jobDescriptionId = jobDescriptionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobDescriptionId() {
        return jobDescriptionId;
    }

    public void setJobDescriptionId(String jobDescriptionId) {
        this.jobDescriptionId = jobDescriptionId;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobDescriptionId=" + jobDescriptionId +
                '}';
    }
}
