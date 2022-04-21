package cn.kunli.una.service.td;

import cn.hutool.core.util.StrUtil;
import cn.kunli.una.mapper.TdEtymologyMapper;
import cn.kunli.una.pojo.td.TdEtymology;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 词源 服务类
 * </p>
 *
 * @author wangpz
 * @since 2022-04-21
 */
@Service
public class TdEtymologyService extends BasicService<TdEtymologyMapper, TdEtymology> {

    @Autowired
    private TdEtymologyService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public SysResult validate(TdEtymology obj) {
        if(StrUtil.isNotBlank(obj.getName()) && StrUtil.isNotBlank(obj.getTypeDcode())){
            List<TdEtymology> sameNamelist = super.listByMap(UnaMapUtil.buildHashMap().put("name", obj.getName()).put("type_dcode", obj.getTypeDcode()).build());
            if(CollectionUtils.isNotEmpty(sameNamelist)&&!sameNamelist.get(0).getId().equals(obj.getId())){
                return SysResult.fail("名字重复，保存失败");
            }
        }

        return SysResult.success();
    }

    @Override
    public TdEtymology initialize(TdEtymology obj) {
        obj = super.initialize(obj);
        if(StrUtil.isBlank(obj.getTypeDcode()) && StrUtil.isNotBlank(obj.getName())){
            int i = obj.getName().indexOf("-");
            switch(i){
                case -1:
                    //没有-，词根
                    obj.setTypeDcode("word_resourceType_root");
                    break;
                case 0:
                    //第一个字母是-，后缀
                    obj.setTypeDcode("word_resourceType_suffix");
                    break;
                default:
                    obj.setTypeDcode("word_resourceType_prefix");
                    break;
            }
        }

        return obj;
    }
}
