package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristFromCityMDao;
import com.winter.model.DwdTourTouristFromCityM;
import com.winter.service.DwdTourTouristFromCityMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwdTourTouristFromCityMService")
public class DwdTourTouristFromCityMServiceImpl extends BaseServiceImp<DwdTourTouristFromCityM> implements DwdTourTouristFromCityMService {

	@Autowired
	private DwdTourTouristFromCityMDao dwdTourTouristFromCityMDao;
}
