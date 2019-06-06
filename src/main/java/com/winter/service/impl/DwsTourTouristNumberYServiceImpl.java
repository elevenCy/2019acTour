package com.winter.service.impl;

import com.winter.mapper.DwsTourTouristNumberYDao;
import com.winter.model.DwsTourTouristNumberY;
import com.winter.service.DwsTourTouristNumberYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DwsTourTouristNumberYService")
public class DwsTourTouristNumberYServiceImpl extends BaseServiceImp<DwsTourTouristNumberY> implements DwsTourTouristNumberYService {

	@Autowired
	private DwsTourTouristNumberYDao dwsTourTouristNumberYDao;
}
