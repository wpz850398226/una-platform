package cn.kunli.una.service.td;

import cn.kunli.una.pojo.td.TdWord;
import cn.kunli.una.mapper.TdWordMapper;
import org.springframework.stereotype.Service;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 单词 服务类
 * </p>
 *
 * @author wangpz
 * @since 2022-04-20
 */
@Service
public class TdWordService extends BasicService<TdWordMapper, TdWord> {

    @Autowired
    private TdWordService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public List<TdWord> parse(List<TdWord> list) {
        list = super.parse(list);
        list.forEach(e -> {
            Map<String, Object> map = e.getMap();
            Object prefixOriginName = map.getOrDefault("prefixOriginName", "");
            Object rootOriginName = map.getOrDefault("rootOriginName", "");
            Object suffixOriginName = map.getOrDefault("suffixOriginName", "");
            StringBuffer sb = new StringBuffer();
            String spellWord = sb.append(prefixOriginName).append(rootOriginName).append(suffixOriginName).toString().replace("-", "");
            e.setSpellWord(spellWord);
        });

        return list;
    }
}
