package com.zknsd.services;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zknsd.bean.*;
import com.zknsd.interfaces.MessageInterface;
import com.zknsd.mapper.CheckColumnMapper;
import com.zknsd.mapper.CheckMessageContentMapper;
import com.zknsd.mapper.CheckMessageImageMapper;
import com.zknsd.mapper.CheckMessageMapper;
import com.zknsd.pojo.MessageResult;
import com.zknsd.pojo.PageHelperPojo;
import com.zknsd.pojo.TreeNodeCommonPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MessageInterface_Imp implements MessageInterface {
    @Autowired
    private CheckMessageMapper messageMapper;
    @Autowired
    private CheckColumnMapper columnMapper;
    @Autowired
    private CheckMessageImageMapper imageMapper;
    @Autowired
    private CheckMessageContentMapper contentMapper;
    @Override
    public MessageResult messageAdd(CheckMessage message,String content,String image,long cid) {

        try {
            //添加message表
            String id = UUID.randomUUID().toString();
            message.setId(id);
            message.setDeleteStatus(true);
            message.setUpdatetime(new Date());
            message.setRefColumn(cid);
            messageMapper.insert(message);
            //添加content表
            CheckMessageContent messageContent = new CheckMessageContent();
            messageContent.setMessageDesc(content);
            messageContent.setRefMessageId(id);
            messageContent.setCreated(new Date());
            contentMapper.insert(messageContent);
            //添加image表
            CheckMessageImage messageImage = new CheckMessageImage();
            String[] images = image.split(",");
            for (String img:images
                 ) {
                img = img.substring(21,img.length());
                messageImage.setMessageAttachmentImage(img);
                messageImage.setRefMessageId(id);
                imageMapper.insert(messageImage);
            }

        }catch (Exception e){
            return MessageResult.build(400,"添加失败");
        }
        return MessageResult.ok();
    }

    @Override
    public PageHelperPojo messageList(int page, int rows) {
        //查询message对象
        CheckMessageExample messageExample = new CheckMessageExample();
        //查询之前先进行分页
        PageHelper.startPage(page, rows);
        //返回分页后的集合
        List<CheckMessage> messages = messageMapper.selectByExample(messageExample);
        //创建pageInfo对象
        PageInfo pageInfo = new PageInfo(messages);
        //创建返回给浏览器的json对象
        PageHelperPojo pageHelperPojo = new PageHelperPojo();
        //设置好属性（当前页数，信息对象集合）
        pageHelperPojo.setTotal(pageInfo.getTotal());
        System.out.println("总条数"+pageInfo.getTotal());
        pageHelperPojo.setRows(messages);
        return pageHelperPojo;
    }

    @Override
    public List<TreeNodeCommonPojo> getMessageNodeList(long id) {
        CheckColumnExample columnExample = new CheckColumnExample();
        CheckColumnExample.Criteria criteria = columnExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<CheckColumn> columnList = columnMapper.selectByExample(columnExample);
        List<TreeNodeCommonPojo> list = new ArrayList<TreeNodeCommonPojo>();
        for (CheckColumn checkColumn:columnList){
            TreeNodeCommonPojo treeNodeCommonPojo = new TreeNodeCommonPojo();
            treeNodeCommonPojo.setId(checkColumn.getId());
            treeNodeCommonPojo.setText(checkColumn.getName());
            treeNodeCommonPojo.setState(checkColumn.getIsParent()?"closed":"open");
            list.add(treeNodeCommonPojo);
        }
        return list;
    }

    @Override
    public CheckMessage getMessageById(String id) {

        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CheckMessageImage> getImageByRef_Attachment_Id(String ref_attachment_id) {
        CheckMessageImageExample messageImageExample= new CheckMessageImageExample();
        CheckMessageImageExample.Criteria criteria = messageImageExample.createCriteria();
        criteria.andRefMessageIdEqualTo(ref_attachment_id);
        List<CheckMessageImage> checkMessageImages = imageMapper.selectByExample(messageImageExample);
        for (CheckMessageImage messageImage:checkMessageImages
             ) {
            messageImage.setMessageAttachmentImage("http://192.168.25.133"+messageImage.getMessageAttachmentImage());
        }
        return checkMessageImages;
    }

    @Override
    public CheckMessageContent getContentByRef_Message_Id(String ref_message_id) {

        return contentMapper.selectByPrimaryKey(ref_message_id);
    }

    @Override
    public void deleteMessage(String ids) {
        String[] split = ids.split(",");
        for (String id:split
             ) {
            //删除时相关联的表也要删除  messageImg
            CheckMessageImageExample imageExample = new CheckMessageImageExample();
            CheckMessageImageExample.Criteria criteria = imageExample.createCriteria();
            criteria.andRefMessageIdEqualTo(id);
            imageMapper.deleteByExample(imageExample);
            //删除 messageContent
            contentMapper.deleteByPrimaryKey(id);
            //删除 message
            messageMapper.deleteByPrimaryKey(id);

        }

    }

    @Override
    public CheckColumn getMessageColumn(long id) {
        return columnMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateMessage(CheckMessage message, String content, String image, long cid) {
        //修改message表
        CheckMessageExample messageExample = new CheckMessageExample();
        CheckMessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andIdEqualTo(message.getId());
        List<CheckMessage> checkMessages = messageMapper.selectByExample(messageExample);
        for (CheckMessage checkMessage : checkMessages
                ) {
            checkMessage.setUpdatetime(new Date());
            checkMessage.setDeleteStatus(true);
            checkMessage.setRefColumn(cid);
            checkMessage.setTitle(message.getTitle());
            messageMapper.updateByExample(checkMessage, messageExample);
        }
        //修改content表
        CheckMessageContentExample messageContentExample = new CheckMessageContentExample();
        CheckMessageContentExample.Criteria criteria1 = messageContentExample.createCriteria();
        criteria1.andRefMessageIdEqualTo(message.getId());
        List<CheckMessageContent> checkMessageContents = contentMapper.selectByExample(messageContentExample);
        for (CheckMessageContent messageContent:checkMessageContents
             ) {
            messageContent.setMessageDesc(content);
            messageContent.setUpdated(new Date());
            contentMapper.updateByExample(messageContent,messageContentExample);
        }

        //添加image表
        if (image != null && image != "") {
            CheckMessageImage messageImage = new CheckMessageImage();
            String[] images = image.split(",");
            for (String img : images
                    ) {
                img = img.substring(21, img.length());
                messageImage.setMessageAttachmentImage(img);
                messageImage.setRefMessageId(message.getId());
                imageMapper.insert(messageImage);
            }
        }
    }


}
