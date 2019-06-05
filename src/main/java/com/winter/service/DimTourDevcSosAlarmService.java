package com.winter.service;

import java.util.List;

import com.winter.model.DimTourDevcSosAlarm;

/**
 * @ClassName DimTourDevcSosAlarm
 * @Description  dimTourDevcSosAlarm业务处理
 * @author tujing
 * @date 2016-7-1
 * @version V1.0
 */
public interface DimTourDevcSosAlarmService extends BaseService<DimTourDevcSosAlarm>{
	public List<DimTourDevcSosAlarm> findCascadeResource(DimTourDevcSosAlarm o);
}
