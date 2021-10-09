package cn.kunli.una.service.oa;

import cn.kunli.una.mapper.OaExtraworkMapper;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.oa.OaExtrawork;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 办公-加班(OaExtrawork)表服务类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:21
 */
@Service
public class OaExtraworkService extends BasicService<OaExtraworkMapper, OaExtrawork> {
    @Autowired
    private OaExtraworkService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public OaExtrawork initialize(OaExtrawork obj) {
        obj = super.initialize(obj);

        if(obj.getId() == null){
            if(obj.getStartTime()!=null && obj.getEndTime() !=null){
                obj.setDuration(DateUtil.getDiffDays(obj.getStartTime(),obj.getEndTime()));
            }
        }

        return obj;
    }

    @Override
    public List<OaExtrawork> parse(List<OaExtrawork> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.parse(list);

        for (OaExtrawork oaExtrawork : list) {
            if(oaExtrawork.getInstanceId()!=null){
                FlowInstance flowInstance = flowInstanceService.getById(oaExtrawork.getInstanceId());
                oaExtrawork.setIsAgree(flowInstance.getIsAgree());
            }
        }

        return list;
    }
}
