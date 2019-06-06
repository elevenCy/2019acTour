package com.winter.service.impl;


import com.winter.mapper.DwdTourParkCarDDao;
import com.winter.model.DwdTourParkCarD;
import com.winter.service.DwdTourParkCarDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DwdTourParkCarDService")
public class DwdTourParkCarDServiceImpl extends BaseServiceImp<DwdTourParkCarD> implements DwdTourParkCarDService {

	@Autowired
	private DwdTourParkCarDDao dwdTourParkCarDDao;
}
