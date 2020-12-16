package com.dh.newsmanagement.repositories;

import com.dh.newsmanagement.model.Bulletin;
import com.dh.newsmanagement.model.BulletinComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinCommentRepository extends PagingAndSortingRepository<BulletinComment, Long> {
    Page<BulletinComment> findAllByBulletin (Bulletin bulletin, Pageable pageable);
}
