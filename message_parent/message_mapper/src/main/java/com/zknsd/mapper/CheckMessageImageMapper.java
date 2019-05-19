package com.zknsd.mapper;

import com.zknsd.bean.CheckMessageImage;
import com.zknsd.bean.CheckMessageImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckMessageImageMapper {
    int countByExample(CheckMessageImageExample example);

    int deleteByExample(CheckMessageImageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CheckMessageImage record);

    int insertSelective(CheckMessageImage record);

    List<CheckMessageImage> selectByExample(CheckMessageImageExample example);

    CheckMessageImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CheckMessageImage record, @Param("example") CheckMessageImageExample example);

    int updateByExample(@Param("record") CheckMessageImage record, @Param("example") CheckMessageImageExample example);

    int updateByPrimaryKeySelective(CheckMessageImage record);

    int updateByPrimaryKey(CheckMessageImage record);
}