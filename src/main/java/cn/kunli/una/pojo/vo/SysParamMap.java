package cn.kunli.una.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class SysParamMap extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1L;
    private Long pageNum;
    private Long pageSize;
    private String orderBy;
    private String sortord;						//排序方式
    private String sortkey;						//排序关键字
    private String sortSql;						//排序查询语句
    private List<SysUtilQuery> utilQueryList;						//精确查询字段集合

    public SysParamMap(Map<String, Object> params) {
        this.putAll(params);
        if (params.get("pageNum") != null) {
            this.pageNum = Long.valueOf(params.get("pageNum").toString());
        }

        if (params.get("pageSize") != null) {
            this.pageSize = Long.valueOf(params.get("pageSize").toString());
        }

        if (params.get("orderBy") != null) {
            this.orderBy = params.get("orderBy").toString();
        }

        this.remove("pageNum");
        this.remove("pageSize");
        this.remove("orderBy");
    }

    public static final class MapBuilder {
        Map<String, Object> map = new HashMap<>();

        private MapBuilder() {
        }

        public static MapBuilder aMap() {
            return new MapBuilder();
        }

        public MapBuilder put(String key,Object value) {
            map.put(key,value);
            return this;
        }

        public SysParamMap build() {
            SysParamMap sysParamMap = new SysParamMap(map);
            return sysParamMap;
        }
    }

}
