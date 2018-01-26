package com.github.rawsanj.kube;


import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InitDataOnFuncStart {

    private final LanguageRepository languageRepository;

    public InitDataOnFuncStart(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Bean
    ApplicationRunner addSomeDataOnFuncStartUp(){
        return applicationArguments -> {
            List<Language> languages = Arrays.asList(new Language("Java", 4f),
                                                        new Language("C#", 2f),
                                                        new Language("JavaScript", 3f),
                                                        new Language("Kotlin", 3.5f));
            languageRepository.save(languages);
        };
    }
}
