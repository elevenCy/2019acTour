package com.winter.model;

import org.apache.ibatis.type.Alias;


/**
 * @name OdsTourTrlCarInfo
 * @Description OdsTourTrlCarInfo实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("OdsTourTrlCarInfo")
public class OdsTourTrlCarInfo{

	private static final long serialVersionUID = 1L;
	/**
	 * @name uniqueNo
	 * @primarykey 主键,要求唯一
	 * @Length(max=64)
	 */
	private String uniqueNo;
	/**
	 * @name 0、入场过车;1、出场过车
	 * 
	 */
	private Integer direction;
	/**
	 * @name 车牌号码
	 * @Length(max=64)
	 */
	private String plateNo;
	/**
	 * @name cardNo
	 * @Length(max=64)
	 */
	private String cardNo;
	/**
	 * @name yyyy-MM-dd HH:mm:ss
	 * @Length(max=64)
	 */
	private String passTime;
	/**
	 * @name 0、其他车;1、小型车;2、大型车
	 * 
	 */
	private Integer vehType;
	/**
	 * @name 0、其他;1、白色;2、灰（银）色;3、银（灰）色;4、黑色;5、红色;6、深红色;7、蓝色;8、黄色;9、绿色;10、棕色;11、粉色;12、紫色
	 * 
	 */
	private Integer vehColor;
	/**
	 * @name operatorName
	 * @Length(max=64)
	 */
	private String operatorName;
	/**
	 * @name terminalNo
	 * @Length(max=64)
	 */
	private String terminalNo;
	/**
	 * @name gateName
	 * @Length(max=64)
	 */
	private String gateName;
	/**
	 * @name laneName
	 * @Length(max=64)
	 */
	private String laneName;
	/**
	 * @name passType
	 * @Length(max=64)
	 */
	private String passType;
	/**
	 * @name yyyy-MM-dd HH:mm:ss
	 * @Length(max=64)
	 */
	private String inPassTime;
	/**
	 * @name inUniqueNo
	 * @Length(max=64)
	 */
	private String inUniqueNo;
	/**
	 * @name shouldPay
	 * 
	 */
	private Integer shouldPay;
	/**
	 * @name actualPay
	 * 
	 */
	private Integer actualPay;
	/**
	 * @name picFilePath
	 * @Length(max=255)
	 */
	private String picFilePath;
	/**
	 * @name plateFilePath
	 * @Length(max=255)
	 */
	private String plateFilePath;

	/**
	 * @name 查询关键字
	 */
	private String keyWord;

    /**
     * @name 无参构造函数
     */
	public OdsTourTrlCarInfo(){
	
	}
	
	


/**
 * @name 设置 uniqueNo
 * @param uniqueNo
 */
public void setUniqueNo(String value) {
	this.uniqueNo = value;
}

/**
 * @name 获得 uniqueNo
 * @return uniqueNo
 */
public String getUniqueNo() {
	return this.uniqueNo;
}
/**
 * @name 设置 0、入场过车;1、出场过车
 * @param direction
 */
public void setDirection(Integer value) {
	this.direction = value;
}

/**
 * @name 获得 0、入场过车;1、出场过车
 * @return direction
 */
public Integer getDirection() {
	return this.direction;
}
/**
 * @name 设置 车牌号码
 * @param plateNo
 */
public void setPlateNo(String value) {
	this.plateNo = value;
}

/**
 * @name 获得 车牌号码
 * @return plateNo
 */
public String getPlateNo() {
	return this.plateNo;
}
/**
 * @name 设置 cardNo
 * @param cardNo
 */
public void setCardNo(String value) {
	this.cardNo = value;
}

/**
 * @name 获得 cardNo
 * @return cardNo
 */
public String getCardNo() {
	return this.cardNo;
}
/**
 * @name 设置 yyyy-MM-dd HH:mm:ss
 * @param passTime
 */
public void setPassTime(String value) {
	this.passTime = value;
}

/**
 * @name 获得 yyyy-MM-dd HH:mm:ss
 * @return passTime
 */
public String getPassTime() {
	return this.passTime;
}
/**
 * @name 设置 0、其他车;1、小型车;2、大型车
 * @param vehType
 */
public void setVehType(Integer value) {
	this.vehType = value;
}

/**
 * @name 获得 0、其他车;1、小型车;2、大型车
 * @return vehType
 */
public Integer getVehType() {
	return this.vehType;
}
/**
 * @name 设置 0、其他;1、白色;2、灰（银）色;3、银（灰）色;4、黑色;5、红色;6、深红色;7、蓝色;8、黄色;9、绿色;10、棕色;11、粉色;12、紫色
 * @param vehColor
 */
public void setVehColor(Integer value) {
	this.vehColor = value;
}

/**
 * @name 获得 0、其他;1、白色;2、灰（银）色;3、银（灰）色;4、黑色;5、红色;6、深红色;7、蓝色;8、黄色;9、绿色;10、棕色;11、粉色;12、紫色
 * @return vehColor
 */
public Integer getVehColor() {
	return this.vehColor;
}
/**
 * @name 设置 operatorName
 * @param operatorName
 */
public void setOperatorName(String value) {
	this.operatorName = value;
}

/**
 * @name 获得 operatorName
 * @return operatorName
 */
public String getOperatorName() {
	return this.operatorName;
}
/**
 * @name 设置 terminalNo
 * @param terminalNo
 */
public void setTerminalNo(String value) {
	this.terminalNo = value;
}

/**
 * @name 获得 terminalNo
 * @return terminalNo
 */
public String getTerminalNo() {
	return this.terminalNo;
}
/**
 * @name 设置 gateName
 * @param gateName
 */
public void setGateName(String value) {
	this.gateName = value;
}

/**
 * @name 获得 gateName
 * @return gateName
 */
public String getGateName() {
	return this.gateName;
}
/**
 * @name 设置 laneName
 * @param laneName
 */
public void setLaneName(String value) {
	this.laneName = value;
}

/**
 * @name 获得 laneName
 * @return laneName
 */
public String getLaneName() {
	return this.laneName;
}
/**
 * @name 设置 passType
 * @param passType
 */
public void setPassType(String value) {
	this.passType = value;
}

/**
 * @name 获得 passType
 * @return passType
 */
public String getPassType() {
	return this.passType;
}
/**
 * @name 设置 yyyy-MM-dd HH:mm:ss
 * @param inPassTime
 */
public void setInPassTime(String value) {
	this.inPassTime = value;
}

/**
 * @name 获得 yyyy-MM-dd HH:mm:ss
 * @return inPassTime
 */
public String getInPassTime() {
	return this.inPassTime;
}
/**
 * @name 设置 inUniqueNo
 * @param inUniqueNo
 */
public void setInUniqueNo(String value) {
	this.inUniqueNo = value;
}

/**
 * @name 获得 inUniqueNo
 * @return inUniqueNo
 */
public String getInUniqueNo() {
	return this.inUniqueNo;
}
/**
 * @name 设置 shouldPay
 * @param shouldPay
 */
public void setShouldPay(Integer value) {
	this.shouldPay = value;
}

/**
 * @name 获得 shouldPay
 * @return shouldPay
 */
public Integer getShouldPay() {
	return this.shouldPay;
}
/**
 * @name 设置 actualPay
 * @param actualPay
 */
public void setActualPay(Integer value) {
	this.actualPay = value;
}

/**
 * @name 获得 actualPay
 * @return actualPay
 */
public Integer getActualPay() {
	return this.actualPay;
}
/**
 * @name 设置 picFilePath
 * @param picFilePath
 */
public void setPicFilePath(String value) {
	this.picFilePath = value;
}

/**
 * @name 获得 picFilePath
 * @return picFilePath
 */
public String getPicFilePath() {
	return this.picFilePath;
}
/**
 * @name 设置 plateFilePath
 * @param plateFilePath
 */
public void setPlateFilePath(String value) {
	this.plateFilePath = value;
}

/**
 * @name 获得 plateFilePath
 * @return plateFilePath
 */
public String getPlateFilePath() {
	return this.plateFilePath;
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

