package ch.fhnw.digibp.model;

public class Candidate {
    private String surname;
    private String name;
    private String mobileNumber;
    private String jobDescriptionId;
    private int workingYears;
    private String highestDiploma;
    private double averageGrade;

    public Candidate(String surname, String name, String mobileNumber, String jobDescriptionId, int workingYears, String highestDiploma, double averageGrade) {
        this.surname = surname;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.jobDescriptionId = jobDescriptionId;
        this.workingYears = workingYears;
        this.highestDiploma = highestDiploma;
        this.averageGrade = averageGrade;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getJobDescriptionId() {
        return jobDescriptionId;
    }

    public void setJobDescriptionId(String jobDescriptionId) {
        this.jobDescriptionId = jobDescriptionId;
    }

    public int getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(int workingYears) {
        this.workingYears = workingYears;
    }

    public String getHighestDiploma() {
        return highestDiploma;
    }

    public void setHighestDiploma(String highestDiploma) {
        this.highestDiploma = highestDiploma;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
