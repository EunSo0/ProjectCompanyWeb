package com.study.springboot.member;

import com.study.springboot.community.Community;
import com.study.springboot.community.dto.CommunityResponseDto;
import com.study.springboot.member.dto.MembeSaveRequestDto;
import com.study.springboot.member.dto.MemberFindDto;
import com.study.springboot.member.dto.MemberLoginDto;
import com.study.springboot.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  @Transactional(readOnly = true)
  public List<MemberResponseDto> findAll() {
    Sort sort = Sort.by(Sort.Direction.DESC, "memberJoinDate", "memberIdx");
    List<Member> list = memberRepository.findAll(sort);

    return list.stream().map(MemberResponseDto::new).collect(Collectors.toList());
  }

  @Transactional
  public Long save(final MembeSaveRequestDto dto){
    Member entity = memberRepository.save(dto.toEntity());
    return entity.getMemberIdx();
  }

  @Transactional(readOnly = true)
  public boolean existsById(Long boardIdx){
    boolean isFound = memberRepository.existsById(boardIdx);
    return isFound;
  }

  @Transactional(readOnly = true)
  public Member existsMember(final MemberLoginDto dto){
    Member member = memberRepository.findByMemberId(dto.getMemberId());
    return member;
  }

  @Transactional(readOnly = true)
  public boolean dupleId(String userId){
    Member member = memberRepository.findByMemberId(userId);
    return member != null;
  }

  @Transactional(readOnly = true)
  public String findId(final MemberFindDto dto){
    Member member = memberRepository.findByMemberNameAndMemberEmail(dto.getMemberName(), dto.getMemberEmail());
    if(member == null){
      return null;
    } else {
      return member.getMemberId();
    }
  }

  @Transactional(readOnly = true)
  public String findPw(final MemberFindDto dto){
    Member member = memberRepository.findByMemberNameAndMemberEmailAndMemberId(dto.getMemberName(), dto.getMemberEmail(), dto.getMemberId());
    if(member == null){
      return null;
    } else {
      return member.getMemberPw();
    }
  }

  @Transactional(readOnly = true)
  public List<MemberResponseDto> search(String option, String keyword) {
    switch (option) {
      case "id":
        return memberRepository.findByMemberIdContainingIgnoreCase(keyword)
            .stream()
            .map(MemberResponseDto::new)
            .collect(Collectors.toList());
      case "name":
        System.out.println(keyword);
        return memberRepository.findByMemberNameContainingIgnoreCase(keyword)
            .stream()
            .map(MemberResponseDto::new)
            .collect(Collectors.toList());
      case "email":
        return memberRepository.findByMemberEmailContainingIgnoreCase(keyword)
            .stream()
            .map(MemberResponseDto::new)
            .collect(Collectors.toList());
      case "all":
        List<MemberResponseDto> result = new ArrayList<>();
        result.addAll(memberRepository.findByMemberIdContainingIgnoreCase(keyword)
            .stream()
            .map(MemberResponseDto::new)
            .toList());
        result.addAll(memberRepository.findByMemberNameContainingIgnoreCase(keyword)
            .stream()
            .map(MemberResponseDto::new)
            .toList());
        result.addAll(memberRepository.findByMemberEmailContainingIgnoreCase(keyword)
            .stream()
            .map(MemberResponseDto::new)
            .toList());
        return result;
      default:
        return memberRepository.findAll()
            .stream()
            .map(MemberResponseDto::new)
            .collect(Collectors.toList());
    }
  }

  @Transactional(readOnly = true)
  public List<MemberResponseDto> sort(String option) {
    switch (option) {
      case "id_asc":
        Sort sort = Sort.by(Sort.Direction.ASC, "memberId");
        List<Member> list = memberRepository.findAll(sort);
        return list.stream().map(MemberResponseDto::new).collect(Collectors.toList());
      case "id_desc":
        Sort sort2 = Sort.by(Sort.Direction.DESC, "memberId");
        List<Member> list2 = memberRepository.findAll(sort2);
        return list2.stream().map(MemberResponseDto::new).collect(Collectors.toList());
      case "join_date_asc":
        Sort sort3 = Sort.by(Sort.Direction.ASC, "memberJoinDate");
        List<Member> list3 = memberRepository.findAll(sort3);
        return list3.stream().map(MemberResponseDto::new).collect(Collectors.toList());
      case "join_date_desc":
        Sort sort4 = Sort.by(Sort.Direction.DESC, "memberJoinDate");
        List<Member> list4 = memberRepository.findAll(sort4);
        return list4.stream().map(MemberResponseDto::new).collect(Collectors.toList());
      default:
        throw new IllegalArgumentException("Invalid sort option: " + option);
    }
  }

  @Transactional(readOnly = true)
  public List<MemberResponseDto> findMembers(String pageSize, String orderOption) {
    Sort sort;
    switch (orderOption) {
      case "id_asc":
        sort = Sort.by(Sort.Direction.ASC, "memberId");
        break;
      case "id_desc":
        sort = Sort.by(Sort.Direction.DESC, "memberId");
        break;
      case "join_date_asc":
        sort = Sort.by(Sort.Direction.ASC, "memberJoinDate");
        break;
      case "join_date_desc":
        sort = Sort.by(Sort.Direction.DESC, "memberJoinDate");
        break;
      default:
        throw new IllegalArgumentException("Invalid sort option: " + orderOption);
    }

    switch (pageSize) {
      case "page5":
        if (orderOption.endsWith("_asc")) {
          if (orderOption.startsWith("id")) {
            return memberRepository.findTop5ByOrderByMemberIdAsc()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
          } else {
            return memberRepository.findTop5ByOrderByMemberJoinDateAsc()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
          }
        } else {
          if (orderOption.startsWith("id")) {
            return memberRepository.findTop5ByOrderByMemberIdDesc()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
          } else {
            return memberRepository.findTop5ByOrderByMemberJoinDateDesc()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
          }
        }
      case "page10":
        if (orderOption.endsWith("_asc")) {
          if (orderOption.startsWith("id")) {
            return memberRepository.findTop10ByOrderByMemberIdAsc()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
          } else {
            return memberRepository.findTop10ByOrderByMemberJoinDateAsc()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
          }
        } else {
          if (orderOption.startsWith("id")) {
            return memberRepository.findTop10ByOrderByMemberIdDesc()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
          } else {
            return memberRepository.findTop10ByOrderByMemberJoinDateDesc()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
          }
        }
      case "pageAll":
        List<Member> members = memberRepository.findAll(sort);
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
      default:
        throw new IllegalArgumentException("Invalid page size option: " + pageSize);
    }
  }



}
