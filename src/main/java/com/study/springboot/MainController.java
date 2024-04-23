package com.study.springboot;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
  @GetMapping("/")
  public String main(){
    return "index"; //index.html로 응답
  }
  @GetMapping("/member/login")
  public String login(){
    return "/member/login";
  }
  @GetMapping("/member/join")
  public String join(){
    return "/member/join2"; //join2.html 응답
  }
  @GetMapping("/member/idFind")
  public String idFind(){
    return "/member/idFind"; //idFind.html 응답
  }
  @GetMapping("/member/passwordFind")
  public String passwordFind(){
    return "/member/passwordFind"; //idFind.html 응답
  }

  @GetMapping("/company/company01")
  public String company01(){
    return "/company/company01";
  }
  @GetMapping("/company/company03")
  public String company03(){
    return "/company/company03";
  }
  @GetMapping("/business/business01")
  public String business01(){
    return "/business/business01";
  }
  @GetMapping("/product/product01")
  public String product01(){
    return "/product/product01";
  }


  @GetMapping("/customer/customer01")
  public String customer01(HttpSession session){
    Boolean isLogin = (Boolean) session.getAttribute("isLogin");

    if (isLogin != null && isLogin) {
      return "/customer/customer01";
    } else {
      return "redirect:/member/login?error=1";
    }
  }
  @GetMapping("/customer/customer02_3")
  public String customer02_3(){
    return "/customer/customer02_3";
  }
  @GetMapping("/customer/customer03")
  public String customer03(HttpSession session){
    Boolean isLogin = (Boolean) session.getAttribute("isLogin");

    if (isLogin != null && isLogin) {
      return "/customer/customer03";
    } else {
      return "redirect:/member/login?error=1";
    }
  }

  @GetMapping("/admin")
  public String adminLogin(){
    return "/admin/admin_login";
  }
}