package com.winter.model;



import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @name DwdTourParkMonitorRt
 * @Description DwdTourParkMonitorRt实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DwdTourParkMonitorRt")
public class DwdTourParkMonitorRt{

	private static final long serialVersionUID = 1L;
	/**
	 * @name id
	 * @primarykey 主键,要求唯一
	 * @Length(max=32)
	 */
	private String id;
	/**
	 * @name resourceId
	 * @Length(max=32)
	 */
	private String resourceId;
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getResourceId() {
		return resourceId;
	}




	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @name name
	 * @NotBlank @Length(max=64)
	 */
	/**
	 * @name 编号
	 * @primarykey 主键,要求唯一
	 * @Length(max=64)
	 */
	private String code;
	/**
	 * @name 停车场名称
	 * @NotBlank @Length(max=64)
	 */
	private String objectname;
	/**
	 * @name 停车场编号
	 * @Length(max=64)
	 */
	private String objectcode;
	/**
	 * @name 总车位数
	 * 
	 */
	private Integer allnum;
	/**
	 * @name 剩余车位数
	 * 
	 */
	private Integer residualNumber;
	/**
	 * @name 时间
	 * 
	 */
	private Date dateTime;

	/**
	 * @name 查询关键字
	 */
	private String keyWord;


/**
 * @name 设置 编号
 * @param code
 */
public void setCode(String value) {
	this.code = value;
}

/**
 * @name 获得 编号
 * @return code
 */
public String getCode() {
	return this.code;
}
/**
 * @name 设置 停车场名称
 * @param objectname
 */
public void setObjectname(String value) {
	this.objectname = value;
}

/**
 * @name 获得 停车场名称
 * @return objectname
 */
public String getObjectname() {
	return this.objectname;
}
/**
 * @name 设置 停车场编号
 * @param objectcode
 */
public void setObjectcode(String value) {
	this.objectcode = value;
}

/**
 * @name 获得 停车场编号
 * @return objectcode
 */
public String getObjectcode() {
	return this.objectcode;
}
/**
 * @name 设置 总车位数
 * @param allnum
 */
public void setAllnum(Integer value) {
	this.allnum = value;
}

/**
 * @name 获得 总车位数
 * @return allnum
 */
public Integer getAllnum() {
	return this.allnum;
}
/**
 * @name 设置 剩余车位数
 * @param residualNumber
 */
public void setResidualNumber(Integer value) {
	this.residualNumber = value;
}

/**
 * @name 获得 剩余车位数
 * @return residualNumber
 */
public Integer getResidualNumber() {
	return this.residualNumber;
}
/**
 * @name 设置 时间
 * @param dateTime
 */
public void setDateTime(Date value) {
	this.dateTime = value;
}

/**
 * @name 获得 时间
 * @return dateTime
 */
public Date getDateTime() {
	return this.dateTime;
}

	/**
	 * @name 设置 keyWord
	 * @param keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * @name 获得 keyWord
	 * @return keyWord
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}

