package com.winter.service.impl;

import com.winter.mapper.DwsTourTouristNumberQDao;
import com.winter.model.DwsTourTouristNumberQ;
import com.winter.service.DwsTourTouristNumberQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwsTourTouristNumberQService")
public class DwsTourTouristNumberQServiceImpl extends BaseServiceImp<DwsTourTouristNumberQ> implements DwsTourTouristNumberQService {

	@Autowired
	private DwsTourTouristNumberQDao dwsTourTouristNumberQDao;
}
