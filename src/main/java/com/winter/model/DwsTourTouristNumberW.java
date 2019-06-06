package com.winter.model;

import java.util.Date;
import org.apache.ibatis.type.Alias;


/**
 * @name DwsTourTouristNumberW
 * @Description DwsTourTouristNumberW实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DwsTourTouristNumberW")
public class DwsTourTouristNumberW {

	private static final long serialVersionUID = 1L;
	/**
	 * @name code
	 * @NotBlank @Length(max=6)
	 */
	private String code;
	/**
	 * @name number
	 * 
	 */
	private Integer number;
	/**
	 * @name 20181101~20181105
	 * @Length(max=8)
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

	private String weeks;
    public String getWeeks() {
		return weeks;
	}




	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}




	/**
     * @name 无参构造函数
     */
	public DwsTourTouristNumberW(){
	
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
 * @name 设置 number
 * @param number
 */
public void setNumber(Integer value) {
	this.number = value;
}

/**
 * @name 获得 number
 * @return number
 */
public Integer getNumber() {
	return this.number;
}
/**
 * @name 设置 20181101~20181105
 * @param dateTime
 */
public void setDateTime(String value) {
	this.dateTime = value;
}

/**
 * @name 获得 20181101~20181105
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

