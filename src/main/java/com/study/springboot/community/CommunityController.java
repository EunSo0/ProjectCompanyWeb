package com.study.springboot.community;

import com.study.springboot.community.dto.CommunityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
  private final CommunityService communityService;

  @GetMapping("/community01")
  public String community01(Model model){
    List<CommunityResponseDto> list = communityService.findAll();
    model.addAttribute("list", list);
    return "/community/community01";
  }

  @GetMapping("/search")
  public String search(@RequestParam("searchType") String searchType,
                       @RequestParam("keyword") String keyword,
                       Model model) {
    List<CommunityResponseDto> searchResult;
    if ("title".equals(searchType)) {
      searchResult = communityService.findByTitle(keyword);
    } else if ("content".equals(searchType)) {
      searchResult = communityService.findByContent(keyword);
    } else {
      searchResult = communityService.findAll();
    }
    model.addAttribute("list", searchResult);
    return "/community/community01";
  }

  @GetMapping("/community01_1")
  public String community01_1(@RequestParam Long no, Model model) {
    CommunityResponseDto dto = communityService.findById(no);
    model.addAttribute("dto", dto);
    return "/community/community01_1";
  }
}
