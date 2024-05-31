package edu.upc.dsa.models;

public class AbuseReport {
    private String reporter;
    private String description;

    private String date;

    public AbuseReport() {
    }
    public AbuseReport(String reporter, String description, String date) {
        this.reporter = reporter;
        this.description = description;
        this.date = date;
    }
    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}