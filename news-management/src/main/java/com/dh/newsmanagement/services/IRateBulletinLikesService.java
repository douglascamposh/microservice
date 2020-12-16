package com.dh.newsmanagement.services;

import com.dh.newsmanagement.model.RateBulletinLikes;

import java.util.List;

public interface IRateBulletinLikesService {
    RateBulletinLikes findByRateBulletinLikeId(Long id);
    List<RateBulletinLikes> findAllRateBulletinLikes();
    RateBulletinLikes saveRateBulletinLike(RateBulletinLikes rateBulletinLikes);
}
