package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristNumberDevcRtDao;
import com.winter.model.DwdTourTouristNumberDevcRt;
import com.winter.service.DwdTourTouristNumberDevcRtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DwdTourTouristNumberDevcRtService")
public class DwdTourTouristNumberDevcRtServiceImpl extends BaseServiceImp<DwdTourTouristNumberDevcRt> implements DwdTourTouristNumberDevcRtService {

	@Autowired
	private DwdTourTouristNumberDevcRtDao dwdTourTouristNumberDevcRtDao;
}
