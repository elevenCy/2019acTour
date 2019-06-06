package com.winter.model;

import java.util.Date;
import org.apache.ibatis.type.Alias;


/**
 * @name DwsTourTouristNumberY
 * @Description DwsTourTouristNumberY实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DwsTourTouristNumberY")
public class DwsTourTouristNumberY  {

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
	 * @name dateTime @Length(max=4)
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

	private String tbNumber;

	private String tb;

	public String getTbNumber() {
		return tbNumber;
	}

	public void setTbNumber(String tbNumber) {
		this.tbNumber = tbNumber;
	}

	public String getTb() {
		return tb;
	}

	public void setTb(String tb) {
		this.tb = tb;
	}

	/**
	 * @name 无参构造函数
	 */
	public DwsTourTouristNumberY() {

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
