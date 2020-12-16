package com.dh.newsmanagement.repositories;

import com.dh.newsmanagement.model.RateBulletinLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateBulletinLikesRepository extends JpaRepository<RateBulletinLikes, Long> {
}
