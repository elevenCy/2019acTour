package com.winter.service.impl;

import com.winter.mapper.DwdTourParkMonitorRtDao;
import com.winter.model.DwdTourParkMonitorRt;
import com.winter.service.DwdTourParkMonitorRtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DwdTourParkMonitorRtService")
public class DwdTourParkMonitorRtServiceImpl extends BaseServiceImp<DwdTourParkMonitorRt> implements DwdTourParkMonitorRtService {

	@Autowired
	private DwdTourParkMonitorRtDao dwdTourParkMonitorRtDao;

	@Override
	public List<DwdTourParkMonitorRt> findCascadeResource(DwdTourParkMonitorRt o) {
		// TODO Auto-generated method stub
		return dwdTourParkMonitorRtDao.findCascadeResource(o);
	}
}
