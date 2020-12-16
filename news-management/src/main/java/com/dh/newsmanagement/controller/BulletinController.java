package com.dh.newsmanagement.controller;

import com.dh.newsmanagement.constants.HeaderConstants;
import com.dh.newsmanagement.dto.BulletinDTO;
import com.dh.newsmanagement.dto.CommentDTO;
import com.dh.newsmanagement.model.Bulletin;
import com.dh.newsmanagement.model.BulletinAttachment;
import com.dh.newsmanagement.model.BulletinComment;
import com.dh.newsmanagement.model.Comment;
import com.dh.newsmanagement.services.IBulletinCommentService;
import com.dh.newsmanagement.services.IBulletinService;
import com.dh.newsmanagement.utils.Utils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(BulletinController.BASE_URL)
public class BulletinController {

    public static final String BASE_URL = "/api/v1/bulletins";
    private final IBulletinService bulletinService;
    private static final Logger logger = LoggerFactory.getLogger(BulletinController.class);

    public BulletinController(IBulletinService bulletinService, IBulletinCommentService bulletinCommentService) {
        this.bulletinService = bulletinService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Bulletin> getAllBulletins() {
        return  bulletinService.findAllBulletins();
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public Bulletin getBulletinById(@PathVariable Long id) {
        return bulletinService.findBulletinById(id).get();
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Account-Id", value = "The user's account value", paramType = "header", dataType = "integer", format = "integer"),
            @ApiImplicitParam(name = "User-Id", value = "The user's identifier", paramType = "header", dataType = "integer", format = "integer")})
    @ResponseStatus(HttpStatus.CREATED)
    public Bulletin saveBulletin(HttpServletRequest request, @RequestBody BulletinDTO bulletinDTO) {
        Long accountId = Utils.validateHeaderNumber(request, HeaderConstants.ACCOUNT_ID);
        Long userId = Utils.validateHeaderNumber(request, HeaderConstants.USER_ID);

        List<BulletinAttachment> attachments = new ArrayList<>();
        bulletinDTO.getFields().forEach((field) -> {
            BulletinAttachment attachment = BulletinAttachment.builder()
                    .accountId(accountId)
                    .field(field)
                    .build();
            attachments.add(attachment);
        });

        Bulletin bulletin = new Bulletin();
        bulletin.setAccountId(accountId);
        bulletin.setSenderUserId(userId);
        bulletin.setTextId(bulletinDTO.getContent());
        return bulletinService.saveBulletinWithAttachments(bulletin, attachments);
    }

    @DeleteMapping(value = "/{id}", name = "The identifier of the post to which you want to comment.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Account-Id", value = "The user's account value",paramType = "header", dataType = "integer", format = "integer"),
            @ApiImplicitParam(name = "User-Id", value = "The user's identifier", paramType = "header", dataType = "integer", format = "integer")})
    @ResponseStatus(HttpStatus.OK)
    public void deleteBulletin(HttpServletRequest request, @PathVariable("id") final Long id) {
        Utils.validateHeaderNumber(request, HeaderConstants.ACCOUNT_ID);
        Utils.validateHeaderNumber(request, HeaderConstants.USER_ID);
        Optional<Bulletin> optionalBulletin = bulletinService.findBulletinById(id);
        if(!optionalBulletin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bulletin Not found with Id:" + id);
        }
        Bulletin bulletin = optionalBulletin.get();
        bulletin.setIsDeleted(true);
        bulletinService.saveBulletin(bulletin);
    }

    @PostMapping(value = "/{id}/comments")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Account-Id", value = "The user's account value",paramType = "header", dataType = "integer", format = "integer"),
            @ApiImplicitParam(name = "User-Id", value = "The user's identifier", paramType = "header", dataType = "integer", format = "integer")})
    @ResponseStatus(HttpStatus.OK)
    public BulletinComment addCommentToBulletin(
            HttpServletRequest request,
            @PathVariable("id") final Long id,
            @RequestBody CommentDTO commentDTO
    ) {
        Long accountId = Utils.validateHeaderNumber(request, HeaderConstants.ACCOUNT_ID);
        Long userId = Utils.validateHeaderNumber(request, HeaderConstants.USER_ID);

        Optional<Bulletin> optionalBulletin = bulletinService.findBulletinById(id);
        if(!optionalBulletin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bulletin Not found with Id:" + id);
        }

        Bulletin bulletin = optionalBulletin.get();
        bulletin.setCommentsCounter(bulletin.getCommentsCounter() + 1);

        BulletinComment bulletinComment = new BulletinComment();
        bulletinComment.setAccountId(accountId);
        bulletinComment.setBulletin(bulletin);

        Comment comment = new Comment();
        comment.setAccountId(accountId);
        comment.setSenderUserId(userId);
        comment.setTextId(commentDTO != null ? commentDTO.getContent() : "");

        return bulletinService.saveBulletinComment(bulletin, bulletinComment, comment);
    }

}
