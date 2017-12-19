package com.doubles.ex05.service;

import com.doubles.ex05.persistence.UploadDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Inject
    private UploadDAO uploadDAO;

    // 게시글 첨부파일 조회
    @Override
    public List<String> getAttach(Integer bno) throws Exception {
        return uploadDAO.getAttach(bno);
    }

    // 게시글 첨부파일 삭제
    @Override
    public void deleteAttach(String fullName) throws Exception {
        uploadDAO.deleteAttach(fullName);
    }

    // 특정 게시글의 첨부파일 갯수 갱신
    @Override
    public void updateAttachCnt(Integer bno) throws Exception {
        uploadDAO.updateAttachCnt(bno);
    }
}
