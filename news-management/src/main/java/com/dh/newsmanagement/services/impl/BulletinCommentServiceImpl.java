package com.dh.newsmanagement.services.impl;

import com.dh.newsmanagement.model.Bulletin;
import com.dh.newsmanagement.model.BulletinComment;
import com.dh.newsmanagement.repositories.BulletinCommentRepository;
import com.dh.newsmanagement.services.IBulletinCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BulletinCommentServiceImpl implements IBulletinCommentService {

    private final BulletinCommentRepository bulletinCommentRepository;

    public BulletinCommentServiceImpl(BulletinCommentRepository bulletinCommentRepository) {
        this.bulletinCommentRepository = bulletinCommentRepository;
    }

    @Override
    public Page<BulletinComment> findAllBulletinComments(Bulletin bulletin, Pageable pageable) {
//        Pageable pageable = (Pageable) PageRequest.of(page, limit);

        return bulletinCommentRepository.findAllByBulletin(bulletin, pageable);

    }
}
