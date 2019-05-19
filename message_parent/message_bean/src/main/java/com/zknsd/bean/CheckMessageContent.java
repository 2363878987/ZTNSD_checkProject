package com.zknsd.bean;

import java.util.Date;

public class CheckMessageContent {
    private String refMessageId;

    private Date created;

    private Date updated;

    private String messageDesc;

    public String getRefMessageId() {
        return refMessageId;
    }

    public void setRefMessageId(String refMessageId) {
        this.refMessageId = refMessageId == null ? null : refMessageId.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }
}