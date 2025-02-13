
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 车位收藏
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/cheweiCollection")
public class CheweiCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(CheweiCollectionController.class);

    private static final String TABLE_NAME = "cheweiCollection";

    @Autowired
    private CheweiCollectionService cheweiCollectionService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaozuorizhiService caozuorizhiService;//操作日志
    @Autowired
    private CheweiService cheweiService;//车位
    @Autowired
    private CheweiLiuyanService cheweiLiuyanService;//车位留言
    @Autowired
    private CheweiZulinService cheweiZulinService;//车位租赁订单
    @Autowired
    private CheweiyuyueService cheweiyuyueService;//车位预约
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private LiuyanService liuyanService;//留言板
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = cheweiCollectionService.queryPage(params);

        //字典表数据转换
        List<CheweiCollectionView> list =(List<CheweiCollectionView>)page.getList();
        for(CheweiCollectionView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"列表查询",list.toString());
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CheweiCollectionEntity cheweiCollection = cheweiCollectionService.selectById(id);
        if(cheweiCollection !=null){
            //entity转view
            CheweiCollectionView view = new CheweiCollectionView();
            BeanUtils.copyProperties( cheweiCollection , view );//把实体数据重构到view中
            //级联表 车位
            //级联表
            CheweiEntity chewei = cheweiService.selectById(cheweiCollection.getCheweiId());
            if(chewei != null){
            BeanUtils.copyProperties( chewei , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setCheweiId(chewei.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(cheweiCollection.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"单条数据查看",view.toString());
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody CheweiCollectionEntity cheweiCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,cheweiCollection:{}",this.getClass().getName(),cheweiCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            cheweiCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<CheweiCollectionEntity> queryWrapper = new EntityWrapper<CheweiCollectionEntity>()
            .eq("chewei_id", cheweiCollection.getCheweiId())
            .eq("yonghu_id", cheweiCollection.getYonghuId())
            .eq("chewei_collection_types", cheweiCollection.getCheweiCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheweiCollectionEntity cheweiCollectionEntity = cheweiCollectionService.selectOne(queryWrapper);
        if(cheweiCollectionEntity==null){
            cheweiCollection.setInsertTime(new Date());
            cheweiCollection.setCreateTime(new Date());
            cheweiCollectionService.insert(cheweiCollection);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",cheweiCollection.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CheweiCollectionEntity cheweiCollection, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,cheweiCollection:{}",this.getClass().getName(),cheweiCollection.toString());
        CheweiCollectionEntity oldCheweiCollectionEntity = cheweiCollectionService.selectById(cheweiCollection.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            cheweiCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            cheweiCollectionService.updateById(cheweiCollection);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(cheweiCollection, oldCheweiCollectionEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<CheweiCollectionEntity> oldCheweiCollectionList =cheweiCollectionService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        cheweiCollectionService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldCheweiCollectionList.toString());
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<CheweiCollectionEntity> cheweiCollectionList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            CheweiCollectionEntity cheweiCollectionEntity = new CheweiCollectionEntity();
//                            cheweiCollectionEntity.setCheweiId(Integer.valueOf(data.get(0)));   //车位 要改的
//                            cheweiCollectionEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            cheweiCollectionEntity.setCheweiCollectionTypes(Integer.valueOf(data.get(0)));   //类型 要改的
//                            cheweiCollectionEntity.setInsertTime(date);//时间
//                            cheweiCollectionEntity.setCreateTime(date);//时间
                            cheweiCollectionList.add(cheweiCollectionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        cheweiCollectionService.insertBatch(cheweiCollectionList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",cheweiCollectionList.toString());
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = cheweiCollectionService.queryPage(params);

        //字典表数据转换
        List<CheweiCollectionView> list =(List<CheweiCollectionView>)page.getList();
        for(CheweiCollectionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"列表查询",list.toString());
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CheweiCollectionEntity cheweiCollection = cheweiCollectionService.selectById(id);
            if(cheweiCollection !=null){


                //entity转view
                CheweiCollectionView view = new CheweiCollectionView();
                BeanUtils.copyProperties( cheweiCollection , view );//把实体数据重构到view中

                //级联表
                    CheweiEntity chewei = cheweiService.selectById(cheweiCollection.getCheweiId());
                if(chewei != null){
                    BeanUtils.copyProperties( chewei , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheweiId(chewei.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(cheweiCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"单条数据查看",view.toString());
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody CheweiCollectionEntity cheweiCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,cheweiCollection:{}",this.getClass().getName(),cheweiCollection.toString());
        Wrapper<CheweiCollectionEntity> queryWrapper = new EntityWrapper<CheweiCollectionEntity>()
            .eq("chewei_id", cheweiCollection.getCheweiId())
            .eq("yonghu_id", cheweiCollection.getYonghuId())
            .eq("chewei_collection_types", cheweiCollection.getCheweiCollectionTypes())
//            .notIn("chewei_collection_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheweiCollectionEntity cheweiCollectionEntity = cheweiCollectionService.selectOne(queryWrapper);
        if(cheweiCollectionEntity==null){
            cheweiCollection.setInsertTime(new Date());
            cheweiCollection.setCreateTime(new Date());
        cheweiCollectionService.insert(cheweiCollection);

            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"前台新增",cheweiCollection.toString());
            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }

}

