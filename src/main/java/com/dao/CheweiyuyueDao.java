package com.dao;

import com.entity.CheweiyuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CheweiyuyueView;

/**
 * 车位预约 Dao 接口
 *
 * @author 
 */
public interface CheweiyuyueDao extends BaseMapper<CheweiyuyueEntity> {

   List<CheweiyuyueView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
