package com.dh.newsmanagement.controller;

import com.dh.newsmanagement.model.RateBulletinLikes;
import com.dh.newsmanagement.services.IRateBulletinLikesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RateBulletinLikesController.BASE_URL)
public class RateBulletinLikesController {

    public static final String BASE_URL = "/api/v1/rateBulletinLikes";
    private final IRateBulletinLikesService rateBulletinLikesService;

    public RateBulletinLikesController(IRateBulletinLikesService rateBulletinLikesService) {
        this.rateBulletinLikesService = rateBulletinLikesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RateBulletinLikes> getAllRateBulletinLikes() {
        return  rateBulletinLikesService.findAllRateBulletinLikes();
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public RateBulletinLikes getRateBulletinLikesById(@PathVariable Long id) {
        return rateBulletinLikesService.findByRateBulletinLikeId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RateBulletinLikes saveRateBulletinLikes(@RequestBody RateBulletinLikes rateBulletinLikes) {
        return rateBulletinLikesService.saveRateBulletinLike(rateBulletinLikes);
    }
}
