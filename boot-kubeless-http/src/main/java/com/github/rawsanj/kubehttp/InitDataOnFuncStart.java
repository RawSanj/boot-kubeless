package com.github.rawsanj.kubehttp;


import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDataOnFuncStart {

    private final LanguageRepository languageRepository;

    public InitDataOnFuncStart(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Bean
    ApplicationRunner addSomeDataOnFuncStartUp(){
        return applicationArguments -> {
            languageRepository.save(new Language("Java", 5f));
        };
    }
}
