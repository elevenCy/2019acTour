package com.winter.model;

import org.apache.ibatis.type.Alias;

/**
 * @name DimTourBasPlateProvinceAndCity
 * @Description DimTourBasPlateProvinceAndCity实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DimTourBasPlateProvinceAndCity")
public class DimTourBasPlateProvinceAndCity {

	private static final long serialVersionUID = 1L;
	/**
	 * @name code
	 * @primarykey 主键,要求唯一
	 * @Length(max=32)
	 */
	private String code;
	/**
	 * @name city
	 * @Length(max=64)
	 */
	private String city;
	/**
	 * @name province
	 * @NotBlank @Length(max=64)
	 */
	private String province;

	/**
	 * @name 查询关键字
	 */
	private String keyWord;

    /**
     * @name 无参构造函数
     */
	public DimTourBasPlateProvinceAndCity(){
	
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
 * @name 设置 city
 * @param city
 */
public void setCity(String value) {
	this.city = value;
}

/**
 * @name 获得 city
 * @return city
 */
public String getCity() {
	return this.city;
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

