package com.winter.service.impl;

import com.winter.mapper.DwdTourParkCarQDao;
import com.winter.model.DwdTourParkCarQ;
import com.winter.service.DwdTourParkCarQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DwdTourParkCarQService")
public class DwdTourParkCarQServiceImpl extends BaseServiceImp<DwdTourParkCarQ> implements DwdTourParkCarQService {

	@Autowired
	private DwdTourParkCarQDao dwdTourParkCarQDao;
}
