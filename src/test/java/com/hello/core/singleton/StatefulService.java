package com.hello.core.singleton;

public class StatefulService {
    // 공유 변수로 사용하지 않도록 한다.
    // private int price; // 상태를 유지하는 필드 / 10000 -> 20000

    // void가 아닌 int로 바꾸고 price를 return 하도록 한다.
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; // <- 이 부분이 문제이다.
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
