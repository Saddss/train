package com.sss.train.business.mapper;

import com.sss.train.business.domain.DailyTrain;
import com.sss.train.business.domain.DailyTrainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyTrainMapper {
    long countByExample(DailyTrainExample example);

    int deleteByExample(DailyTrainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DailyTrain record);

    int insertSelective(DailyTrain record);

    List<DailyTrain> selectByExample(DailyTrainExample example);

    DailyTrain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DailyTrain record, @Param("example") DailyTrainExample example);

    int updateByExample(@Param("record") DailyTrain record, @Param("example") DailyTrainExample example);

    int updateByPrimaryKeySelective(DailyTrain record);

    int updateByPrimaryKey(DailyTrain record);
}