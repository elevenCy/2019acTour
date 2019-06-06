package com.winter.model;


import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @name DwdTourParkH
 * @Description DwdTourParkH实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DwdTourParkH")
public class DwdTourParkH  {

	private static final long serialVersionUID = 1L;
	/**
	 * @name 编号
	 * @NotBlank @Length(max=64)
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
	 * @name 小时点
	 * @NotNull 
	 */
	private Integer hour;
	/**
	 * @name 数据时间 日期格式yyyyMMdd
	 * @Length(max=8)
	 */
	private String dateTime;
	/**
	 * @name 数据创建时间
	 * 
	 */
	private Date createTime;

	/**
	 * @name 查询关键字
	 */
	private String keyWord;

    /**
     * @name 无参构造函数
     */
	public DwdTourParkH(){
	
	}
	
	


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
 * @name 设置 小时点
 * @param hour
 */
public void setHour(Integer value) {
	this.hour = value;
}

/**
 * @name 获得 小时点
 * @return hour
 */
public Integer getHour() {
	return this.hour;
}
/**
 * @name 设置 数据时间 日期格式yyyyMMdd
 * @param dateTime
 */
public void setDateTime(String value) {
	this.dateTime = value;
}

/**
 * @name 获得 数据时间 日期格式yyyyMMdd
 * @return dateTime
 */
public String getDateTime() {
	return this.dateTime;
}
/**
 * @name 设置 数据创建时间
 * @param createTime
 */
public void setCreateTime(Date value) {
	this.createTime = value;
}

/**
 * @name 获得 数据创建时间
 * @return createTime
 */
public Date getCreateTime() {
	return this.createTime;
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

