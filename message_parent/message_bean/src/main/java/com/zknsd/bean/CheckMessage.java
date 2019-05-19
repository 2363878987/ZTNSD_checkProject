package com.zknsd.bean;

import java.util.Date;

public class CheckMessage {
    private String id;

    private String title;

    private Date updatetime;

    private Long refColumn;

    private Boolean deleteStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getRefColumn() {
        return refColumn;
    }

    public void setRefColumn(Long refColumn) {
        this.refColumn = refColumn;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}