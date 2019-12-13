package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristNumberDevcMiddleDao;
import com.winter.model.DwdTourTouristNumberDevcMiddle;
import com.winter.service.DwdTourTouristNumberDevcMiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service("DwdTourTouristNumberDevcMiddleService")
public class DwdTourTouristNumberDevcMiddleServiceImpl extends BaseServiceImp<DwdTourTouristNumberDevcMiddle> implements DwdTourTouristNumberDevcMiddleService {

	@Autowired
	private DwdTourTouristNumberDevcMiddleDao dwdTourTouristNumberDevcMiddleDao;
}
