package cn.kunli.una.service.duohui.toubiao;

import cn.kunli.una.pojo.bid.BidBidder;
import cn.kunli.una.mapper.BidBidderMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
