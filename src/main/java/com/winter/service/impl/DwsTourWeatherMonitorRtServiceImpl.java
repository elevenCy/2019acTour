package com.winter.service.impl;

import com.winter.mapper.DwsTourWeatherMonitorRtDao;
import com.winter.model.DwsTourWeatherMonitorRt;
import com.winter.service.DwsTourWeatherMonitorRtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwsTourWeatherMonitorRtService")
public class DwsTourWeatherMonitorRtServiceImpl extends BaseServiceImp<DwsTourWeatherMonitorRt> implements DwsTourWeatherMonitorRtService {

	@Autowired
	private DwsTourWeatherMonitorRtDao dwsTourWeatherMonitorRtDao;
}
