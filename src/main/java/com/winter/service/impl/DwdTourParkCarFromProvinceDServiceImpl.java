package com.winter.service.impl;


import com.winter.mapper.DwdTourParkCarFromProvinceDDao;
import com.winter.model.DwdTourParkCarFromProvinceD;
import com.winter.service.DwdTourParkCarFromProvinceDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("DwdTourParkCarFromProvinceDService")
public class DwdTourParkCarFromProvinceDServiceImpl extends BaseServiceImp<DwdTourParkCarFromProvinceD> implements DwdTourParkCarFromProvinceDService {

	@Autowired
	private DwdTourParkCarFromProvinceDDao dwdTourParkCarFromProvinceDDao;
}
