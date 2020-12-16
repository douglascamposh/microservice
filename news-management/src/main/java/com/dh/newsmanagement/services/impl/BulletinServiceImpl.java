package com.dh.newsmanagement.services.impl;

import com.dh.newsmanagement.model.Bulletin;
import com.dh.newsmanagement.model.BulletinAttachment;
import com.dh.newsmanagement.model.BulletinComment;
import com.dh.newsmanagement.model.Comment;
import com.dh.newsmanagement.repositories.BulletinAttachmentRepository;
import com.dh.newsmanagement.repositories.BulletinCommentRepository;
import com.dh.newsmanagement.repositories.BulletinRepository;
import com.dh.newsmanagement.repositories.CommentRepository;
import com.dh.newsmanagement.services.IBulletinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BulletinServiceImpl implements IBulletinService {

    private final BulletinRepository bulletinRepository;
    private final BulletinAttachmentRepository bulletinAttachmentRepository;
    private final BulletinCommentRepository bulletinCommentRepository;
    private final CommentRepository commentRepository;

    public BulletinServiceImpl(BulletinRepository bulletinRepository, BulletinAttachmentRepository bulletinAttachmentRepository, BulletinCommentRepository bulletinCommentRepository, CommentRepository commentRepository) {
        this.bulletinRepository = bulletinRepository;
        this.bulletinAttachmentRepository = bulletinAttachmentRepository;
        this.bulletinCommentRepository = bulletinCommentRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Bulletin> findBulletinById(Long id) {
        return bulletinRepository.findById(id);
    }

    @Override
    public List<Bulletin> findAllBulletins() {
        return bulletinRepository.findAll();
    }

    @Override
    public Bulletin saveBulletin(Bulletin bulletin) {
        return bulletinRepository.save(bulletin);
    }

    @Override
    @Transactional
    public Bulletin saveBulletinWithAttachments(Bulletin bulletin, List<BulletinAttachment> attachments) {
        Bulletin newBulletin = bulletinRepository.save(bulletin);
        attachments.forEach((attachment) -> {
            attachment.setBulletin(newBulletin);
            bulletinAttachmentRepository.save(attachment);
        });
        return newBulletin;
    }

    @Override
    @Transactional
    public BulletinComment saveBulletinComment(Bulletin bulletin, BulletinComment bulletinComment, Comment comment) {
        bulletinRepository.save(bulletin);
        commentRepository.save(comment);
        bulletinComment.setComment(comment);
        bulletinComment.setBulletin(bulletin);
        return bulletinCommentRepository.save(bulletinComment);
    }
}
