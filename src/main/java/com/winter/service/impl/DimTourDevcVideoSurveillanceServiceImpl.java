package com.winter.service.impl;

import java.util.List;

import com.winter.mapper.DimTourDevcVideoSurveillanceDao;
import com.winter.model.DimTourDevcVideoSurveillance;
import com.winter.service.DimTourDevcVideoSurveillanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service("DimTourDevcVideoSurveillanceService")
public class DimTourDevcVideoSurveillanceServiceImpl extends BaseServiceImp<DimTourDevcVideoSurveillance> implements DimTourDevcVideoSurveillanceService {

	@Autowired
	private DimTourDevcVideoSurveillanceDao dimTourDevcVideoSurveillanceDao;

	@Override
	public List<DimTourDevcVideoSurveillance> findCascadeResource(DimTourDevcVideoSurveillance o) {
		return dimTourDevcVideoSurveillanceDao.findCascadeResource(o);
	}
}
