package com.kouryoushine.springbootapplication.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Profile("java8")
public class Java8CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... values) {
        System.out.println("java8 implement");
        return Stream.of(values).reduce(0,Integer::sum);
    }
}
