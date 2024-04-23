package com.study.springboot.admin;

import com.study.springboot.ResDto;
import com.study.springboot.community.Community;
import com.study.springboot.community.CommunityService;
import com.study.springboot.community.dto.CommunityResponseDto;
import com.study.springboot.community.dto.CommunitySaveRequestDto;
import com.study.springboot.customer.qna.dto.QnAResponseDto;
import com.study.springboot.member.Member;
import com.study.springboot.member.MemberService;
import com.study.springboot.member.dto.MemberLoginDto;
import com.study.springboot.member.dto.MemberResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

  private final MemberService memberService;
  private final AdminService adminService;
  private final CommunityService communityService;


  @GetMapping("/admin_notice_write")
  public String adminNoticeWrite(){
    return "/admin/admin_notice_write";
  }

  @GetMapping("/admin_member")
  public String adminMember(Model model){
    List<MemberResponseDto> list = memberService.findAll();
    model.addAttribute("list", list);
    model.addAttribute("count", list.size());
    return "/admin/admin_member";
  }

  @GetMapping("/admin_notice")
  public String adminNotice(Model model){
    List<CommunityResponseDto> list = communityService.findAll();
    model.addAttribute("list", list);
    model.addAttribute("count", list.size());
    return "/admin/admin_notice";
  }

  @GetMapping("/admin_notice_view")
  public String adminNoticeView(@RequestParam Long notice_idx, Model model){
    CommunityResponseDto dto = communityService.findById(notice_idx);
    model.addAttribute("dto", dto);
    return "/admin/admin_notice_view";
  }

  @PostMapping("/loginAction")
  @ResponseBody
  public ResDto loginAction(@RequestBody MemberLoginDto dto, HttpSession session){
    Admin admin = adminService.existsMember(dto);
    if(admin != null){
      if(admin.getMemberPw().equals(dto.getMemberPw())){
        session.setAttribute("isLogin", true);
        session.setAttribute("memberId", admin.getMemberId());

        return ResDto.builder()
            .status("ok").message("로그인되었습니다.")
            .build();
      } else{
        return ResDto.builder()
            .status("fail").message("비밀번호가 다릅니다.")
            .build();
      }
    }else{
      return ResDto.builder()
          .status("fail").message("아이디가 존재하지 않습니다.")
          .build();
    }
  }

  @PostMapping("/admin_notice_write")
  @ResponseBody
  public ResDto writeNotice(@RequestBody CommunitySaveRequestDto dto){
    Long newIdx = communityService.save(dto);

    boolean isFound = communityService.existsById(newIdx);
    if(isFound) {
      return ResDto.builder()
          .status("ok").message("글을 등록하였습니다.")
          .build();
    } else {
      return ResDto.builder()
          .status("fail").message("글 등록에 실패했습니다.")
          .build();
    }
  }

  @PostMapping("/admin_notice_modify")
  @ResponseBody
  public ResDto modifyNotice(@RequestBody CommunitySaveRequestDto dto,
                             @RequestParam("notice_idx") Long notice_idx){
    Community entity = communityService.update(notice_idx, dto);
    if(Objects.equals(entity.getNoticeIdx(), notice_idx)){
      return ResDto.builder()
          .status("ok").message(notice_idx+"")
          .build();
    }else{
      return ResDto.builder()
      .status("fail").message("글 수정 실패했습니다.")
          .build();
    }
  }

  @GetMapping("/searchMembers")
  @ResponseBody
  public List<MemberResponseDto> searchMembers(@RequestParam String option, @RequestParam String keyword) {
    List<MemberResponseDto> members = memberService.search(option, keyword);
    System.out.println(members.size());
    return members;
  }

  @GetMapping("/sortMembers")
  @ResponseBody
  public List<MemberResponseDto> sortMembers(@RequestParam String option) {
    List<MemberResponseDto> members = memberService.sort(option);
    return members;
  }

  @GetMapping("/getMembers")
  @ResponseBody
  public List<MemberResponseDto> findMembers(String pageSize, String orderOption) {
    List<MemberResponseDto> members = memberService.findMembers(pageSize, orderOption);
    return members;
  }

  @GetMapping("/searchNotice")
  @ResponseBody
  public List<CommunityResponseDto> searchNotice(@RequestParam String option, @RequestParam String keyword) {
    List<CommunityResponseDto> notice = communityService.search(option, keyword);
    return notice;
  }

  @GetMapping("/sortNotice")
  @ResponseBody
  public List<CommunityResponseDto> sortNotice(@RequestParam String option) {
    List<CommunityResponseDto> notice = communityService.sort(option);
    return notice;
  }

  @GetMapping("/getNotice")
  @ResponseBody
  public List<CommunityResponseDto> findNotice(String pageSize, String orderOption) {
    List<CommunityResponseDto> notice = communityService.findNotice(pageSize,orderOption);
    return notice;
  }
}
