package com.zknsd.mapper;

import com.zknsd.bean.CheckMessage;
import com.zknsd.bean.CheckMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckMessageMapper {
    int countByExample(CheckMessageExample example);

    int deleteByExample(CheckMessageExample example);

    int deleteByPrimaryKey(String id);

    int insert(CheckMessage record);

    int insertSelective(CheckMessage record);

    List<CheckMessage> selectByExample(CheckMessageExample example);

    CheckMessage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CheckMessage record, @Param("example") CheckMessageExample example);

    int updateByExample(@Param("record") CheckMessage record, @Param("example") CheckMessageExample example);

    int updateByPrimaryKeySelective(CheckMessage record);

    int updateByPrimaryKey(CheckMessage record);
}