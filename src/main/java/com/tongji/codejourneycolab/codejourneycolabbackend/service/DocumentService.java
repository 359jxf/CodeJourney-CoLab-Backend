package com.tongji.codejourneycolab.codejourneycolabbackend.service;

import com.tongji.codejourneycolab.codejourneycolabbackend.dto.DocumentInfoDto;

import java.util.List;

public interface DocumentService {

    /// TODO: remove some of them, and make private

    Boolean isOwner(Integer userId, Integer documentId);

    Boolean isCollaborator(Integer userId, Integer documentId);

    Integer joinCollaboration(Integer userId, String colabCode);

    String getContent(Integer userId, Integer documentId);

    DocumentInfoDto getDocumentInfo(Integer userId , Integer documentId);

    void updateContent(Integer userId ,String documentCode, String new_content);

    void deleteDocument(Integer ownerId, Integer documentId);

    DocumentInfoDto createDocument(Integer ownerId,String title);

    String getDocumentShareCode(Integer ownerId, Integer documentId);

    List<DocumentInfoDto> getDocumentInfoList(Integer userId);

}
