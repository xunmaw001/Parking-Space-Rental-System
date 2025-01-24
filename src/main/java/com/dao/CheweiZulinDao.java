package com.dao;

import com.entity.CheweiZulinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CheweiZulinView;

/**
 * 车位租赁订单 Dao 接口
 *
 * @author 
 */
public interface CheweiZulinDao extends BaseMapper<CheweiZulinEntity> {

   List<CheweiZulinView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
