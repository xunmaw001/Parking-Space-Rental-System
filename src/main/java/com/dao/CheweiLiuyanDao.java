package com.dao;

import com.entity.CheweiLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CheweiLiuyanView;

/**
 * 车位留言 Dao 接口
 *
 * @author 
 */
public interface CheweiLiuyanDao extends BaseMapper<CheweiLiuyanEntity> {

   List<CheweiLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
