package com.yuandian;

import static com.robert.vesta.service.factory.IdServiceFactoryBean.Type.PROPERTY;
import static org.junit.Assert.assertTrue;

import com.robert.vesta.service.factory.IdServiceFactoryBean;
import com.robert.vesta.service.intf.IdService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        IdServiceFactoryBean bean = new IdServiceFactoryBean();
        bean.setProviderType(PROPERTY);
        bean.init();
        IdService idService = bean.getObject();
        long id = idService.genId();
        System.out.println(id);
    }
}
