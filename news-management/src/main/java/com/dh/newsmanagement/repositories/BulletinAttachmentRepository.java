package com.dh.newsmanagement.repositories;

import com.dh.newsmanagement.model.BulletinAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinAttachmentRepository extends JpaRepository<BulletinAttachment, Long> {
}
