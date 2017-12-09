package com.doubles.ex03.service;

import com.doubles.ex03.domain.MessageVO;
import com.doubles.ex03.persistence.MessageDAO;
import com.doubles.ex03.persistence.PointDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class MessageServiceImpl implements MessageService {

    @Inject
    private MessageDAO messageDAO;

    @Inject
    private PointDAO pointDAO;

    @Transactional
    @Override
    public void addMessage(MessageVO messageVO) throws Exception {

        // 메시지 작성 + 전송
        messageDAO.create(messageVO);
        // 메시지 전송 포인트 적립
        pointDAO.updatePoint(messageVO.getSender(), 10);

    }

    @Override
    public MessageVO readMessage(String uid, Integer mno) throws Exception {

        // 메시지 열람 일자 수정
        messageDAO.updateState(mno);
        // 메시지 열람 포인트 적립
        pointDAO.updatePoint(uid, 5);

        // 메시지 조회/열람
        return messageDAO.readMessage(mno);
    }
}
