package com.winter.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;



/**
 * @name DwdTourTouristNumberD
 * @Description DwdTourTouristNumberD实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DwdTourTouristNumberD")
public class DwdTourTouristNumberD  {

	private static final long serialVersionUID = 1L;
	/**
	 * @name code
	 * @primarykey 主键,要求唯一 @Length(max=6)
	 */
	private String code;
	/**
	 * @name number
	 * 
	 */
	private Integer number;

	private String gNumber;

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
	public DwdTourTouristNumberD() {

	}

	private Integer max;

	private Integer min;

	private Integer avg;

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getAvg() {
		return avg;
	}

	public void setAvg(Integer avg) {
		this.avg = avg;
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

	public String getgNumber() {
		return gNumber;
	}

	public void setgNumber(String gNumber) {
		this.gNumber = gNumber;
	}

}
