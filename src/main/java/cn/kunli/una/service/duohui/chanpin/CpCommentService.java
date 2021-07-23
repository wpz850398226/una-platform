package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpComment;
import cn.kunli.una.mapper.CpCommentMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-评论(CpComment)表服务类
 *
 * @author Ponzio
 * @since 2021-07-21 21:17:28
 */
@Service
public class CpCommentService extends BasicService<CpCommentMapper, CpComment> {
    @Autowired
    private CpCommentService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
