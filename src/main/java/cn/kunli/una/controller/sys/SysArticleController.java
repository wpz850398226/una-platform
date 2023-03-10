package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysArticle;
import cn.kunli.una.pojo.sys.SysData;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.sys.SysArticleService;
import cn.kunli.una.utils.common.UnaMapUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * (SysArticle)表控制层
 *
 * @author Ponzio
 * @since 2020-05-06 17:13:09
 */
@Slf4j
@Controller
@RequestMapping("/sys/article")
public class SysArticleController extends BaseController<SysArticleService, SysArticle> {

    //token获取用户信息
    @GetMapping("/showInTemplate/{entityId}/{id}")
    @ResponseBody
    public SysResult showInTemplate(@PathVariable Integer entityId,@PathVariable Integer id) {

        SysEntity sysEntity = sysEntityService.getById(entityId);
        if(sysEntity.getIsVirtual()){
            //虚拟实体
            SysData sysData = sysDataService.getById(id);
            JSONObject value = sysData.getValue();

            if(value==null||!value.containsKey("templateName")){
                log.error("查看失败，文章内容异常:{}",value);
                return SysResult.fail("查看失败，文章内容异常");
            }

            String templateName = value.get("templateName").toString();
            SysArticle templateArticle = service.selectOne(UnaMapUtil.getMap("name", templateName));
            if(templateArticle==null)return SysResult.fail("查看失败，模板不存在");

            String content = templateArticle.getContent();
            for (Map.Entry<String, Object> entry : value.entrySet()) {
                if(content.contains("{" + entry.getKey() + "}")){
                    content = content.replace("{"+entry.getKey()+"}",entry.getValue().toString());
                }
                if(content.contains("{</u><span>"+entry.getKey()+"</span><u>}")){
                    content = content.replace("{</u><span>"+entry.getKey()+"</span><u>}",entry.getValue().toString());
                }
            }
            return new SysResult().success(content);

        }
        return SysResult.fail();
    }
}
