package com.hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    
    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        // Thread A : A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        
        // Thread B : B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);
        
        // Thread A : 사용자 A가 주문 금액을 조회
        // int price = statefulService1.getPrice();

        // 10000원이 나오길 기대했지만, 20000원이 나온다 (B가 중간에 끼어들었기 때문)
        System.out.println("price = " + userAPrice);

        // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }
    
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}