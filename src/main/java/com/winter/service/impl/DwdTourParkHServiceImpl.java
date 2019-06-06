package com.winter.service.impl;

import com.winter.mapper.DwdTourParkHDao;
import com.winter.model.DwdTourParkH;
import com.winter.service.DwdTourParkHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DwdTourParkHService")
public class DwdTourParkHServiceImpl extends BaseServiceImp<DwdTourParkH> implements DwdTourParkHService {

	@Autowired
	private DwdTourParkHDao dwdTourParkHDao;
}
