package com.github.rawsanj.kube;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class FunctionController {

    private final LanguageRepository languageRepository;

    public FunctionController(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @GetMapping
    ResponseEntity<List<Language>> getLanguage(){
        List<Language> languages = languageRepository.findAll();
        return ResponseEntity.ok(languages);
    }

    @PostMapping
    ResponseEntity<Language> addLanguage(@RequestBody Language language){
        Language lang = languageRepository.save(language);
        return ResponseEntity.ok(lang);
    }


}
