package com.hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 시작위치 정하기, 이 패키지를 포함해 하위 패키지를 모두 탐색한다.
        // basePackages = "com.hello.core.member",

        // 자동 스캔 대상에서 제외시키기 : Configuration (이전 예제 AppConfig의 Configuration까지 포함되지 않도록 하기 위함)
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
