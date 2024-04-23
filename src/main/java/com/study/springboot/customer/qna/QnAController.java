package com.study.springboot.customer.qna;

import com.study.springboot.ResDto;
import com.study.springboot.customer.qna.dto.QnAResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RequiredArgsConstructor
@Controller
public class QnAController {
  private final QnAService qnAService;

  @GetMapping("/customer02")
  public String customer02(Model model, HttpSession session){
    List<QnAResponseDto> list = qnAService.findAll();
    model.addAttribute("list", list);

    Boolean isLogin = (Boolean) session.getAttribute("isLogin");
    if (isLogin != null && isLogin) {
      return "/customer/customer02";
    } else {
      return "redirect:/member/login?error=1";
    }
  }

  @GetMapping("/search")
  public String search(@RequestParam("searchType") String searchType,
                       @RequestParam("keyword") String keyword,
                       Model model) {
    List<QnAResponseDto> searchResult;
    if ("title".equals(searchType)) {
      searchResult = qnAService.findByTitleSearch(keyword);
    } else if ("content".equals(searchType)) {
      searchResult = qnAService.findByContentSearch(keyword);
    } else if ("author".equals(searchType)) {
      searchResult = qnAService.findByAuthorSearch(keyword);
    } else {
      searchResult = qnAService.findByTitleSearch(keyword);
    }
    model.addAttribute("list", searchResult);
    return "/customer/customer02";
  }

  @GetMapping("/qnaPwdAction")
  @ResponseBody
  public ResDto qnaPwdAction(@RequestParam Long qna_index, @RequestParam String pwd){
    boolean isTrue = qnAService.findByPwd(pwd);
    if(isTrue) {
      return ResDto.builder()
          .status("ok").message(qna_index+"")
          .build();
    } else {
      return ResDto.builder()
          .status("fail").message("비밀번호가 틀렸습니다.")
          .build();
    }
  }

  @GetMapping("/customer02_2")
  public String customer02_2(){
    return "/customer/customer02_2";
  }

  @GetMapping("/customer02_4")
  public String customer02_4(@RequestParam Long qna_index, Model model){
    QnAResponseDto dto = qnAService.findById(qna_index);
    model.addAttribute("dto", dto);
    return "/customer/customer02_4";
  }


}
