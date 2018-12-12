package com.omelchenkoaleks.core.abstracts;

import java.util.Calendar;

public abstract class AbstractOpetation {

    private long id;
    private Calendar dateTime;
    private String addInfo;

    public AbstractOpetation() {
    }


    public AbstractOpetation(long id, Calendar dateTime, String addInfo) {
        this.id = id;
        this.dateTime = dateTime;
        this.addInfo = addInfo;
    }

    public AbstractOpetation(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
}
