package com.zknsd.bean;

public class CheckMessageImage {
    private Long id;

    private String messageAttachmentImage;

    private String refMessageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageAttachmentImage() {
        return messageAttachmentImage;
    }

    public void setMessageAttachmentImage(String messageAttachmentImage) {
        this.messageAttachmentImage = messageAttachmentImage == null ? null : messageAttachmentImage.trim();
    }

    public String getRefMessageId() {
        return refMessageId;
    }

    public void setRefMessageId(String refMessageId) {
        this.refMessageId = refMessageId == null ? null : refMessageId.trim();
    }
}