package com.github.rawsanj.kubehttp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class FunctionController {

    private final Logger logger = LoggerFactory.getLogger(FunctionController.class);

    private final LanguageRepository languageRepository;

    public FunctionController(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @GetMapping
    ResponseEntity<List<Language>> getLanguage(){
        List<Language> languages = languageRepository.findAll();
        logger.info("Returning no. Languages: {}", languages.size());

        return ResponseEntity.ok(languages);
    }

    @PostMapping
    ResponseEntity<Language> addLanguage(@RequestBody Language language){
        logger.info("Saving Languages: {}", language.toString());
        Language lang = languageRepository.save(language);
        return ResponseEntity.ok(lang);
    }


}
