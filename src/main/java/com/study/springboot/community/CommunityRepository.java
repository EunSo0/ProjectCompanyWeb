package com.study.springboot.community;

import com.study.springboot.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
  List<Community> findByNoticeTitleContainingIgnoreCase(String keyword);
  List<Community> findByNoticeContentContainingIgnoreCase(String keyword);
  List<Community> findByNoticeMemberIdContainingIgnoreCase(String keyword);

  List<Community> findTop5ByOrderByNoticeMemberIdAsc(); // 아이디 오름차순으로 첫 5개 회원 조회
  List<Community> findTop5ByOrderByNoticeMemberIdDesc(); // 아이디 내림차순으로 첫 5개 회원 조회
  List<Community> findTop5ByOrderByNoticeDateAsc(); // 등록일 오름차순으로 첫 5개 회원 조회
  List<Community> findTop5ByOrderByNoticeDateDesc(); // 등록일 내림차순으로 첫 5개 회원 조회

  List<Community> findTop10ByOrderByNoticeMemberIdAsc(); // 아이디 오름차순으로 첫 10개 회원 조회
  List<Community> findTop10ByOrderByNoticeMemberIdDesc(); // 아이디 내림차순으로 첫 10개 회원 조회
  List<Community> findTop10ByOrderByNoticeDateAsc(); // 등록일 오름차순으로 첫 10개 회원 조회
  List<Community> findTop10ByOrderByNoticeDateDesc(); // 등록일 내림차순으로 첫 10개 회원 조회
}
