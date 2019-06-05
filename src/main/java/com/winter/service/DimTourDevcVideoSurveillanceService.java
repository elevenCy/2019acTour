package com.winter.service;

import java.util.List;

import com.winter.model.DimTourDevcVideoSurveillance;

/**
 * @ClassName DimTourDevcVideoSurveillance
 * @Description  dimTourDevcVideoSurveillance业务处理
 * @author tujing
 * @date 2016-7-1
 * @version V1.0
 */
public interface DimTourDevcVideoSurveillanceService extends BaseService<DimTourDevcVideoSurveillance>{
	public List<DimTourDevcVideoSurveillance> findCascadeResource(DimTourDevcVideoSurveillance o);
}
