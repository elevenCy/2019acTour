package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristFromProvinceWDao;
import com.winter.model.DwdTourTouristFromProvinceW;
import com.winter.service.DwdTourTouristFromProvinceWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DwdTourTouristFromProvinceWService")
public class DwdTourTouristFromProvinceWServiceImpl extends BaseServiceImp<DwdTourTouristFromProvinceW> implements DwdTourTouristFromProvinceWService {

	@Autowired
	private DwdTourTouristFromProvinceWDao dwdTourTouristFromProvinceWDao;
}
