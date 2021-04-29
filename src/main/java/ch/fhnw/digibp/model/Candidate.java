package ch.fhnw.digibp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Candidate {
    private String surname;
    private String name;
    private String mobileNumber;
    private String jobDescriptionId;
    private String workingYears;
    private String highestDiploma;
    private String averageGrade;

    public Candidate() {
    }

    public Candidate(String surname, String name, String mobileNumber, String jobDescriptionId, String workingYears, String highestDiploma, String averageGrade) {
        this.surname = surname;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.jobDescriptionId = jobDescriptionId;
        this.workingYears = workingYears;
        this.highestDiploma = highestDiploma;
        this.averageGrade = averageGrade;
    }

    @JsonProperty("1")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonProperty("6")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("7")
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @JsonProperty("2")
    public String getJobDescriptionId() {
        return jobDescriptionId;
    }

    public void setJobDescriptionId(String jobDescriptionId) {
        this.jobDescriptionId = jobDescriptionId;
    }

    @JsonProperty("3")
    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    @JsonProperty("4")
    public String getHighestDiploma() {
        return highestDiploma;
    }

    public void setHighestDiploma(String highestDiploma) {
        this.highestDiploma = highestDiploma;
    }

    @JsonProperty("5")
    public String getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = averageGrade;
    }
}
