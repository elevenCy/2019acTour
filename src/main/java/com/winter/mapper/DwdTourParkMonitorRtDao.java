package com.winter.mapper;
import com.winter.model.DwdTourParkMonitorRt;

import java.util.List;


/**
 * @ClassName DwdTourParkMonitorRt
 * @Description  dwdTourParkMonitorRt数据访问
 * @author tujing
 * @date 2016-7-1
 * @version V1.0
 */
public interface DwdTourParkMonitorRtDao  extends BaseDao<DwdTourParkMonitorRt> {
	public List<DwdTourParkMonitorRt> findCascadeResource(DwdTourParkMonitorRt o);	
}
