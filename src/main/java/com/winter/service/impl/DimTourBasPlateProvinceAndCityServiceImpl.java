package com.winter.service.impl;

import com.winter.mapper.DimTourBasPlateProvinceAndCityDao;
import com.winter.model.DimTourBasPlateProvinceAndCity;
import com.winter.service.DimTourBasPlateProvinceAndCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service("DimTourBasPlateProvinceAndCityService")
public class DimTourBasPlateProvinceAndCityServiceImpl extends BaseServiceImp<DimTourBasPlateProvinceAndCity> implements DimTourBasPlateProvinceAndCityService {

	@Autowired
	private DimTourBasPlateProvinceAndCityDao dimTourBasPlateProvinceAndCityDao;
}
