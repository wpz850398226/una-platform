package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysRegionMapper;
import cn.kunli.una.pojo.system.SysRegion;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.service.SpringContextUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysRegion)表服务实现类
 *
 * @author Ponzio
 * @since 2020-06-29 15:18:55
 */
@Service
public class SysRegionService extends BasicService<SysRegionMapper, SysRegion> {

    /**
     * 查询结果格式化
     *
     * @param list
     * @return
     */
    public List<SysRegion> resultFormat(List<SysRegion> list) {
        SysRegionService _this = SpringContextUtil.getBean(SysRegionService.class);
        super.resultFormat(list);
        for (SysRegion record : list) {
            if(record.getLevel() == 2){
                List<SysRegion> subList = _this.list(wrapperUtil.mapToWrapper(MapUtil.getMap("parentId", record.getId())));
                if(CollectionUtils.isNotEmpty(subList)){
                    this.resultFormat(subList);
                }
                record.setChildren(subList);
            }
        }
        return list;
    }
}
