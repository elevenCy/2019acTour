package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristFromCityWDao;
import com.winter.model.DwdTourTouristFromCityW;
import com.winter.service.DwdTourTouristFromCityWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DwdTourTouristFromCityWService")
public class DwdTourTouristFromCityWServiceImpl extends BaseServiceImp<DwdTourTouristFromCityW> implements DwdTourTouristFromCityWService {

	@Autowired
	private DwdTourTouristFromCityWDao dwdTourTouristFromCityWDao;
}
