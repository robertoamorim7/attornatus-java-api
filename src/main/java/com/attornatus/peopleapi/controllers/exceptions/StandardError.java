package com.attornatus.peopleapi.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String msg;
    private String path;

    public StandardError() {}

    public StandardError(Instant timestamp, Integer status, String error, String msg, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.msg = msg;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
