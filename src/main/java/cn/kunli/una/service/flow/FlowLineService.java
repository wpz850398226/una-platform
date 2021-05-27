package cn.kunli.una.service.flow;

import cn.kunli.una.mapper.FlowLineMapper;
import cn.kunli.una.pojo.flow.FlowLine;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程连线(FlowLine)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:50
 */
@Service
public class FlowLineService extends BasicService<FlowLineMapper, FlowLine> {

    @Autowired
    private FlowLineService thisProxy;
    @Override
    public BasicService<FlowLineMapper, FlowLine> getThisProxy() {
        return thisProxy;
    }
}
