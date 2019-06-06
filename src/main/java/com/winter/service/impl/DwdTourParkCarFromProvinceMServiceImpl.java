package com.winter.service.impl;

import com.winter.mapper.DwdTourParkCarFromProvinceMDao;
import com.winter.model.DwdTourParkCarFromProvinceM;
import com.winter.service.DwdTourParkCarFromProvinceMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DwdTourParkCarFromProvinceMService")
public class DwdTourParkCarFromProvinceMServiceImpl extends BaseServiceImp<DwdTourParkCarFromProvinceM> implements DwdTourParkCarFromProvinceMService {

	@Autowired
	private DwdTourParkCarFromProvinceMDao dwdTourParkCarFromProvinceMDao;
}
