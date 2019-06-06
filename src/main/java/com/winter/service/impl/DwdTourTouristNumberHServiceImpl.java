package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristNumberHDao;
import com.winter.model.DwdTourTouristNumberH;
import com.winter.service.DwdTourTouristNumberHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwdTourTouristNumberHService")
public class DwdTourTouristNumberHServiceImpl extends BaseServiceImp<DwdTourTouristNumberH> implements DwdTourTouristNumberHService {

	@Autowired
	private DwdTourTouristNumberHDao dwdTourTouristNumberHDao;
}
