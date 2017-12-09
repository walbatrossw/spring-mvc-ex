package com.doubles.ex03.persistence;

import com.doubles.ex03.domain.MessageVO;

public interface MessageDAO {

    public void create(MessageVO messageVO) throws Exception;

    public MessageVO readMessage(Integer mno) throws Exception;

    public void updateState(Integer mno) throws Exception;

}
