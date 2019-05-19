package com.zknsd.mapper;

import com.zknsd.bean.CheckMessage;
import com.zknsd.bean.CheckMessageContent;
import com.zknsd.bean.CheckMessageContentExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CheckMessageContentMapper {

    int countByExample(CheckMessageContentExample example);

    int deleteByExample(CheckMessageContentExample example);

    int deleteByPrimaryKey(String refMessageId);

    int insert(CheckMessageContent record);

    int insertSelective(CheckMessageContent record);

    List<CheckMessageContent> selectByExampleWithBLOBs(CheckMessageContentExample example);

    List<CheckMessageContent> selectByExample(CheckMessageContentExample example);

    CheckMessageContent selectByPrimaryKey(String refMessageId);

    int updateByExampleSelective(@Param("record") CheckMessageContent record, @Param("example") CheckMessageContentExample example);

    int updateByExampleWithBLOBs(@Param("record") CheckMessageContent record, @Param("example") CheckMessageContentExample example);

    int updateByExample(@Param("record") CheckMessageContent record, @Param("example") CheckMessageContentExample example);

    int updateByPrimaryKeySelective(CheckMessageContent record);

    int updateByPrimaryKeyWithBLOBs(CheckMessageContent record);

    int updateByPrimaryKey(CheckMessageContent record);
    void  updateMessageContent(CheckMessageContent messageContent);
}