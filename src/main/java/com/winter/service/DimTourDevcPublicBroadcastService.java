package com.winter.service;

import java.util.List;

import com.winter.model.DimTourDevcPublicBroadcast;

/**
 * @ClassName DimTourDevcPublicBroadcast
 * @Description  dimTourDevcPublicBroadcast业务处理
 * @author tujing
 * @date 2016-7-1
 * @version V1.0
 */
public interface DimTourDevcPublicBroadcastService extends BaseService<DimTourDevcPublicBroadcast>{
	public List<DimTourDevcPublicBroadcast> findCascadeResource(DimTourDevcPublicBroadcast o);
}
