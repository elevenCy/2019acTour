package com.winter.service.impl;

import com.winter.mapper.DwdTourParkCarWDao;
import com.winter.model.DwdTourParkCarW;
import com.winter.service.DwdTourParkCarWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwdTourParkCarWService")
public class DwdTourParkCarWServiceImpl extends BaseServiceImp<DwdTourParkCarW> implements DwdTourParkCarWService {

	@Autowired
	private DwdTourParkCarWDao dwdTourParkCarWDao;
}
