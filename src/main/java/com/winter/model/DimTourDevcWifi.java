package com.winter.model;

import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("DimTourDevcWifi")
public class DimTourDevcWifi{

    private static final long serialVersionUID = 1L;
    /**
     * @name 数据库内的唯一主键
     * @primarykey 主键,要求唯一
     * @Length(max=32)
     */
    private String id;
    /**
     * @name resourceId
     * @Length(max=32)
     */
    private String resourceId;
    /**
     * @name name
     * @NotBlank @Length(max=64)
     */
    private String name;
    /**
     * @name 设备id
     * @Length(max=64)
     */
    private String code;
    /**
     * @name ip
     * @Length(max=64)
     */
    private String ip;
    /**
     * @name mac
     * @Length(max=64)
     */
    private String mac;
    /**
     * @name status
     * @NotNull
     */
    private Integer status;
    /**
     * @name remark
     * @Length(max=255)
     */
    private String remark;
    /**
     * @name sort
     *
     */
    private Integer sort;
    /**
     * @name createTime
     * @NotNull
     */
    private Date createTime;
    /**
     * @name 更新时间即数据时间
     * @NotNull
     */
    private Date updateTime;

    /**
     * @name 查询关键字
     */
    private String keyWord;

    /**
     * @name 无参构造函数
     */
    public DimTourDevcWifi(){

    }


    /**
     * @name 设置 数据库内的唯一主键
     * @param value
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * @name 获得 数据库内的唯一主键
     * @return id
     */
    public String getId() {
        return this.id;
    }
    /**
     * @name 设置 resourceId
     * @param value
     */
    public void setResourceId(String value) {
        this.resourceId = value;
    }

    /**
     * @name 获得 resourceId
     * @return resourceId
     */
    public String getResourceId() {
        return this.resourceId;
    }
    /**
     * @name 设置 name
     * @param value
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
     * @name 设置 设备id
     * @param value
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * @name 获得 设备id
     * @return code
     */
    public String getCode() {
        return this.code;
    }
    /**
     * @name 设置 ip
     * @param value
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * @name 获得 ip
     * @return ip
     */
    public String getIp() {
        return this.ip;
    }
    /**
     * @name 设置 mac
     * @param value
     */
    public void setMac(String value) {
        this.mac = value;
    }

    /**
     * @name 获得 mac
     * @return mac
     */
    public String getMac() {
        return this.mac;
    }
    /**
     * @name 设置 status
     * @param value
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * @name 获得 status
     * @return status
     */
    public Integer getStatus() {
        return this.status;
    }
    /**
     * @name 设置 remark
     * @param value
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * @name 获得 remark
     * @return remark
     */
    public String getRemark() {
        return this.remark;
    }
    /**
     * @name 设置 sort
     * @param value
     */
    public void setSort(Integer value) {
        this.sort = value;
    }

    /**
     * @name 获得 sort
     * @return sort
     */
    public Integer getSort() {
        return this.sort;
    }
    /**
     * @name 设置 createTime
     * @param value
     */
    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    /**
     * @name 获得 createTime
     * @return createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }
    /**
     * @name 设置 更新时间即数据时间
     * @param value
     */
    public void setUpdateTime(Date value) {
        this.updateTime = value;
    }

    /**
     * @name 获得 更新时间即数据时间
     * @return updateTime
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * @name 设置 keyWord
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