package com.winter.model;

import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * @name SysApiLog
 * @Description SysApiLog实体对象
 * @author tujing
 * @date 2016-07-01
 */
@Alias("SysApiLog")
public class SysApiLog{

	private static final long serialVersionUID = 1L;
	/**
	 * @name id
	 * @primarykey 主键,要求唯一
	 * @Length(max=32)
	 */
	private String id;
	/**
	 * @name name
	 * @Length(max=100)
	 */
	private String name;
	/**
	 * @name url
	 * @Length(max=200)
	 */
	private String url;
	/**
	 * @name param
	 * @Length(max=2147483647)
	 */
	private String param;
	/**
	 * @name sendTime
	 * 
	 */
	private Date sendTime;

	/**
	 * @name 查询关键字
	 */
	private String keyWord;

    /**
     * @name 无参构造函数
     */
	public SysApiLog(){
	
	}
	
	


/**
 * @name 设置 id
 * @param id
 */
public void setId(String value) {
	this.id = value;
}

/**
 * @name 获得 id
 * @return id
 */
public String getId() {
	return this.id;
}
/**
 * @name 设置 name
 * @param name
 */
public void setName(String value) {
	this.name = value;
}

/**
 * @name 获得 name
 * @return name
 */
public String getName() {
	return this.name;
}
/**
 * @name 设置 url
 * @param url
 */
public void setUrl(String value) {
	this.url = value;
}

/**
 * @name 获得 url
 * @return url
 */
public String getUrl() {
	return this.url;
}
/**
 * @name 设置 param
 * @param param
 */
public void setParam(String value) {
	this.param = value;
}

/**
 * @name 获得 param
 * @return param
 */
public String getParam() {
	return this.param;
}
/**
 * @name 设置 sendTime
 * @param sendTime
 */
public void setSendTime(Date value) {
	this.sendTime = value;
}

/**
 * @name 获得 sendTime
 * @return sendTime
 */
public Date getSendTime() {
	return this.sendTime;
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

