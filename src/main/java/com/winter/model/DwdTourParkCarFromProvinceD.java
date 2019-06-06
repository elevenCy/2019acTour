package com.winter.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @name DwdTourParkCarFromProvinceD
 * @Description DwdTourParkCarFromProvinceD实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DwdTourParkCarFromProvinceD")
public class DwdTourParkCarFromProvinceD{

	private static final long serialVersionUID = 1L;
	/**
	 * @name 编号
	 * @NotBlank @Length(max=10)
	 */
	private String code;
	/**
	 * @name 来源省
	 * @Length(max=64)
	 */
	private String province;
	/**
	 * @name 数量
	 * 
	 */
	private Integer number;
	/**
	 * @name 数据时间(日)YYYYMMDD
	 * @Length(max=8)
	 */
	private String dateTime;
	/**
	 * @name 创建时间
	 * 
	 */
	private Date createdate;

	/**
	 * @name 查询关键字
	 */
	private String keyWord;

    /**
     * @name 无参构造函数
     */
	public DwdTourParkCarFromProvinceD(){
	
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
 * @name 设置 来源省
 * @param province
 */
public void setProvince(String value) {
	this.province = value;
}

/**
 * @name 获得 来源省
 * @return province
 */
public String getProvince() {
	return this.province;
}
/**
 * @name 设置 数量
 * @param number
 */
public void setNumber(Integer value) {
	this.number = value;
}

/**
 * @name 获得 数量
 * @return number
 */
public Integer getNumber() {
	return this.number;
}
/**
 * @name 设置 数据时间(日)YYYYMMDD
 * @param dateTime
 */
public void setDateTime(String value) {
	this.dateTime = value;
}

/**
 * @name 获得 数据时间(日)YYYYMMDD
 * @return dateTime
 */
public String getDateTime() {
	return this.dateTime;
}
/**
 * @name 设置 创建时间
 * @param createdate
 */
public void setCreatedate(Date value) {
	this.createdate = value;
}

/**
 * @name 获得 创建时间
 * @return createdate
 */
public Date getCreatedate() {
	return this.createdate;
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

