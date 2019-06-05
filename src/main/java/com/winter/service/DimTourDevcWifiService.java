package com.winter.service;

import com.winter.model.DimTourDevcWifi;

import java.util.List;

/**
 * @ClassName DimTourDevcPublicBroadcast
 * @Description  dimTourDevcPublicBroadcast业务处理
 * @author tujing
 * @date 2016-7-1
 * @version V1.0
 */
public interface DimTourDevcWifiService extends BaseService<DimTourDevcWifi>{
	public List<DimTourDevcWifi> findCascadeResource(DimTourDevcWifi o);
}
