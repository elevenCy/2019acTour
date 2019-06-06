package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristFromProvinceMDao;
import com.winter.model.DwdTourTouristFromProvinceM;
import com.winter.service.DwdTourTouristFromProvinceMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwdTourTouristFromProvinceMService")
public class DwdTourTouristFromProvinceMServiceImpl extends BaseServiceImp<DwdTourTouristFromProvinceM> implements DwdTourTouristFromProvinceMService {

	@Autowired
	private DwdTourTouristFromProvinceMDao dwdTourTouristFromProvinceMDao;
}
