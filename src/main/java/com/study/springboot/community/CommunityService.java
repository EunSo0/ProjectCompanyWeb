package com.study.springboot.community;

import com.study.springboot.community.dto.CommunityResponseDto;
import com.study.springboot.community.dto.CommunitySaveRequestDto;
import com.study.springboot.member.Member;
import com.study.springboot.member.dto.MembeSaveRequestDto;
import com.study.springboot.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {
  private final CommunityRepository communityRepository;

  @Transactional(readOnly = true)
  public List<CommunityResponseDto> findAll() {
    Sort sort = Sort.by(Sort.Direction.DESC, "noticeDate", "noticeMemberId");
    List<Community> list = communityRepository.findAll(sort);

    return list.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public CommunityResponseDto findById(Long noticeIdx){
    Community entity
        = communityRepository.findById( noticeIdx )
        .orElseThrow( () -> new IllegalArgumentException(
            "없는 글인덱스입니다.noticeIdx:"+noticeIdx));
    return new CommunityResponseDto(entity);
  }

  @Transactional(readOnly = true)
  public boolean existsById(Long noticeIdx){
    boolean isFound = communityRepository.existsById(noticeIdx);
    return isFound;
  }

  @Transactional
  public Long save(final CommunitySaveRequestDto dto){
    Community entity = communityRepository.save(dto.toEntity());
    return entity.getNoticeIdx();
  }

  @Transactional
  public Community update(final Long noticeIdx, final CommunitySaveRequestDto dto){
    Community entity = communityRepository.findById(noticeIdx)
        .orElseThrow( () -> new IllegalArgumentException("해당 글 인덱스가 없습니다. noticeIdx:"+noticeIdx));
    entity.update(dto.getNoticeTitle(), dto.getNoticeContent(), dto.getNoticeMemberId());
    return entity;
  }

  @Transactional(readOnly = true)
  public List<CommunityResponseDto> findByTitle(String keyword) {
    List<Community> communities = communityRepository.findByNoticeTitleContainingIgnoreCase(keyword);
    return communities.stream()
        .map(CommunityResponseDto::new)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<CommunityResponseDto> findByContent(String keyword) {
    List<Community> communities = communityRepository.findByNoticeContentContainingIgnoreCase(keyword);
    return communities.stream()
        .map(CommunityResponseDto::new)
        .collect(Collectors.toList());
  }


  @Transactional(readOnly = true)
  public List<CommunityResponseDto> search(String option, String keyword) {
    switch (option) {
      case "title":
        return communityRepository.findByNoticeTitleContainingIgnoreCase(keyword)
            .stream()
            .map(CommunityResponseDto::new)
            .collect(Collectors.toList());
      case "content":
        System.out.println(keyword);
        return communityRepository.findByNoticeContentContainingIgnoreCase(keyword)
            .stream()
            .map(CommunityResponseDto::new)
            .collect(Collectors.toList());
      case "id":
        return communityRepository.findByNoticeMemberIdContainingIgnoreCase(keyword)
            .stream()
            .map(CommunityResponseDto::new)
            .collect(Collectors.toList());
      case "all":
        List<CommunityResponseDto> result = new ArrayList<>();
        result.addAll(communityRepository.findByNoticeTitleContainingIgnoreCase(keyword)
            .stream()
            .map(CommunityResponseDto::new)
            .toList());
        result.addAll(communityRepository.findByNoticeContentContainingIgnoreCase(keyword)
            .stream()
            .map(CommunityResponseDto::new)
            .toList());
        result.addAll(communityRepository.findByNoticeMemberIdContainingIgnoreCase(keyword)
            .stream()
            .map(CommunityResponseDto::new)
            .toList());
        return result;
      default:
        return communityRepository.findAll()
            .stream()
            .map(CommunityResponseDto::new)
            .collect(Collectors.toList());
    }
  }

  @Transactional(readOnly = true)
  public List<CommunityResponseDto> sort(String option) {
    switch (option) {
      case "id_asc":
        Sort sort = Sort.by(Sort.Direction.ASC, "noticeMemberId");
        List<Community> list = communityRepository.findAll(sort);
        return list.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
      case "id_desc":
        Sort sort2 = Sort.by(Sort.Direction.DESC, "noticeMemberId");
        List<Community> list2 = communityRepository.findAll(sort2);
        return list2.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
      case "reg_date_asc":
        Sort sort3 = Sort.by(Sort.Direction.ASC, "noticeDate");
        List<Community> list3 = communityRepository.findAll(sort3);
        return list3.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
      case "reg_date_desc":
        Sort sort4 = Sort.by(Sort.Direction.DESC, "noticeDate");
        List<Community> list4 = communityRepository.findAll(sort4);
        return list4.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
      default:
        throw new IllegalArgumentException("Invalid sort option: " + option);
    }
  }

  @Transactional(readOnly = true)
  public List<CommunityResponseDto> findNotice(String pageSize, String orderOption) {
    Sort sort;
    switch (orderOption) {
      case "id_asc":
        sort = Sort.by(Sort.Direction.ASC, "noticeMemberId");
        break;
      case "id_desc":
        sort = Sort.by(Sort.Direction.DESC, "noticeMemberId");
        break;
      case "reg_date_asc":
        sort = Sort.by(Sort.Direction.ASC, "noticeDate");
        break;
      case "reg_date_desc":
        sort = Sort.by(Sort.Direction.DESC, "noticeDate");
        break;
      default:
        throw new IllegalArgumentException("Invalid sort option: " + orderOption);
    }

    switch (pageSize) {
      case "page5":
        if (orderOption.endsWith("_asc")) {
          if (orderOption.startsWith("id")) {
            return communityRepository.findTop5ByOrderByNoticeMemberIdAsc()
                .stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
          } else {
            return communityRepository.findTop5ByOrderByNoticeDateAsc()
                .stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
          }
        } else {
          if (orderOption.startsWith("id")) {
            return communityRepository.findTop5ByOrderByNoticeMemberIdDesc()
                .stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
          } else {
            return communityRepository.findTop5ByOrderByNoticeDateDesc()
                .stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
          }
        }
      case "page10":
        if (orderOption.endsWith("_asc")) {
          if (orderOption.startsWith("id")) {
            return communityRepository.findTop10ByOrderByNoticeMemberIdAsc()
                .stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
          } else {
            return communityRepository.findTop10ByOrderByNoticeDateAsc()
                .stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
          }
        } else {
          if (orderOption.startsWith("id")) {
            return communityRepository.findTop10ByOrderByNoticeMemberIdDesc()
                .stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
          } else {
            return communityRepository.findTop10ByOrderByNoticeDateDesc()
                .stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
          }
        }
      case "pageAll":
        List<Community> notice = communityRepository.findAll(sort);
        return notice.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
      default:
        throw new IllegalArgumentException("Invalid page size option: " + pageSize);
    }
  }
}
