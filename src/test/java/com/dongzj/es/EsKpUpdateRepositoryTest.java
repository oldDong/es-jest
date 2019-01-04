package com.dongzj.es;

import com.dongzj.es.domain.EsKpUpdatePo;
import com.dongzj.es.domain.EsSearchResult;
import com.dongzj.es.repository.EsKpUpdateRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/8
 * Time: 10:52
 */
public class EsKpUpdateRepositoryTest extends EsApplicationTests {

    @Autowired
    private EsKpUpdateRepository esKpUpdateRepository;

    @Test
    public void testSearch() {
        String json = "{\"query\" : {\"match\" : {\"depttitles\" : \"党委书记\"}}}";
        try {
            EsSearchResult<EsKpUpdatePo> kpUpdatePoEsSearchResult = esKpUpdateRepository.search(json);
            System.out.println(kpUpdatePoEsSearchResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
