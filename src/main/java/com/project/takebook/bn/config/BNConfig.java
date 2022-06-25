package com.project.takebook.bn.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BNConfig {

    @Value("${bn.api.endpoint.prod}")
    private String bnApiEndpoint;

}
