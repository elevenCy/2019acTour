package com.winter.mapper;

import java.util.List;

import com.winter.model.DimTourDevcSosAlarm;


/**
 * @ClassName DimTourDevcSosAlarm
 * @Description  dimTourDevcSosAlarm数据访问
 * @author tujing
 * @date 2016-7-1
 * @version V1.0
 */
public interface DimTourDevcSosAlarmDao  extends BaseDao<DimTourDevcSosAlarm>{

	public List<DimTourDevcSosAlarm> findCascadeResource(DimTourDevcSosAlarm o);
	public DimTourDevcSosAlarm findById(String id);
	
}
