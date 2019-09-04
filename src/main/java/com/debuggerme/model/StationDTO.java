package com.debuggerme.model;

import javafx.animation.Timeline;
import javafx.scene.control.Button;

import java.time.LocalTime;
import java.util.Timer;

public class StationDTO {

    private Timer timer;
    private String station;
    private String fileName;
    private LocalTime startedTime;
    private LocalTime endTime;
    private String size;
    private String status;
    private String location;
    private Button action;
    private Button open;
    private Timeline timeline;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalTime getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(LocalTime startedTime) {
        this.startedTime = startedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    public Button getOpen() {
        return open;
    }

    public void setOpen(Button open) {
        this.open = open;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StationDTO{");
        sb.append("timer=").append(timer);
        sb.append(", station='").append(station).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", startedTime=").append(startedTime);
        sb.append(", status='").append(status).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", action=").append(action);
        sb.append(", open=").append(open);
        sb.append('}');
        return sb.toString();
    }
}
