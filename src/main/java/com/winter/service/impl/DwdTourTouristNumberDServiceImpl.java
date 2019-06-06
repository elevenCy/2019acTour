package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristNumberDDao;
import com.winter.model.DwdTourTouristNumberD;
import com.winter.service.DwdTourTouristNumberDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DwdTourTouristNumberDService")
public class DwdTourTouristNumberDServiceImpl extends BaseServiceImp<DwdTourTouristNumberD> implements DwdTourTouristNumberDService {

	@Autowired
	private DwdTourTouristNumberDDao dwdTourTouristNumberDDao;
}
