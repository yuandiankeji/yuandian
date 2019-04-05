package com.yuandian.config;

import com.robert.vesta.service.factory.IdServiceFactoryBean;
import com.robert.vesta.service.intf.IdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.robert.vesta.service.factory.IdServiceFactoryBean.Type.PROPERTY;

/**
 * @author: luyufeng
 * @Date: 2019/4/5
 */

@Configuration
public class IdGeneratorConfig {

    @Bean
    public IdService getIdService() throws Exception {
        IdServiceFactoryBean bean = new IdServiceFactoryBean();
        bean.setProviderType(PROPERTY);
        bean.init();
        IdService idService = bean.getObject();
        return idService;
    }
}
