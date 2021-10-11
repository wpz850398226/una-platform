package cn.kunli.una.controller.duohui.guanwang;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.guanwang.GwMenu;
import cn.kunli.una.pojo.system.SysArticle;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.system.SysData;
import cn.kunli.una.service.duohui.guanwang.GwMenuService;
import cn.kunli.una.service.system.SysDataService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/duohui/guanwang")
public class GwIndexController extends BaseController<SysDataService, SysData> {
    @Autowired
    private GwMenuService gwMenuService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        /*SysEntity sysEntity = sysEntityService.selectOne(MapUtil.getMap("code", "GwIndex")));
        List<SysData> list = this.list(MapUtil.buildHashMap().put("entityId", sysEntity.getId()).put("last", "limit 1").build());
        if(CollectionUtils.isNotEmpty(list)){
            model.addAttribute("record",list.get(0));
        }*/
        SysData record = sysDataService.getById(100001);
        record = sysDataService.parse(ListUtil.getList(record)).get(0);
        model.addAttribute("record",record);

        //查询政策法规前5条
        Page<SysArticle> page1 = sysArticleService.page(0, 5, MapUtil.getMap("typeDcode", "articleType_policy"));
        model.addAttribute("zcfgList",sysArticleService.parse(page1.getRecords()));
        //查询行业动态前5条
        Page<SysArticle> page2 = sysArticleService.page(0, 5, MapUtil.getMap("typeDcode", "articleType_industryState"));
        model.addAttribute("hydtList",sysArticleService.parse(page2.getRecords()));
        //查询公司公告前5条
        Page<SysArticle> page3 = sysArticleService.page(0, 5, MapUtil.getMap("typeDcode", "articleType_notice"));
        model.addAttribute("gsggList",sysArticleService.parse(page3.getRecords()));

        getCommonItem(model,null);
        return "duohui/guanwang/index";
    }


    /**
     * 列表页
     *
     * @param model
     * @return
     */
    @GetMapping("/page/{code}")
    public String list(Model model,@PathVariable("code") String code, @RequestParam Map<String, Object> map) {

        Object menuId = null;
        if(map.containsKey("menuId")){
            menuId = map.get("menuId");
            map.remove("menuId");
        }
        getCommonItem(model,menuId);

        if(!map.containsKey("orderByAsc")&&!map.containsKey("orderByDesc")){
            map.put("orderByDesc","createTime");
        }
        if(StringUtils.isNotBlank(code)){
            map.put("typeDcode",code);
        }
        Page page = sysArticleService.page(map.get("pageNum"),map.get("pageSize"), map);

        model.addAttribute("page", page);
        return "duohui/guanwang/page";
    }

    /**
     * 单页
     *
     * @param model
     * @return
     */
    @GetMapping("/single/{id}")
    public String single(Model model, @PathVariable("id") Integer id,@RequestParam Map<String, Object> map) {
        SysArticle record = sysArticleService.getById(id);
        model.addAttribute("record",record);
        //增加点击量
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.setEntity(new SysArticle().setId(id));
        updateWrapper.setSql("view_amount = view_amount + 1");
        sysArticleService.update(updateWrapper);

        Object menuId = null;
        if(map.containsKey("menuId")){
            menuId = map.get("menuId");
            map.remove("menuId");
        }
        getCommonItem(model,menuId);
        return "duohui/guanwang/single";
    }

    private void getCommonItem(Model model,Object menuId){
        List<GwMenu> gwMenuList = gwMenuService.parse(gwMenuService.selectList(MapUtil.getMap("parentId", 100000)));
        model.addAttribute("gwMenuList", gwMenuList);

        //查询系统配置信息
        List<SysConfiguration> configList = sysConfigurationService.selectList(MapUtil.getMap(":code", "GwIndex_"));
        Map<String,String> configMap = new HashMap<>();
        configList.forEach(c -> configMap.put(c.getCode(),c.getValue()));
        model.addAttribute("configMap", configMap);

        if(menuId!=null){
            for (GwMenu gwMenu : gwMenuList) {
                if(CollectionUtils.isNotEmpty(gwMenu.getChildren())){
                    for (GwMenu child : gwMenu.getChildren()) {
                        if(child.getId().equals(Integer.valueOf(menuId.toString()))){
                            model.addAttribute("currentPrimaryMenu", gwMenu);
                            model.addAttribute("currentMenu", child);
                            return;
                        }
                    }
                }
            }
        }
    }

}
