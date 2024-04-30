package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.StoryPrompt;
import com.demo.spring.service.StoryGenerationService;

@RestController
public class StoryGenerationController {

    @Autowired
    private StoryGenerationService storyGenerationService;

    @PostMapping("/generateStory")
    public ResponseEntity<String> generateStory(@RequestBody StoryPrompt storyPrompt) {
        String generatedStory = storyGenerationService.generateStory(storyPrompt.getText());
        return new ResponseEntity<>(generatedStory, HttpStatus.OK);
    }
}
