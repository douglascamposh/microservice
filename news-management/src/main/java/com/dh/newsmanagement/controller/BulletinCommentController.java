package com.dh.newsmanagement.controller;

import com.dh.newsmanagement.constants.HeaderConstants;
import com.dh.newsmanagement.dto.BulletinCommentDTO;
import com.dh.newsmanagement.dto.UserDTO;
import com.dh.newsmanagement.model.Bulletin;
import com.dh.newsmanagement.model.BulletinComment;
import com.dh.newsmanagement.services.IBulletinCommentService;
import com.dh.newsmanagement.services.IBulletinService;
import com.dh.newsmanagement.utils.Utils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(BulletinCommentController.BASE_URL)
public class BulletinCommentController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${dh.user.server.base_url}")
    private String userManagementBaseUrl;

    public static final String BASE_URL = "/api/v1/bulletins";
    private static final Logger logger = LoggerFactory.getLogger(BulletinController.class);
    private final IBulletinCommentService bulletinCommentService;
    private final IBulletinService bulletinService;

    public BulletinCommentController(IBulletinCommentService bulletinCommentService, IBulletinService bulletinService) {
        this.bulletinCommentService = bulletinCommentService;
        this.bulletinService = bulletinService;
    }

    @GetMapping(value = "/{id}/comments", name = "The identifier of the post from which\n" +
            "you want to list all your comments.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Account-Id", value = "The user's account value",paramType = "header", dataType = "integer", format = "integer"),
            @ApiImplicitParam(name = "User-Id", value = "The user's identifier", paramType = "header", dataType = "integer", format = "integer")})
    @ResponseStatus(HttpStatus.OK)
    public Page<BulletinCommentDTO> findAllCommentsByBulletinId(
            HttpServletRequest request,
            @PathVariable("id") final Long id,
            Pageable pageable
//            @RequestParam(value = HeaderConstants.PAGE) final int page,
//            @RequestParam(value = HeaderConstants.LIMIT) final int limit
    ) {
        Long accountId = Utils.validateHeaderNumber(request, HeaderConstants.ACCOUNT_ID);
        Long userId = Utils.validateHeaderNumber(request, HeaderConstants.USER_ID);

        Optional<Bulletin> optionalBulletin = bulletinService.findBulletinById(id);
        if(!optionalBulletin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Does not exist Bulletin with Id:" + id);
        }

        Page<BulletinComment> bulletinCommentPage = bulletinCommentService.findAllBulletinComments(optionalBulletin.get(), pageable);

        List<BulletinCommentDTO> bulletinCommentDTOList = new ArrayList<>();
        bulletinCommentPage.getContent().forEach((bulletinComment) -> {

            UserDTO user = restTemplate.getForObject( userManagementBaseUrl + HeaderConstants.USER_RESOURCES + "{userId}" , UserDTO.class, userId);

            BulletinCommentDTO bulletinCommentDTO = BulletinCommentDTO.builder()
                    .commentId(bulletinComment.getId())
                    .accountId(accountId)
                    .content(bulletinComment.getComment().getTextId())
                    .repliesCounter(bulletinComment.getRepliesCounter())
                    .wallMessageId(bulletinComment.getBulletin().getId())
                    .createdDate(bulletinComment.getCreatedDate())
                    .isDeleted(bulletinComment.getIsDeleted())
                    .senderUser(user)
                    .build();
            bulletinCommentDTOList.add(bulletinCommentDTO);
        });

        return new PageImpl<BulletinCommentDTO>(bulletinCommentDTOList, pageable, bulletinCommentPage.getTotalElements());
    }
}
