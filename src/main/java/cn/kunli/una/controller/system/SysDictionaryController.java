package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ponzio
 * @version 2019年6月5日 下午5:25:14
 * 字典管理
 */
@Slf4j
@Controller
@RequestMapping("/sys/dictionary")
public class SysDictionaryController extends BaseController<SysDictionaryService, SysDictionary> {


    @Autowired
    private SysDictionaryService objService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * ajax查询所有
     * @return
     */
	/*@Override
	@RequestMapping("/queryPlural")
	@ResponseBody
	public List<SysDictionary> queryPlural(SysDictionary obj) {
		if(obj.getParentId()!=null&&obj.getParentId().equals("null")){
			return redisUtil.getIsConnect()&&redisUtil.hasKey("SysDictionary:root")?(List<SysDictionary>)redisUtil.get("SysDictionary:root"):
					objService.selectBySelective(obj);
		}
		return super.queryPlural(obj);
	}*/

}
