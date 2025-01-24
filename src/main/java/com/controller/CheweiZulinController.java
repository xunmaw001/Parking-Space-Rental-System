
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
 * 车位租赁订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/cheweiZulin")
public class CheweiZulinController {
    private static final Logger logger = LoggerFactory.getLogger(CheweiZulinController.class);

    private static final String TABLE_NAME = "cheweiZulin";

    @Autowired
    private CheweiZulinService cheweiZulinService;


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
        PageUtils page = cheweiZulinService.queryPage(params);

        //字典表数据转换
        List<CheweiZulinView> list =(List<CheweiZulinView>)page.getList();
        for(CheweiZulinView c:list){
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
        CheweiZulinEntity cheweiZulin = cheweiZulinService.selectById(id);
        if(cheweiZulin !=null){
            //entity转view
            CheweiZulinView view = new CheweiZulinView();
            BeanUtils.copyProperties( cheweiZulin , view );//把实体数据重构到view中
            //级联表 车位
            //级联表
            CheweiEntity chewei = cheweiService.selectById(cheweiZulin.getCheweiId());
            if(chewei != null){
            BeanUtils.copyProperties( chewei , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setCheweiId(chewei.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(cheweiZulin.getYonghuId());
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
    public R save(@RequestBody CheweiZulinEntity cheweiZulin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,cheweiZulin:{}",this.getClass().getName(),cheweiZulin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            cheweiZulin.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<CheweiZulinEntity> queryWrapper = new EntityWrapper<CheweiZulinEntity>()
            .eq("chewei_id", cheweiZulin.getCheweiId())
            .eq("yonghu_id", cheweiZulin.getYonghuId())
            .eq("buy_number", cheweiZulin.getBuyNumber())
            .eq("chewei_zulin_types", cheweiZulin.getCheweiZulinTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheweiZulinEntity cheweiZulinEntity = cheweiZulinService.selectOne(queryWrapper);
        if(cheweiZulinEntity==null){
            cheweiZulin.setInsertTime(new Date());
            cheweiZulin.setCreateTime(new Date());
            cheweiZulinService.insert(cheweiZulin);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",cheweiZulin.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CheweiZulinEntity cheweiZulin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,cheweiZulin:{}",this.getClass().getName(),cheweiZulin.toString());
        CheweiZulinEntity oldCheweiZulinEntity = cheweiZulinService.selectById(cheweiZulin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            cheweiZulin.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            cheweiZulinService.updateById(cheweiZulin);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(cheweiZulin, oldCheweiZulinEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<CheweiZulinEntity> oldCheweiZulinList =cheweiZulinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        cheweiZulinService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldCheweiZulinList.toString());
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
            List<CheweiZulinEntity> cheweiZulinList = new ArrayList<>();//上传的东西
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
                            CheweiZulinEntity cheweiZulinEntity = new CheweiZulinEntity();
//                            cheweiZulinEntity.setCheweiZulinUuidNumber(data.get(0));                    //订单编号 要改的
//                            cheweiZulinEntity.setCheweiId(Integer.valueOf(data.get(0)));   //车位 要改的
//                            cheweiZulinEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            cheweiZulinEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买月份 要改的
//                            cheweiZulinEntity.setCheweiZulinTruePrice(data.get(0));                    //实付价格 要改的
//                            cheweiZulinEntity.setCheweiZulinTypes(Integer.valueOf(data.get(0)));   //订单状态 要改的
//                            cheweiZulinEntity.setInsertTime(date);//时间
//                            cheweiZulinEntity.setDaoqiTime(sdf.parse(data.get(0)));          //车位到期时间 要改的
//                            cheweiZulinEntity.setCreateTime(date);//时间
                            cheweiZulinList.add(cheweiZulinEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("cheweiZulinUuidNumber")){
                                    List<String> cheweiZulinUuidNumber = seachFields.get("cheweiZulinUuidNumber");
                                    cheweiZulinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> cheweiZulinUuidNumber = new ArrayList<>();
                                    cheweiZulinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("cheweiZulinUuidNumber",cheweiZulinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<CheweiZulinEntity> cheweiZulinEntities_cheweiZulinUuidNumber = cheweiZulinService.selectList(new EntityWrapper<CheweiZulinEntity>().in("chewei_zulin_uuid_number", seachFields.get("cheweiZulinUuidNumber")));
                        if(cheweiZulinEntities_cheweiZulinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CheweiZulinEntity s:cheweiZulinEntities_cheweiZulinUuidNumber){
                                repeatFields.add(s.getCheweiZulinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        cheweiZulinService.insertBatch(cheweiZulinList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",cheweiZulinList.toString());
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
        PageUtils page = cheweiZulinService.queryPage(params);

        //字典表数据转换
        List<CheweiZulinView> list =(List<CheweiZulinView>)page.getList();
        for(CheweiZulinView c:list)
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
        CheweiZulinEntity cheweiZulin = cheweiZulinService.selectById(id);
            if(cheweiZulin !=null){


                //entity转view
                CheweiZulinView view = new CheweiZulinView();
                BeanUtils.copyProperties( cheweiZulin , view );//把实体数据重构到view中

                //级联表
                    CheweiEntity chewei = cheweiService.selectById(cheweiZulin.getCheweiId());
                if(chewei != null){
                    BeanUtils.copyProperties( chewei , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheweiId(chewei.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(cheweiZulin.getYonghuId());
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
    public R add(@RequestBody CheweiZulinEntity cheweiZulin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,cheweiZulin:{}",this.getClass().getName(),cheweiZulin.toString());

        CheweiEntity cheweiEntity = cheweiService.selectById(cheweiZulin.getCheweiId());
        if(cheweiEntity == null)
            return R.error("查不到车位");
        if(cheweiEntity.getYonghuId() != null || cheweiEntity.getCheweiZhuangtaiTypes()!=1){
            return R.error("车位已被租赁,不能租赁");
        }
        cheweiEntity.setYonghuId(cheweiZulin.getYonghuId());
        cheweiEntity.setCheweiZhuangtaiTypes(2);


        double huafei = cheweiEntity.getCheweiNewMoney() * cheweiZulin.getBuyNumber();
        YonghuEntity yonghuEntity = yonghuService.selectById(cheweiZulin.getYonghuId());
        if(yonghuEntity == null)
            return R.error("查不到用户");
        double balance = yonghuEntity.getNewMoney() - huafei;
        if(balance<0)
            return R.error("余额不够支付,请充值后再租赁车位");
        yonghuEntity.setNewMoney(balance);
        yonghuService.updateById(yonghuEntity);

        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MONTH,cheweiZulin.getBuyNumber());
        cheweiZulin.setDaoqiTime(instance.getTime());
        cheweiZulin.setCheweiZulinTypes(1);
        cheweiZulin.setInsertTime(new Date());
        cheweiZulin.setCheweiZulinTruePrice(huafei);
        cheweiZulin.setCheweiZulinUuidNumber(String.valueOf(new Date().getTime()));
        cheweiZulin.setCreateTime(new Date());
        cheweiZulinService.insert(cheweiZulin);
        cheweiService.updateById(cheweiEntity);

            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"前台新增",cheweiZulin.toString());
            return R.ok();
    }
    /**
    * 续租
    */
    @RequestMapping("/xuzu")
    public R xuzu(Integer id, HttpServletRequest request){
        logger.debug("续租方法:,,Controller:{},,id:{}",this.getClass().getName(),id);


        CheweiZulinEntity cheweiZulinEntity = cheweiZulinService.selectById(id);
        if(cheweiZulinEntity == null){
            return R.error("查不到车位租赁信息");
        }
        CheweiEntity cheweiEntity = cheweiService.selectById(cheweiZulinEntity.getCheweiId());
        if(cheweiEntity == null)
            return R.error("查不到车位");
        YonghuEntity yonghuEntity = yonghuService.selectById(cheweiZulinEntity.getYonghuId());
        if(yonghuEntity == null)
            return R.error("查不到用户");
        double balance = yonghuEntity.getNewMoney() - cheweiEntity.getCheweiNewMoney();
        if(balance<0)
            return R.error("余额不够支付,请充值后再续租车位");
        cheweiZulinEntity.setBuyNumber(cheweiZulinEntity.getBuyNumber()+1);

        Calendar instance = Calendar.getInstance();
        instance.setTime(cheweiZulinEntity.getDaoqiTime());
        instance.add(Calendar.MONTH,1);

        cheweiZulinEntity.setDaoqiTime(instance.getTime());
        cheweiZulinEntity.setCheweiZulinTruePrice(cheweiZulinEntity.getCheweiZulinTruePrice()+cheweiEntity.getCheweiNewMoney());


        yonghuService.updateById(yonghuEntity);

        cheweiZulinService.updateById(cheweiZulinEntity);


    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"续租一个月",cheweiZulinEntity.toString());
    return R.ok();
    }

    /**
    * 车位到期
    */
    @RequestMapping("/cheweidaoqi")
    public R cheweidaoqi(Integer id, HttpServletRequest request){
        logger.debug("车位到期方法:,,Controller:{},,id:{}",this.getClass().getName(),id);


        CheweiZulinEntity cheweiZulinEntity = cheweiZulinService.selectById(id);
        if(cheweiZulinEntity == null){
            return R.error("查不到车位租赁信息");
        }
        cheweiZulinEntity.setCheweiZulinTypes(2);



        CheweiEntity cheweiEntity = cheweiService.selectById(cheweiZulinEntity.getCheweiId());
        if(cheweiEntity == null)
            return R.error("查不到车位");
        cheweiEntity.setYonghuId(null);
        cheweiEntity.setCheweiZhuangtaiTypes(1);
        cheweiService.updateAllColumnById(cheweiEntity);

        cheweiZulinService.updateById(cheweiZulinEntity);


    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"车位到期",cheweiZulinEntity.toString());
    return R.ok();
    }

}

