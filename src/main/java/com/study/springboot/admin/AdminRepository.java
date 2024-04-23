package com.study.springboot.admin;

import com.study.springboot.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
  Admin findByMemberId(String memberId);
}
