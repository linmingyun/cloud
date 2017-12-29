package com.heimu;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualHelloService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}