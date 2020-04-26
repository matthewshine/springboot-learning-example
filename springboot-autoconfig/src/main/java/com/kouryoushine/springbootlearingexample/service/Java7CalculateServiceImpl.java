package com.kouryoushine.springbootlearingexample.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Profile("java7")
public class Java7CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... values) {
        System.out.println("java7 implement");
        return Stream.of(values).reduce(0,Integer::sum);
    }
}
