package com.dh.newsmanagement.services;

import com.dh.newsmanagement.model.Bulletin;
import com.dh.newsmanagement.model.BulletinComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBulletinCommentService {
    Page<BulletinComment> findAllBulletinComments(Bulletin bulletin, Pageable pageable);
}
