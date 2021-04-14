package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysRegion;
import cn.kunli.una.pojo.vo.SysParamMap;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysRegionService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * (SysRegion)表控制层
 *
 * @author Ponzio
 * @since 2020-06-29 15:18:55
 */
@Controller
@RequestMapping("/sys/region")
public class SysRegionController extends BaseController<SysRegionService, SysRegion> {
    @Autowired
    private SysRegionService objService;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 刷新redis河北地区字典缓存
     *
     * @return
     */
    /*@RequestMapping("refreshRedis")
    @ResponseBody
    public SysResult refreshRedis() {

        if (redisUtil.getIsConnect()) {
            Set<String> keys = redisUtil.hasKeys("reg_*");
            redisUtil.delKeys(keys);

            List<SysRegion> sysRegionList = objService.selectBySelective(SysParamMap.MapBuilder.aMap().put("rootId", "130000").build());
            try {
                if (ListUtil.isNotNull(sysRegionList)) {
                    for (SysRegion obj : sysRegionList) {
                        String key = "reg_" + obj.getId();
                        //添加新的缓存数据
                        redisUtil.set(key, obj);
                    }
                }
                return SysResult.success("刷新redis成功");
            } catch (Exception e) {
                e.printStackTrace();
                return SysResult.fail("刷新redis失败");
            }
        } else {
            return SysResult.fail("redis连接失败");
        }
    }*/
}
