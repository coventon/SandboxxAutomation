package com.sandboxx.framework.utils;

public class TestResultData {

    private String testsPassed;
    private String testsFailed;
    private String passRate;
    private String total;
    private String startTime;
    private String finishTime;
    private String duration;
    private String platform;
    private String environment;

    @Override
    public String toString() {
        return "TestResultData{" +
                "testsPasses='" + testsPassed + '\'' +
                ", testsFailed='" + testsFailed + '\'' +
                ", passRate='" + passRate + '\'' +
                ", total='" + total + '\'' +
                ", startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", duration='" + duration + '\'' +
                ", platform='" + platform + '\'' +
                ", environment='" + environment + '\'' +
                '}';
    }

    public void setTestsPassed(String testsPasses) {
        this.testsPassed = testsPasses;
    }

    public void setTestsFailed(String testsFailed) {
        this.testsFailed = testsFailed;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }



    public String getTestsPassed() {
        return testsPassed;
    }

    public String getTestsFailed() {
        return testsFailed;
    }

    public String getPassRate() {
        return passRate;
    }

    public String getTotal() {
        return total;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getPlatform() {
        return platform;
    }

    public String getEnvironment() {
        return environment;
    }
}
