package com.zknsd.web;

import com.zknsd.bean.CheckColumn;
import com.zknsd.bean.CheckMessage;
import com.zknsd.bean.CheckMessageContent;
import com.zknsd.bean.CheckMessageImage;
import com.zknsd.interfaces.MessageInterface;
import com.zknsd.pojo.MessageCommon;
import com.zknsd.pojo.MessageResult;
import com.zknsd.pojo.PageHelperPojo;
import com.zknsd.pojo.TreeNodeCommonPojo;
import com.zknsd.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Check_action {
    @Autowired
    private MessageInterface messageInterface;
    @RequestMapping("/")
    public String test(){
        return "index";
    }
    @RequestMapping("/{page}")
    public String showIndex(@PathVariable(value="page")String page) {
        return page;
    }
    @RequestMapping("/rest/page/{page}")
    public String showEditIndex(@PathVariable(value="page")String page) {
        return page;
    }
    //message表添加功能
    @RequestMapping("/item/save")
    @ResponseBody
    public MessageResult itemAdd(final CheckMessage message,String content,String image,long cid){


        return messageInterface.messageAdd(message,content,image,cid);

    }
    //message表展示功能
    @RequestMapping("/item/list")
    @ResponseBody
    public PageHelperPojo messageList(int page, int rows){
        return  messageInterface.messageList(page,rows);
    }
    //图片上传功能
    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map getItemCatTreeNodeList(MultipartFile uploadFile){

        try {

            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/fastdfs.conf");
            String originalFilename = uploadFile.getOriginalFilename();
            String fileextension = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            String urlRight = fastDFSClient.uploadFile(uploadFile.getBytes(), fileextension);
            String urlLeft="http://192.168.25.133/";
            System.out.println(urlLeft+urlRight);
            HashMap hashMap = new HashMap();
            hashMap.put("error",0);
            hashMap.put("url",urlLeft+urlRight);
            return hashMap;
        } catch (Exception e) {
            // TODO: handle exception
            HashMap hashMap = new HashMap();
            hashMap.put("error",1);
            hashMap.put("message","图片上传失败");
            return hashMap;

        }
    }
    //    树形结构的信息栏目
    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<TreeNodeCommonPojo> getMessageNodeList(@RequestParam(value = "id",defaultValue = "0")long id){
        return messageInterface.getMessageNodeList(id);
    }
    //修改窗口回显messageContent 信息表中的内容

    @RequestMapping("/rest/item/query/item/desc/{id}")
    @ResponseBody
    public MessageResult resultMessageContent(@PathVariable String id){
        CheckMessageContent messageContent = messageInterface.getContentByRef_Message_Id(id);
       return MessageResult.ok(messageContent);
    }
    //修改窗口回显message_image 表中的图片地址
    @RequestMapping("/rest/item/query/item/image/{id}")
    @ResponseBody
    public MessageResult resultMessageImage(@PathVariable String id){
        List<CheckMessageImage> image = messageInterface.getImageByRef_Attachment_Id(id);
        CheckMessageImage checkMessageImage = new CheckMessageImage();
        checkMessageImage.setImages(image);
        return MessageResult.ok(checkMessageImage);
    }
    //修改窗口回显message_image 表中的图片地址
    @RequestMapping("/rest/item/query/item/column/{id}")
    @ResponseBody
    public MessageResult resultMessageColumn(@PathVariable String id){
        CheckMessage messageById = messageInterface.getMessageById(id);
        CheckColumn messageColumn = messageInterface.getMessageColumn(messageById.getRefColumn());
        return MessageResult.ok(messageColumn);
    }
    //信息删除
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public MessageResult messageDelete(String ids){
        try {
            messageInterface.deleteMessage(ids);
        }catch (Exception e){
            return MessageResult.build(400,"删除失败");
        }
        return MessageResult.ok();
    }
    //修改信息
    @RequestMapping("/rest/item/update")
    @ResponseBody
    public MessageResult messageUpdate(CheckMessage message,String content,String image,long cid){
        try {
            messageInterface.updateMessage(message,content,image,cid);
            return MessageResult.ok();
        }catch (Exception e){
            return MessageResult.build(400,"修改失败");
        }
    }
}
