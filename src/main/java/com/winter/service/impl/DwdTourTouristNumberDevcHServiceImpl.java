package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristNumberDevcHDao;
import com.winter.model.DwdTourTouristNumberDevcH;
import com.winter.service.DwdTourTouristNumberDevcHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwdTourTouristNumberDevcHService")
public class DwdTourTouristNumberDevcHServiceImpl extends BaseServiceImp<DwdTourTouristNumberDevcH> implements DwdTourTouristNumberDevcHService {

	@Autowired
	private DwdTourTouristNumberDevcHDao dwdTourTouristNumberDevcHDao;
}
