package com.doubles.mvcboard.tutorial.service;

import com.doubles.mvcboard.tutorial.domain.MessageVO;

public interface MessageService {

    void addMessage(MessageVO messageVO) throws Exception;

    MessageVO readMessage(String userId, Integer messageNo) throws Exception;

}
