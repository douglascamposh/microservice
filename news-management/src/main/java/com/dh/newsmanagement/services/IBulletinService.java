package com.dh.newsmanagement.services;

import com.dh.newsmanagement.model.Bulletin;
import com.dh.newsmanagement.model.BulletinAttachment;
import com.dh.newsmanagement.model.BulletinComment;
import com.dh.newsmanagement.model.Comment;

import java.util.List;
import java.util.Optional;

public interface IBulletinService {
    Optional<Bulletin> findBulletinById(Long id);
    List<Bulletin> findAllBulletins();
    Bulletin saveBulletin(Bulletin bulletin);
    Bulletin saveBulletinWithAttachments(Bulletin bulletin, List<BulletinAttachment> attachments);
    BulletinComment saveBulletinComment(Bulletin bulletin, BulletinComment bulletinComment, Comment comment);
}
