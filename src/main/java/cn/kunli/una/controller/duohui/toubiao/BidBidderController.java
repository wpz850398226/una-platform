package cn.kunli.una.controller.duohui.toubiao;

import cn.kunli.una.pojo.bid.BidBidder;
import cn.kunli.una.service.duohui.toubiao.BidBidderService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 招投标-投标信息(BidBidder)表控制层
 *
 * @author Ponzio
 * @since 2021-07-17 13:19:43
 */
@Controller
@RequestMapping("/bid/bidder")
public class BidBidderController extends BaseController<BidBidderService, BidBidder> {
}
