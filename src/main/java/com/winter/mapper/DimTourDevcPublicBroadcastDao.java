package com.winter.mapper;

import com.winter.model.DimTourDevcPublicBroadcast;

import java.util.List;



/**
 * @ClassName DimTourDevcPublicBroadcast
 * @Description  dimTourDevcPublicBroadcast数据访问
 * @author tujing
 * @date 2016-7-1
 * @version V1.0
 */
public interface DimTourDevcPublicBroadcastDao  extends BaseDao<DimTourDevcPublicBroadcast>{
	public List<DimTourDevcPublicBroadcast> findCascadeResource(DimTourDevcPublicBroadcast o);
	public DimTourDevcPublicBroadcast findById(String id);
}
