package com.yuandian.core.utils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author twjitm 2019/4/6/21:20
 */
public class ClassLoadUtil {


    public static List<Class> getSubClasses(final Class parentClass,
                                            final String packagePath) {
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
                false);
        provider.addIncludeFilter(new AssignableTypeFilter(parentClass));
        final Set<BeanDefinition> components = provider
                .findCandidateComponents(packagePath);
        final List<Class> subClasses = new ArrayList<Class>();
        for (final BeanDefinition component : components) {
            @SuppressWarnings("unchecked") final Class cls;
            try {
                cls = Class.forName(component
                        .getBeanClassName());
                if (Modifier.isAbstract(cls.getModifiers())) {
                    continue;
                }
                subClasses.add(cls);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return subClasses;
    }

}
