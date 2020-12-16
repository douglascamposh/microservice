package com.dh.newsmanagement.services.impl;

import com.dh.newsmanagement.model.RateBulletinLikes;
import com.dh.newsmanagement.repositories.RateBulletinLikesRepository;
import com.dh.newsmanagement.services.IRateBulletinLikesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateBulletinLikesImpl implements IRateBulletinLikesService {

    private RateBulletinLikesRepository rateBulletinLikesRepository;

    public RateBulletinLikesImpl(RateBulletinLikesRepository rateBulletinLikesRepository) {
        this.rateBulletinLikesRepository = rateBulletinLikesRepository;
    }

    @Override
    public RateBulletinLikes findByRateBulletinLikeId(Long id) {
        return rateBulletinLikesRepository.findById(id).get();
    }

    @Override
    public List<RateBulletinLikes> findAllRateBulletinLikes() {
        return rateBulletinLikesRepository.findAll();
    }

    @Override
    public RateBulletinLikes saveRateBulletinLike(RateBulletinLikes rateBulletinLikes) {
        return rateBulletinLikesRepository.save(rateBulletinLikes);
    }
}
