package com.winter.model;

import java.util.Date;
import org.apache.ibatis.type.Alias;


/**
 * @name DwdTourParkCarW
 * @Description DwdTourParkCarW实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("DwdTourParkCarW")
public class DwdTourParkCarW {

	private static final long serialVersionUID = 1L;
	/**
	 * @name code
	 * @Length(max=6)
	 */
	private String code;
	/**
	 * @name 总车量
	 * 
	 */
	private Integer number;
	/**
	 * @name 进
	 * 
	 */
	private Integer numberIn;
	/**
	 * @name 出
	 * 
	 */
	private Integer numberOut;
	/**
	 * @name 其他类型车
	 * 
	 */
	private Integer otherIn;
	/**
	 * @name 大型车
	 * 
	 */
	private Integer bcarIn;
	/**
	 * @name 小型车
	 * 
	 */
	private Integer scarIn;
	/**
	 * @name otherOut
	 * 
	 */
	private Integer otherOut;
	/**
	 * @name bcarOut
	 * 
	 */
	private Integer bcarOut;
	/**
	 * @name scarOut
	 * 
	 */
	private Integer scarOut;
	/**
	 * @name 保留
	 * @Length(max=32)
	 */
	private String column1;
	/**
	 * @name 保留
	 * @Length(max=32)
	 */
	private String column2;
	/**
	 * @name dateTime
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

    /**
     * @name 无参构造函数
     */
	public DwdTourParkCarW(){
	
	}

	private String weeks;
    public String getWeeks() {
		return weeks;
	} 
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	private String oip;
	private String bip;
	private String sip;
	private String oop;
	private String bop;
	private String sop;
	public String getOip() {
		return oip;
	}
	public void setOip(String oip) {
		this.oip = oip;
	}
	public String getBip() {
		return bip;
	}
	public void setBip(String bip) {
		this.bip = bip;
	}
	public String getSip() {
		return sip;
	}
	public void setSip(String sip) {
		this.sip = sip;
	}
	public String getOop() {
		return oop;
	}
	public void setOop(String oop) {
		this.oop = oop;
	}
	public String getBop() {
		return bop;
	}
	public void setBop(String bop) {
		this.bop = bop;
	}
	public String getSop() {
		return sop;
	}
	public void setSop(String sop) {
		this.sop = sop;
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
 * @name 设置 总车量
 * @param number
 */
public void setNumber(Integer value) {
	this.number = value;
}

/**
 * @name 获得 总车量
 * @return number
 */
public Integer getNumber() {
	return this.number;
}
/**
 * @name 设置 进
 * @param numberIn
 */
public void setNumberIn(Integer value) {
	this.numberIn = value;
}

/**
 * @name 获得 进
 * @return numberIn
 */
public Integer getNumberIn() {
	return this.numberIn;
}
/**
 * @name 设置 出
 * @param numberOut
 */
public void setNumberOut(Integer value) {
	this.numberOut = value;
}

/**
 * @name 获得 出
 * @return numberOut
 */
public Integer getNumberOut() {
	return this.numberOut;
}
/**
 * @name 设置 其他类型车
 * @param otherIn
 */
public void setOtherIn(Integer value) {
	this.otherIn = value;
}

/**
 * @name 获得 其他类型车
 * @return otherIn
 */
public Integer getOtherIn() {
	return this.otherIn;
}
/**
 * @name 设置 大型车
 * @param bcarIn
 */
public void setBcarIn(Integer value) {
	this.bcarIn = value;
}

/**
 * @name 获得 大型车
 * @return bcarIn
 */
public Integer getBcarIn() {
	return this.bcarIn;
}
/**
 * @name 设置 小型车
 * @param scarIn
 */
public void setScarIn(Integer value) {
	this.scarIn = value;
}

/**
 * @name 获得 小型车
 * @return scarIn
 */
public Integer getScarIn() {
	return this.scarIn;
}
/**
 * @name 设置 otherOut
 * @param otherOut
 */
public void setOtherOut(Integer value) {
	this.otherOut = value;
}

/**
 * @name 获得 otherOut
 * @return otherOut
 */
public Integer getOtherOut() {
	return this.otherOut;
}
/**
 * @name 设置 bcarOut
 * @param bcarOut
 */
public void setBcarOut(Integer value) {
	this.bcarOut = value;
}

/**
 * @name 获得 bcarOut
 * @return bcarOut
 */
public Integer getBcarOut() {
	return this.bcarOut;
}
/**
 * @name 设置 scarOut
 * @param scarOut
 */
public void setScarOut(Integer value) {
	this.scarOut = value;
}

/**
 * @name 获得 scarOut
 * @return scarOut
 */
public Integer getScarOut() {
	return this.scarOut;
}
/**
 * @name 设置 保留
 * @param column1
 */
public void setColumn1(String value) {
	this.column1 = value;
}

/**
 * @name 获得 保留
 * @return column1
 */
public String getColumn1() {
	return this.column1;
}
/**
 * @name 设置 保留
 * @param column2
 */
public void setColumn2(String value) {
	this.column2 = value;
}

/**
 * @name 获得 保留
 * @return column2
 */
public String getColumn2() {
	return this.column2;
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

