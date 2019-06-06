package com.winter.service.impl;

import com.winter.mapper.DwdTourParkCarYDao;
import com.winter.model.DwdTourParkCarY;
import com.winter.service.DwdTourParkCarYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DwdTourParkCarYService")
public class DwdTourParkCarYServiceImpl extends BaseServiceImp<DwdTourParkCarY> implements DwdTourParkCarYService {

	@Autowired
	private DwdTourParkCarYDao dwdTourParkCarYDao;
}
