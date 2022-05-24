package cn.kunli.una.service.duohui.toubiao;

import cn.kunli.una.handler.UnaResponseException;
import cn.kunli.una.mapper.BidBidderMapper;
import cn.kunli.una.pojo.bid.BidBidder;
import cn.kunli.una.pojo.bid.BidProject;
import cn.kunli.una.pojo.sys.SysDictionary;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.kunli.una.utils.common.UserUtil;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招投标-投标信息(BidBidder)表服务类
 *
 * @author Ponzio
 * @since 2021-07-17 13:19:43
 */
@Service
public class BidBidderService extends BasicService<BidBidderMapper, BidBidder> {
    @Autowired
    private BidBidderService thisProxy;
    @Autowired
    private BidProjectService bidProjectService;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    @SneakyThrows
    public void saveValidate(BidBidder obj) {
        super.saveValidate(obj);

        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        if(obj.getProjectId()!=null){
            List<BidBidder> bidBidders = thisProxy.selectList(UnaMapUtil.buildHashMap().put("projectId", obj.getProjectId()).put("creatorId", loginUser.getId()).build());
            if(CollectionUtils.isNotEmpty(bidBidders)){
                throw new UnaResponseException("已报名，请勿重复操作");
            }
        }
    }

    @Override
    public List<BidBidder> parse(List<BidBidder> list) {
        list = super.parse(list);

        list.forEach(bidBidder -> {
            Integer projectId = bidBidder.getProjectId();
            BidProject bidProject = bidProjectService.getById(projectId);
            String statusDcode = bidProject.getStatusDcode();
            SysDictionary sysDictionary = sysDictionaryService.selectOne(UnaMapUtil.getMap("code", statusDcode));
            if(sysDictionary!=null){
                bidBidder.setProjectStatusDname(sysDictionary.getName());
            }

        });

        return list;
    }
}
