
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
 * 车位预约
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/cheweiyuyue")
public class CheweiyuyueController {
    private static final Logger logger = LoggerFactory.getLogger(CheweiyuyueController.class);

    private static final String TABLE_NAME = "cheweiyuyue";

    @Autowired
    private CheweiyuyueService cheweiyuyueService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaozuorizhiService caozuorizhiService;//操作日志
    @Autowired
    private CheweiService cheweiService;//车位
    @Autowired
    private CheweiCollectionService cheweiCollectionService;//车位收藏
    @Autowired
    private CheweiLiuyanService cheweiLiuyanService;//车位留言
    @Autowired
    private CheweiZulinService cheweiZulinService;//车位租赁订单
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
        PageUtils page = cheweiyuyueService.queryPage(params);

        //字典表数据转换
        List<CheweiyuyueView> list =(List<CheweiyuyueView>)page.getList();
        for(CheweiyuyueView c:list){
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
        CheweiyuyueEntity cheweiyuyue = cheweiyuyueService.selectById(id);
        if(cheweiyuyue !=null){
            //entity转view
            CheweiyuyueView view = new CheweiyuyueView();
            BeanUtils.copyProperties( cheweiyuyue , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(cheweiyuyue.getYonghuId());
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
    public R save(@RequestBody CheweiyuyueEntity cheweiyuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,cheweiyuyue:{}",this.getClass().getName(),cheweiyuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            cheweiyuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<CheweiyuyueEntity> queryWrapper = new EntityWrapper<CheweiyuyueEntity>()
            .eq("yonghu_id", cheweiyuyue.getYonghuId())
            .eq("cheweiyuyue_name", cheweiyuyue.getCheweiyuyueName())
            .eq("chewei_types", cheweiyuyue.getCheweiTypes())
            .eq("cheweiyuyue_yuefen", cheweiyuyue.getCheweiyuyueYuefen())
            .in("cheweiyuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheweiyuyueEntity cheweiyuyueEntity = cheweiyuyueService.selectOne(queryWrapper);
        if(cheweiyuyueEntity==null){
            cheweiyuyue.setInsertTime(new Date());
            cheweiyuyue.setCheweiyuyueYesnoTypes(1);
            cheweiyuyue.setCreateTime(new Date());
            cheweiyuyueService.insert(cheweiyuyue);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",cheweiyuyue.toString());
            return R.ok();
        }else {
            if(cheweiyuyueEntity.getCheweiyuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(cheweiyuyueEntity.getCheweiyuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CheweiyuyueEntity cheweiyuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,cheweiyuyue:{}",this.getClass().getName(),cheweiyuyue.toString());
        CheweiyuyueEntity oldCheweiyuyueEntity = cheweiyuyueService.selectById(cheweiyuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            cheweiyuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            cheweiyuyueService.updateById(cheweiyuyue);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(cheweiyuyue, oldCheweiyuyueEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody CheweiyuyueEntity cheweiyuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,cheweiyuyueEntity:{}",this.getClass().getName(),cheweiyuyueEntity.toString());

        CheweiyuyueEntity oldCheweiyuyue = cheweiyuyueService.selectById(cheweiyuyueEntity.getId());//查询原先数据

//        if(cheweiyuyueEntity.getCheweiyuyueYesnoTypes() == 2){//通过
//            cheweiyuyueEntity.setCheweiyuyueTypes();
//        }else if(cheweiyuyueEntity.getCheweiyuyueYesnoTypes() == 3){//拒绝
//            cheweiyuyueEntity.setCheweiyuyueTypes();
//        }
        cheweiyuyueEntity.setCheweiyuyueShenheTime(new Date());//审核时间
        cheweiyuyueService.updateById(cheweiyuyueEntity);//审核

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"审核数据","审核"+oldCheweiyuyue+"数据,审核结果是"+(cheweiyuyueEntity.getCheweiyuyueYesnoTypes()==2?"通过":"拒绝"));
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<CheweiyuyueEntity> oldCheweiyuyueList =cheweiyuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        cheweiyuyueService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldCheweiyuyueList.toString());
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
            List<CheweiyuyueEntity> cheweiyuyueList = new ArrayList<>();//上传的东西
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
                            CheweiyuyueEntity cheweiyuyueEntity = new CheweiyuyueEntity();
//                            cheweiyuyueEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            cheweiyuyueEntity.setCheweiyuyueUuidNumber(data.get(0));                    //车位预约编号 要改的
//                            cheweiyuyueEntity.setCheweiyuyueName(data.get(0));                    //车位预约名称 要改的
//                            cheweiyuyueEntity.setCheweiTypes(Integer.valueOf(data.get(0)));   //车位类型 要改的
//                            cheweiyuyueEntity.setCheweiyuyueYuefen(Integer.valueOf(data.get(0)));   //租赁月份 要改的
//                            cheweiyuyueEntity.setCheweiyuyueContent("");//详情和图片
//                            cheweiyuyueEntity.setInsertTime(date);//时间
//                            cheweiyuyueEntity.setCheweiyuyueYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            cheweiyuyueEntity.setCheweiyuyueYesnoText(data.get(0));                    //审核意见 要改的
//                            cheweiyuyueEntity.setCheweiyuyueShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            cheweiyuyueEntity.setCreateTime(date);//时间
                            cheweiyuyueList.add(cheweiyuyueEntity);


                            //把要查询是否重复的字段放入map中
                                //车位预约编号
                                if(seachFields.containsKey("cheweiyuyueUuidNumber")){
                                    List<String> cheweiyuyueUuidNumber = seachFields.get("cheweiyuyueUuidNumber");
                                    cheweiyuyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> cheweiyuyueUuidNumber = new ArrayList<>();
                                    cheweiyuyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("cheweiyuyueUuidNumber",cheweiyuyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //车位预约编号
                        List<CheweiyuyueEntity> cheweiyuyueEntities_cheweiyuyueUuidNumber = cheweiyuyueService.selectList(new EntityWrapper<CheweiyuyueEntity>().in("cheweiyuyue_uuid_number", seachFields.get("cheweiyuyueUuidNumber")));
                        if(cheweiyuyueEntities_cheweiyuyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CheweiyuyueEntity s:cheweiyuyueEntities_cheweiyuyueUuidNumber){
                                repeatFields.add(s.getCheweiyuyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [车位预约编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        cheweiyuyueService.insertBatch(cheweiyuyueList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",cheweiyuyueList.toString());
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
        PageUtils page = cheweiyuyueService.queryPage(params);

        //字典表数据转换
        List<CheweiyuyueView> list =(List<CheweiyuyueView>)page.getList();
        for(CheweiyuyueView c:list)
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
        CheweiyuyueEntity cheweiyuyue = cheweiyuyueService.selectById(id);
            if(cheweiyuyue !=null){


                //entity转view
                CheweiyuyueView view = new CheweiyuyueView();
                BeanUtils.copyProperties( cheweiyuyue , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(cheweiyuyue.getYonghuId());
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
    public R add(@RequestBody CheweiyuyueEntity cheweiyuyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,cheweiyuyue:{}",this.getClass().getName(),cheweiyuyue.toString());
        Wrapper<CheweiyuyueEntity> queryWrapper = new EntityWrapper<CheweiyuyueEntity>()
            .eq("yonghu_id", cheweiyuyue.getYonghuId())
            .eq("cheweiyuyue_uuid_number", cheweiyuyue.getCheweiyuyueUuidNumber())
            .eq("cheweiyuyue_name", cheweiyuyue.getCheweiyuyueName())
            .eq("chewei_types", cheweiyuyue.getCheweiTypes())
            .eq("cheweiyuyue_yuefen", cheweiyuyue.getCheweiyuyueYuefen())
            .in("cheweiyuyue_yesno_types", new Integer[]{1,2})
            .eq("cheweiyuyue_yesno_text", cheweiyuyue.getCheweiyuyueYesnoText())
//            .notIn("cheweiyuyue_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheweiyuyueEntity cheweiyuyueEntity = cheweiyuyueService.selectOne(queryWrapper);
        if(cheweiyuyueEntity==null){
            cheweiyuyue.setInsertTime(new Date());
            cheweiyuyue.setCheweiyuyueYesnoTypes(1);
            cheweiyuyue.setCreateTime(new Date());
        cheweiyuyueService.insert(cheweiyuyue);

            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"前台新增",cheweiyuyue.toString());
            return R.ok();
        }else {
            if(cheweiyuyueEntity.getCheweiyuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(cheweiyuyueEntity.getCheweiyuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

