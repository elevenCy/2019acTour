package com.winter.service.impl;
import java.util.List;

import com.winter.mapper.DimTourDevcPublicBroadcastDao;
import com.winter.model.DimTourDevcPublicBroadcast;
import com.winter.service.DimTourDevcPublicBroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DimTourDevcPublicBroadcastService")
public class DimTourDevcPublicBroadcastServiceImpl extends BaseServiceImp<DimTourDevcPublicBroadcast> implements DimTourDevcPublicBroadcastService {

	@Autowired
	private DimTourDevcPublicBroadcastDao dimTourDevcPublicBroadcastDao;

	@Override
	public List<DimTourDevcPublicBroadcast> findCascadeResource(DimTourDevcPublicBroadcast o){
		// TODO Auto-generated method stub
		return dimTourDevcPublicBroadcastDao.findCascadeResource(o);
	}
}
