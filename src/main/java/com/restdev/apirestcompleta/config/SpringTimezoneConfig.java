package com.restdev.apirestcompleta.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.TimeZone;

@Configurable
public class SpringTimezoneConfig {
    @PostConstruct
    public void timeZoneConfig(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

}
