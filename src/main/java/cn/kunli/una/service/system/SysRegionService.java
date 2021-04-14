package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysRegionMapper;
import cn.kunli.una.pojo.system.SysRegion;
import cn.kunli.una.service.BaseService;
import cn.kunli.una.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysRegion)表服务实现类
 *
 * @author Ponzio
 * @since 2020-06-29 15:18:55
 */
@Service
public class SysRegionService extends BaseService<SysRegionMapper, SysRegion> {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据主键查询名字
     *
     * @param id
     * @return
     */
    @Override
    public SysRegion selectByPrimaryKey(Object id) {
        if (redisUtil.hasKey("reg_" + id)) {
            Object o = redisUtil.get("reg_" + id);
            if (o != null) return (SysRegion) o;
        } else {
            SysRegion sysRegion = this.mapper.selectByPrimaryKey(id);
            if (sysRegion != null) {
                redisUtil.set("reg_" + sysRegion.getId(), sysRegion);
                return sysRegion;
            }
        }
        return null;
    }

}
