package com.winter.service.impl;

import com.winter.mapper.DwsTourTouristNumberWDao;
import com.winter.model.DwsTourTouristNumberW;
import com.winter.service.DwsTourTouristNumberWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwsTourTouristNumberWService")
public class DwsTourTouristNumberWServiceImpl extends BaseServiceImp<DwsTourTouristNumberW> implements DwsTourTouristNumberWService {

	@Autowired
	private DwsTourTouristNumberWDao dwsTourTouristNumberWDao;
}
