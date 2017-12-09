package com.doubles.ex03.service;

import com.doubles.ex03.domain.MessageVO;

import javax.inject.Inject;

public interface MessageService {

    public void addMessage(MessageVO messageVO) throws Exception;

    public MessageVO readMessage(String uid, Integer mno) throws Exception;

}
