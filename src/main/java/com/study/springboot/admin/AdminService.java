package com.study.springboot.admin;

import com.study.springboot.member.Member;
import com.study.springboot.member.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
  private final AdminRepository adminRepository;

  @Transactional(readOnly = true)
  public Admin existsMember(final MemberLoginDto dto){
    Admin admin = adminRepository.findByMemberId(dto.getMemberId());
    return admin;
  }
}
