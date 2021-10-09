package cn.kunli.una.service.oa;

import cn.kunli.una.mapper.OaVacateMapper;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.oa.OaVacate;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 办公-请假(OaVacate)表服务类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:25
 */
@Service
public class OaVacateService extends BasicService<OaVacateMapper, OaVacate> {
    @Autowired
    private OaVacateService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public OaVacate initialize(OaVacate obj) {
        obj = super.initialize(obj);

        if(obj.getId() == null){
            if(obj.getStartTime()!=null && obj.getEndTime() !=null){
                obj.setDuration(DateUtil.getDiffDays(obj.getStartTime(),obj.getEndTime()));
            }
        }

        return obj;
    }

    @Override
    public List<OaVacate> parse(List<OaVacate> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.parse(list);

        for (OaVacate oaVacate : list) {
            if(oaVacate.getInstanceId()!=null){
                FlowInstance flowInstance = flowInstanceService.getById(oaVacate.getInstanceId());
                oaVacate.setIsAgree(flowInstance.getIsAgree());
            }
        }

        return list;
    }
}
