package com.study.springboot.member;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByMemberId(String memberId);
  Member findByMemberNameAndMemberEmail(String memberName, String memberEmail);
  Member findByMemberNameAndMemberEmailAndMemberId(String memberName, String memberEmail, String memberId);


  List<Member> findByMemberIdContainingIgnoreCase(String keyword);
  List<Member> findByMemberNameContainingIgnoreCase(String keyword);
  List<Member> findByMemberEmailContainingIgnoreCase(String keyword);

  List<Member> findTop5ByOrderByMemberIdAsc(); // 아이디 오름차순으로 첫 5개 회원 조회
  List<Member> findTop5ByOrderByMemberIdDesc(); // 아이디 내림차순으로 첫 5개 회원 조회
  List<Member> findTop5ByOrderByMemberJoinDateAsc(); // 가입일 오름차순으로 첫 5개 회원 조회
  List<Member> findTop5ByOrderByMemberJoinDateDesc(); // 가입일 내림차순으로 첫 5개 회원 조회

  List<Member> findTop10ByOrderByMemberIdAsc(); // 아이디 오름차순으로 첫 10개 회원 조회
  List<Member> findTop10ByOrderByMemberIdDesc(); // 아이디 내림차순으로 첫 10개 회원 조회
  List<Member> findTop10ByOrderByMemberJoinDateAsc(); // 가입일 오름차순으로 첫 10개 회원 조회
  List<Member> findTop10ByOrderByMemberJoinDateDesc(); // 가입일 내림차순으로 첫 10개 회원 조회

}
