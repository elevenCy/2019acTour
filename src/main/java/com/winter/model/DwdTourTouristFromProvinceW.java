package com.winter.model;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.ibatis.type.Alias;


/**
 * @name DwdTourTouristFromProvinceW
 * @Description DwdTourTouristFromProvinceW实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DwdTourTouristFromProvinceW")
public class DwdTourTouristFromProvinceW {

	private static final long serialVersionUID = 1L;
	/**
	 * @name code
	 * @primarykey 主键,要求唯一
	 * @Length(max=6)
	 */
	private String code;
	/**
	 * @name province
	 * @Length(max=64)
	 */
	private String province;
	/**
	 * @name num
	 * 
	 */
	private Integer num;
	/**
	 * @name prop
	 * 
	 */
	private BigDecimal prop;
	/**
	 * @name dateTime
	 * 
	 */
	private String dateTime;
	/**
	 * @name createdate
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
	public DwdTourTouristFromProvinceW(){
	
	}
	
	


/**
 * @name 设置 code
 * @param code
 */
public void setCode(String value) {
	this.code = value;
}

/**
 * @name 获得 code
 * @return code
 */
public String getCode() {
	return this.code;
}
/**
 * @name 设置 province
 * @param province
 */
public void setProvince(String value) {
	this.province = value;
}

/**
 * @name 获得 province
 * @return province
 */
public String getProvince() {
	return this.province;
}
/**
 * @name 设置 num
 * @param num
 */
public void setNum(Integer value) {
	this.num = value;
}

/**
 * @name 获得 num
 * @return num
 */
public Integer getNum() {
	return this.num;
}
/**
 * @name 设置 prop
 * @param prop
 */
public void setProp(BigDecimal value) {
	this.prop = value;
}

/**
 * @name 获得 prop
 * @return prop
 */
public BigDecimal getProp() {
	return this.prop;
}
/**
 * @name 设置 dateTime
 * @param dateTime
 */
public void setDateTime(String value) {
	this.dateTime = value;
}

/**
 * @name 获得 dateTime
 * @return dateTime
 */
public String getDateTime() {
	return this.dateTime;
}
/**
 * @name 设置 createdate
 * @param createdate
 */
public void setCreatedate(Date value) {
	this.createdate = value;
}

/**
 * @name 获得 createdate
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

