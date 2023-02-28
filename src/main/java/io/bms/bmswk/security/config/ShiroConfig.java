package io.bms.bmswk.security.config;

import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.security.filter.TokenAuthFilter;
import io.bms.bmswk.security.realm.BmsWkRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import  org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *  shiro configuration, enable cache
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-27 16:52
 */
@Configuration
public class ShiroConfig {

    /** use field to gurantee singleton pattern */
    private final BmsWkRealm theRealm = new BmsWkRealm();

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        bean.setSecurityManager(securityManager);

        // add filter to filter map
        bean.getFilters()
                .put(SecurityConstant.AUTH_FILTER_NAME, new TokenAuthFilter());

        // gen route-FilterName chain
        Map<String, String> chainRouteFilterNameMap = new LinkedHashMap<>();

        // attach auth filter to all route under the "/api/v1/**"
        chainRouteFilterNameMap.put("/api/v1/**", SecurityConstant.AUTH_FILTER_NAME);

        bean.setFilterChainDefinitionMap(chainRouteFilterNameMap);
        return bean;
    }

    /**
     * add realm bean to IoC container
     */
    @Bean
    public BmsWkRealm realmBean() {
        return theRealm;
    }

    /**
     * add security manager to IoC container
     */
    @Bean
    public SecurityManager securityManagerBean() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // attach the realm
        securityManager.setRealm(theRealm);

        // disable session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    /**
     * attach security manager to annotation supporter
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor advisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
