package com.winter.service.impl;


import com.winter.mapper.OdsTourTrlCarInfoDao;
import com.winter.model.OdsTourTrlCarInfo;
import com.winter.service.OdsTourTrlCarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OdsTourTrlCarInfoService")
public class OdsTourTrlCarInfoServiceImpl extends BaseServiceImp<OdsTourTrlCarInfo> implements OdsTourTrlCarInfoService {

	@Autowired
	private OdsTourTrlCarInfoDao odsTourTrlCarInfoDao;
}
