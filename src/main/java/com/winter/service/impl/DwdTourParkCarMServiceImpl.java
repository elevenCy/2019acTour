package com.winter.service.impl;

import com.winter.mapper.DwdTourParkCarMDao;
import com.winter.model.DwdTourParkCarM;
import com.winter.service.DwdTourParkCarMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwdTourParkCarMService")
public class DwdTourParkCarMServiceImpl extends BaseServiceImp<DwdTourParkCarM> implements DwdTourParkCarMService {

	@Autowired
	private DwdTourParkCarMDao dwdTourParkCarMDao;
}
