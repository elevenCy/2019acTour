package com.winter.mapper;

import java.util.List;

import com.winter.model.DimTourDevcVideoSurveillance;


/**
 * @ClassName DimTourDevcVideoSurveillance
 * @Description  dimTourDevcVideoSurveillance数据访问
 * @author tujing
 * @date 2016-7-1
 * @version V1.0
 */
public interface DimTourDevcVideoSurveillanceDao  extends BaseDao<DimTourDevcVideoSurveillance>{
	public List<DimTourDevcVideoSurveillance> findCascadeResource(DimTourDevcVideoSurveillance o);
	public DimTourDevcVideoSurveillance findById(String id);
	
}
