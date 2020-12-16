package com.dh.newsmanagement.repositories;

import com.dh.newsmanagement.model.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> {
}
