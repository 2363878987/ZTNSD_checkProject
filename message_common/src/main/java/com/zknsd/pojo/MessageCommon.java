package com.zknsd.pojo;


import com.zknsd.bean.CheckMessageImage;

import java.util.List;

public class MessageCommon {
    //Message 表

    private String title;

    private Boolean deleteStatus;

    //信息内容表

    private List<CheckMessageImage> messageAttachment;

    private String messageContent;

    //信息类目表

    private List<TreeNodeCommonPojo> columnNodeList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public List<CheckMessageImage> getMessageAttachment() {
        return messageAttachment;
    }

    public void setMessageAttachment(List<CheckMessageImage> messageAttachment) {
        this.messageAttachment = messageAttachment;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public List<TreeNodeCommonPojo> getColumnNodeList() {
        return columnNodeList;
    }

    public void setColumnNodeList(List<TreeNodeCommonPojo> columnNodeList) {
        this.columnNodeList = columnNodeList;
    }

    public MessageCommon() {
    }
}
