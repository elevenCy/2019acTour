package com.winter.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;



/**
 * @name DwdTourTouristNumberRt
 * @Description DwdTourTouristNumberRt实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DwdTourTouristNumberRt")
public class DwdTourTouristNumberRt  {

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
	/**
	 * @name createdate
	 * 
	 */
	private Date createdate;

	private Integer carrying;
	/**
	 * @name 查询关键字
	 */
	private String keyWord;

	/**
	 * @name 无参构造函数
	 */
	public DwdTourTouristNumberRt() {

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

	public Integer getCarrying() {
		return carrying;
	}

	public void setCarrying(Integer carrying) {
		this.carrying = carrying;
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
