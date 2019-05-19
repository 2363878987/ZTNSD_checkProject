package com.zknsd.interfaces;

import com.zknsd.bean.CheckColumn;
import com.zknsd.bean.CheckMessage;
import com.zknsd.bean.CheckMessageContent;
import com.zknsd.bean.CheckMessageImage;
import com.zknsd.pojo.MessageResult;
import com.zknsd.pojo.PageHelperPojo;
import com.zknsd.pojo.TreeNodeCommonPojo;

import java.util.List;

public interface MessageInterface {
    //信息添加功能
    MessageResult messageAdd(CheckMessage message,String content,String image,long cid);
    //信息分页查询
    PageHelperPojo messageList(int page,int rows);
    //树形结构的信息栏目
    List<TreeNodeCommonPojo> getMessageNodeList(long id);
    //获取CheckMessage对象
    CheckMessage getMessageById(String id);
    //获取CheckMessageImmage对象集合
    List<CheckMessageImage> getImageByRef_Attachment_Id(String ref_attachment_id);
    //获取CheckContent对象
    CheckMessageContent getContentByRef_Message_Id(String ref_message_id);

    void deleteMessage(String ids);

    CheckColumn getMessageColumn(long id);

    void updateMessage(CheckMessage message, String content, String image, long cid);
}
