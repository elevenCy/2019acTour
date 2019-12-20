package com.winter.service.impl;

import com.winter.mapper.SysApiLogDao;
import com.winter.model.SysApiLog;
import com.winter.service.SysApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service("SysApiLogService")
public class SysApiLogServiceImpl extends BaseServiceImp<SysApiLog> implements SysApiLogService {

	@Autowired
	private SysApiLogDao sysApiLogDao;
}
