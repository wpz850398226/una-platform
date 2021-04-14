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
    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;
    private String sortord;						//排序方式
    private String sortkey;						//排序关键字
    private String sortSql;						//排序查询语句
    private List<SysUtilQuery> utilQueryList;						//精确查询字段集合

    public SysParamMap(Map<String, Object> params) {
        this.putAll(params);
        if (params.get("pageNum") != null) {
            this.pageNum = Integer.parseInt(params.get("pageNum").toString());
        }

        if (params.get("pageSize") != null) {
            this.pageSize = Integer.parseInt(params.get("pageSize").toString());
        }

        if (params.get("orderBy") != null) {
            this.orderBy = params.get("orderBy").toString();
        }

        this.remove("pageNum");
        this.remove("pageSize");
        this.remove("orderBy");
    }

//    public SysParamMap put(String key,Object value) {
//        this.put(key,value);
//        return this;
//    }

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
