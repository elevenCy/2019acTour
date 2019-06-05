package com.winter.mapper;

import com.winter.model.DimTourDevcWifi;

import java.util.List;

public interface DimTourDevcWifiDao  extends BaseDao<DimTourDevcWifi>{
    public List<DimTourDevcWifi> findCascadeResource(DimTourDevcWifi o);
    public DimTourDevcWifi findById(String id);

}
