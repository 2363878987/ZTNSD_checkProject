package com.zknsd.mapper;

import com.zknsd.bean.CheckColumn;
import com.zknsd.bean.CheckColumnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckColumnMapper {
    int countByExample(CheckColumnExample example);

    int deleteByExample(CheckColumnExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CheckColumn record);

    int insertSelective(CheckColumn record);

    List<CheckColumn> selectByExample(CheckColumnExample example);

    CheckColumn selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CheckColumn record, @Param("example") CheckColumnExample example);

    int updateByExample(@Param("record") CheckColumn record, @Param("example") CheckColumnExample example);

    int updateByPrimaryKeySelective(CheckColumn record);

    int updateByPrimaryKey(CheckColumn record);
}