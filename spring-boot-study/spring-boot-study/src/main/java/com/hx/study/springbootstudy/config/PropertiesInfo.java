package com.hx.study.springbootstudy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "study.env")
@Data
public class PropertiesInfo {
    private String activeProfile;
}
