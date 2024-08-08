package com.example.maids_project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class modelMapperConfig {


    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
