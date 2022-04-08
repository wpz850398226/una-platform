package cn.kunli.una.service.flow;

import cn.kunli.una.mapper.FlowNodeMapper;
import cn.kunli.una.pojo.flow.FlowLine;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程节点(FlowNode)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:51
 */
@Service
public class FlowNodeService extends BasicService<FlowNodeMapper, FlowNode> {
    @Autowired
    private FlowNodeService thisProxy;
    @Autowired
    private FlowLineService flowLineService;
    @Override
    public BasicService<FlowNodeMapper, FlowNode> getThisProxy() {
        return thisProxy;
    }

    //查询该节点后面的节点
    public List<FlowNode> getSubsequent(Integer nodeId){
        if(nodeId==null)return null;
        List<FlowNode> flowNodeList = new ArrayList<>();
        List<FlowLine> flowLineList = flowLineService.selectList(UnaMapUtil.getMap("originNodeId", nodeId));
        if(CollectionUtils.isEmpty(flowLineList))return flowNodeList;
        for (FlowLine flowLine : flowLineList) {
            FlowNode flowNode = thisProxy.getById(flowLine.getTargetNodeId());
            flowNodeList.add(flowNode);
        }
        return flowNodeList;
    }

}
