package com.wy.es.elasticsearch.dao.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


/**
 * @author WY
 * @Description:
 * @date 2019/7/17
 */
@Configuration
@Setter
@Getter
public class IndexNameConfig {

    private String material = "material-" + LocalDate.now();

    @Bean
    public String material(){
        return material;
    }



}
