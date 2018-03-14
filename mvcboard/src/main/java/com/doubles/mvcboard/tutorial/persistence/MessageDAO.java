package com.doubles.mvcboard.tutorial.persistence;

import com.doubles.mvcboard.tutorial.domain.MessageVO;

public interface MessageDAO {

    void create(MessageVO messageVO) throws Exception;

    MessageVO readMessage(Integer messageNo) throws Exception;

    void updateState(Integer messageNo) throws Exception;

}
