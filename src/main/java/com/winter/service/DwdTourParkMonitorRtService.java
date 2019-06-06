package com.winter.service;

import java.util.List;

import com.winter.model.DwdTourParkMonitorRt;

/**
 * @ClassName DwdTourParkMonitorRt
 * @Description  dwdTourParkMonitorRt业务处理
 * @author tujing
 * @date 2016-7-1
 * @version V1.0
 */
public interface DwdTourParkMonitorRtService extends BaseService<DwdTourParkMonitorRt> {
	public List<DwdTourParkMonitorRt> findCascadeResource(DwdTourParkMonitorRt o);	
}
