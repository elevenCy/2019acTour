package com.winter.service.impl;

import com.winter.mapper.DwdTourParkCarFromCityMDao;
import com.winter.model.DwdTourParkCarFromCityM;
import com.winter.service.DwdTourParkCarFromCityMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DwdTourParkCarFromCityMService")
public class DwdTourParkCarFromCityMServiceImpl extends BaseServiceImp<DwdTourParkCarFromCityM> implements DwdTourParkCarFromCityMService {

	@Autowired
	private DwdTourParkCarFromCityMDao dwdTourParkCarFromCityMDao;
}
