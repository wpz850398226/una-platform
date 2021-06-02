package cn.kunli.una.controller.duohui.guanwang;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.duohui.guanwang.GwConfiguration;
import cn.kunli.una.pojo.duohui.guanwang.GwMenu;
import cn.kunli.una.pojo.system.SysData;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.service.duohui.guanwang.GwConfigurationService;
import cn.kunli.una.service.duohui.guanwang.GwMenuService;
import cn.kunli.una.service.system.SysDataService;
import cn.kunli.una.utils.common.MapUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/duohui/guanwang")
public class GwIndexController extends BaseController<SysDataService, SysData> {
    @Autowired
    private GwMenuService gwMenuService;
    @Autowired
    private GwConfigurationService gwConfigurationService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        /*SysEntity sysEntity = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code", "GwIndex")));
        List<SysData> list = this.list(MapUtil.buildHashMap().put("entityId", sysEntity.getId()).put("last", "limit 1").build());
        if(CollectionUtils.isNotEmpty(list)){
            model.addAttribute("record",list.get(0));
        }*/
        SysData record = sysDataService.getById(100001);
        model.addAttribute("record",record);

        getCommonItem(model,null);
        return "duohui/guanwang/index";
    }


    /**
     * 列表页
     *
     * @param model
     * @return
     */
    @GetMapping("/page/{entityId}")
    public String list(Model model,@RequestParam Map<String, Object> map) {

        Object menuId = null;
        if(map.containsKey("menuId")){
            menuId = map.get("menuId");
            map.remove("menuId");
        }
        getCommonItem(model,menuId);

        Long pageNum = 1L;
        Long pageSize = 10L;
        if(map.get("pageNum")!=null){
            pageNum = Long.valueOf(map.get("pageNum").toString());
            map.remove("pageNum");
        }
        if(map.get("pageSize")!=null){
            pageSize = Long.valueOf(map.get("pageSize").toString());
            map.remove("pageSize");
        }
        Page<SysData> objectPage = new Page<SysData>().setCurrent(pageNum).setSize(pageSize);
        Page page = sysDataService.page(objectPage, wrapperUtil.mapToWrapper(service.format(map)));

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
        SysData record = sysDataService.getById(id);
        model.addAttribute("record",record);

        Object menuId = null;
        if(map.containsKey("menuId")){
            menuId = map.get("menuId");
            map.remove("menuId");
        }
        getCommonItem(model,menuId);
        return "duohui/guanwang/single";
    }

    private void getCommonItem(Model model,Object menuId){
        List<GwMenu> gwMenuList = gwMenuService.parse(gwMenuService.list(gwMenuService.getWrapper(MapUtil.getMap("parentId", 100000))));
        GwConfiguration systemTitle = gwConfigurationService.getOne(gwConfigurationService.getWrapper(MapUtil.getMap("code","systemTitle")));
        model.addAttribute("gwMenuList", gwMenuList);
        model.addAttribute("systemName", systemTitle.getValue());

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
