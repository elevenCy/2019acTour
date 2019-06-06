package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristNumberMDao;
import com.winter.model.DwdTourTouristNumberM;
import com.winter.service.DwdTourTouristNumberMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service("DwdTourTouristNumberMService")
public class DwdTourTouristNumberMServiceImpl extends BaseServiceImp<DwdTourTouristNumberM> implements DwdTourTouristNumberMService {

	@Autowired
	private DwdTourTouristNumberMDao dwdTourTouristNumberMDao;
}
