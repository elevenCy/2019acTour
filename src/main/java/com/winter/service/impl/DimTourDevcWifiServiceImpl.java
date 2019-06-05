package com.winter.service.impl;

import java.util.List;

import com.winter.mapper.DimTourDevcWifiDao;
import com.winter.model.DimTourDevcWifi;
import com.winter.service.DimTourDevcWifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DimTourDevcWifiService")
public class DimTourDevcWifiServiceImpl extends BaseServiceImp<DimTourDevcWifi> implements DimTourDevcWifiService {

	@Autowired
	private DimTourDevcWifiDao dimTourDevcWifiDao;

	@Override
	public List<DimTourDevcWifi> findCascadeResource(DimTourDevcWifi o) {
		return dimTourDevcWifiDao.findCascadeResource(o);
	}
}
