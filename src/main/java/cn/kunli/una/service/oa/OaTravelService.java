package cn.kunli.una.service.oa;

import cn.kunli.una.mapper.OaTravelMapper;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.oa.OaTravel;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 办公-出差(OaTravel)表服务类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:25
 */
@Service
public class OaTravelService extends BasicService<OaTravelMapper, OaTravel> {
    @Autowired
    private OaTravelService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public OaTravel initialize(OaTravel obj) {
        obj = super.initialize(obj);

        if(obj.getId() == null){
            if(obj.getStartTime()!=null && obj.getEndTime() !=null){
                obj.setDuration(DateUtil.getDiffDays(obj.getStartTime(),obj.getEndTime()));
            }
        }

        return obj;
    }

    @Override
    public List<OaTravel> parse(List<OaTravel> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.parse(list);

        for (OaTravel oaTravel : list) {
            if(oaTravel.getInstanceId()!=null){
                FlowInstance flowInstance = flowInstanceService.getById(oaTravel.getInstanceId());
                oaTravel.setIsAgree(flowInstance.getIsAgree());
            }
        }

        return list;
    }
}
