package com.cn.tonyou.shiro;
import java.io.IOException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
/**
 * 继承FreeMarkerConfigurer类,重写afterPropertiesSet()方法；
 * 集成shiroTags标签
 * Created by zsc on 2016/1/5.
 */
public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer {
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }

}
