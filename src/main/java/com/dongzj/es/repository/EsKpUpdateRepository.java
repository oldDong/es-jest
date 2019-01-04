package com.dongzj.es.repository;

import com.dongzj.es.domain.EsKpUpdatePo;
import com.dongzj.es.domain.EsSearchResult;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/8
 * Time: 10:19
 */
@Repository
public class EsKpUpdateRepository {

    /**
     * 索引
     */
    private static final String INDEX = "crm_opr_kp_update";

    /**
     * 类型
     */
    private static final String TYPE = "crm_opr_kp_update";

    @Autowired
    private JestClient jestClient;

    public EsSearchResult<EsKpUpdatePo> search(String json) throws IOException {
        Search search = new Search.Builder(json).addIndex(INDEX).addType(TYPE).build();
        SearchResult result = jestClient.execute(search);
        return result(result.getJsonString(), EsKpUpdatePo.class);
    }

    private <T> EsSearchResult<T> result(String searchResult, Class<T> valueType) throws IOException {
        EsSearchResult<T> esSearchResult = new EsSearchResult<T>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map responseMap = objectMapper.readValue(searchResult, Map.class);
        Map hitsMap = (Map) responseMap.get("hits");
        Integer total = (Integer) hitsMap.get("total");
        List<Map> var1 = (List) hitsMap.get("hits");
        JavaType beanType = objectMapper.getTypeFactory().constructParametricType(List.class, valueType);
        List var2 = new ArrayList();
        for (Map map : var1) {
            var2.add(map.get("_source"));
        }
        List<T> l = objectMapper.readValue(objectMapper.writeValueAsString(var2), beanType);
        esSearchResult.setTotal(total);
        esSearchResult.setHits(l);
        return esSearchResult;
    }
}
