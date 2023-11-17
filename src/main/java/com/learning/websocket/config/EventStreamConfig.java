package com.learning.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@Configuration
public class EventStreamConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters (List<HttpMessageConverter<?>> converters) {
        converters.add(new EventStreamHttpMessageConverter());
    }

    public class EventStreamHttpMessageConverter<T> extends AbstractHttpMessageConverter<T> {

        public EventStreamHttpMessageConverter() {
            super(new MediaType("text", "event-stream"));
        }

        @Override
        protected boolean supports(Class<?> clazz) {
            return false;
        }

        @Override
        protected T readInternal(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
            return null;
        }

        @Override
        protected void writeInternal(T t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        }

    }

}
