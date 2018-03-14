package com.doubles.mvcboard.tutorial.controller;

import com.doubles.mvcboard.tutorial.domain.MessageVO;
import com.doubles.mvcboard.tutorial.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Inject
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> addMessage(@RequestBody MessageVO messageVO) {

        ResponseEntity<String> entity = null;
        try {
            messageService.addMessage(messageVO);
            entity = new ResponseEntity<>("addSuccess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

}
