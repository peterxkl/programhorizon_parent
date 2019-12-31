package com.dillon.config;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.time.LocalDateTime;

@Component
public class SaveMongoEventListener extends AbstractMongoEventListener<Object> {

    /*
    注意：这个类使用来激活@CreatedDate注解的
     */
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        final Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            if (field.isAnnotationPresent(CreatedDate.class) && field.get(source) == null) {
                field.set(source, LocalDateTime.now());
            }
            if (field.isAnnotationPresent(LastModifiedDate.class)) {
                field.set(source, LocalDateTime.now());
            }
        });
    }
}