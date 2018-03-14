package com.doubles.mvcboard.tutorial.service;

import com.doubles.mvcboard.tutorial.domain.MessageVO;
import com.doubles.mvcboard.tutorial.persistence.MessageDAO;
import com.doubles.mvcboard.tutorial.persistence.PointDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDAO messageDAO;

    private final PointDAO pointDAO;

    @Inject
    public MessageServiceImpl(MessageDAO messageDAO, PointDAO pointDAO) {
        this.messageDAO = messageDAO;
        this.pointDAO = pointDAO;
    }

    @Override
    public void addMessage(MessageVO messageVO) throws Exception {
        messageDAO.create(messageVO);
    }

    @Override
    public MessageVO readMessage(String userId, Integer messageNo) throws Exception {
        messageDAO.updateState(messageNo);
        pointDAO.updatePoint(userId, 5);
        return messageDAO.readMessage(messageNo);
    }
}
