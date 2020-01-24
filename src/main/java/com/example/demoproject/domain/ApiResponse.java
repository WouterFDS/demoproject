package com.example.demoproject.domain;

public class ApiResponse {

    private int status;
    private String boodschap;

    public ApiResponse(int status, String boodschap, Object resultaat) {
        this.status = status;
        this.boodschap = boodschap;
        this.resultaat = resultaat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBoodschap() {
        return boodschap;
    }

    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
    }

    public Object getResultaat() {
        return resultaat;
    }

    public void setResultaat(Object resultaat) {
        this.resultaat = resultaat;
    }

    private Object resultaat;
}
