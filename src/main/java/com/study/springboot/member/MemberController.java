package com.study.springboot.member;

import com.study.springboot.ResDto;
import com.study.springboot.member.dto.DupleDto;
import com.study.springboot.member.dto.MembeSaveRequestDto;
import com.study.springboot.member.dto.MemberFindDto;
import com.study.springboot.member.dto.MemberLoginDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @PostMapping("/joinAction")
  public ResDto joinAction(@RequestBody MembeSaveRequestDto dto){
    Long newIdx = memberService.save(dto);

    boolean isFound = memberService.existsById(newIdx);
    if(isFound) {
      return ResDto.builder()
          .status("ok").message("회원가입이 완료되었습니다.")
          .build();
    } else {
      return ResDto.builder()
          .status("fail").message("회원가입에 실패했습니다.")
          .build();
    }
  }

  @PostMapping("/loginAction")
  public ResDto loginAction(@RequestBody MemberLoginDto dto, HttpSession session){
    Member member = memberService.existsMember(dto);
    if(member != null){
      if(member.getMemberPw().equals(dto.getMemberPw())){
        session.setAttribute("isLogin", true);
        session.setAttribute("memberId", member.getMemberId());

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

  @GetMapping("/logoutAction")
  public String logoutAction(HttpSession session){
    session.setAttribute("isLogin", null);
    session.setAttribute("userId", null);

    session.invalidate();

    return "<script>alert('로그아웃되었습니다.'); location.href='/';</script>";
  }

  @PostMapping("/joinIdDuple")
  public ResDto loginIdDuple(@RequestBody DupleDto dto){
    boolean isDuple = memberService.dupleId(dto.getMemberId());
    if(isDuple){
      return ResDto.builder()
          .status("fail").message("아이디가 중복됩니다.")
          .build();
    } else{
      return ResDto.builder()
          .status("ok").message("아이디 사용가능합니다.")
          .build();
    }
  }

  @PostMapping("/idFind")
  public ResDto findId(@RequestBody MemberFindDto dto){
    String id = memberService.findId(dto);
    System.out.println(id);
    if(id == null){
      return ResDto.builder()
          .status("fail").message("일치하는 회원정보가 없습니다.")
          .build();
    } else{
      return ResDto.builder()
          .status("ok").message("회원님의 아이디는 " + id + " 입니다.")
          .build();
    }
  }

  @PostMapping("/passwordFind")
  public ResDto findPw(@RequestBody MemberFindDto dto){
    String pwd = memberService.findPw(dto);
    if(pwd == null){
      return ResDto.builder()
          .status("fail").message("일치하는 회원정보가 없습니다.")
          .build();
    } else{
      return ResDto.builder()
          .status("ok").message("회원님의 암호는 " + pwd + " 입니다.")
          .build();
    }
  }
}
