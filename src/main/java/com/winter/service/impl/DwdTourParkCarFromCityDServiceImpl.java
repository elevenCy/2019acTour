package com.winter.service.impl;


import com.winter.mapper.DwdTourParkCarFromCityDDao;
import com.winter.model.DwdTourParkCarFromCityD;
import com.winter.service.DwdTourParkCarFromCityDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DwdTourParkCarFromCityDService")
public class DwdTourParkCarFromCityDServiceImpl extends BaseServiceImp<DwdTourParkCarFromCityD> implements DwdTourParkCarFromCityDService {

	@Autowired
	private DwdTourParkCarFromCityDDao dwdTourParkCarFromCityDDao;
}
