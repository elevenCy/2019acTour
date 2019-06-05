package com.winter.service.impl;

import java.util.List;

import com.winter.mapper.DimTourDevcSosAlarmDao;
import com.winter.model.DimTourDevcSosAlarm;
import com.winter.service.DimTourDevcSosAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DimTourDevcSosAlarmService")
public class DimTourDevcSosAlarmServiceImpl extends BaseServiceImp<DimTourDevcSosAlarm> implements DimTourDevcSosAlarmService {

	@Autowired
	private DimTourDevcSosAlarmDao dimTourDevcSosAlarmDao;

	@Override
	public List<DimTourDevcSosAlarm> findCascadeResource(DimTourDevcSosAlarm o) {
		return dimTourDevcSosAlarmDao.findCascadeResource(o);
	}
}
