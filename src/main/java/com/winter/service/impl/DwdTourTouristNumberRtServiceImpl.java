package com.winter.service.impl;

import com.winter.mapper.DwdTourTouristNumberRtDao;
import com.winter.model.DwdTourTouristNumberRt;
import com.winter.service.DwdTourTouristNumberRtService;
import com.winter.service.impl.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwdTourTouristNumberRtService")
public class DwdTourTouristNumberRtServiceImpl extends BaseServiceImp<DwdTourTouristNumberRt> implements DwdTourTouristNumberRtService {

	@Autowired
	private DwdTourTouristNumberRtDao dwdTourTouristNumberRtDao;
}
