package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysFile;
import cn.kunli.una.pojo.vo.SysParameter;
import cn.kunli.una.pojo.vo.SysResponseParameter;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysFileService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.MinIoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * (SysFile)表控制层
 *
 * @author Ponzio
 * @since 2020-05-16 22:04:52
 */
@Slf4j
@Controller
@RequestMapping("/sys/file")
public class SysFileController extends BaseController<SysFileService, SysFile> {

    @Autowired
    private MinIoUtil minIoUtil;

    /**
     * 跳转通用表单页
     *
     * @param model
     * @param
     * @return
     */
    //@RequiresPermissions("SysFile:retrieve")
    @RequestMapping("/respository")
    public String form(Model model, SysParameter sysParameter, String textInputId, Integer num) {

        //如果redis已连接，则从redis中获取实体类，否则从数据库查询
        SysEntity entityClass = sysEntityService.selectOne(MapUtil.getMap("code", "SysFile"));
        if(entityClass!=null){
            entityClass = sysEntityService.parse(ListUtil.getList(entityClass)).get(0);
        }
        model.addAttribute("sysResponseParameter", new SysResponseParameter().setSysEntity(entityClass));
        model.addAttribute("textInputId", textInputId);
        model.addAttribute("num", num);
        return "system/file/respository";
    }

    //富文本保存图片用
    @PostMapping("/saveByEditor")
    @ResponseBody
    public String saveByEditor(@Valid SysFile entity) {
        SysResult result = super.save(entity);
        if(result.getIsSuccess()){
            String downLoadPath = minIoUtil.getDownLoadPath(entity.getPath());
            return downLoadPath;
        }else{
            return "保存失败";
        }

    }
}
