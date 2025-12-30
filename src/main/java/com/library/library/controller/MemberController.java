package com.library.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.library.library.entity.Member;
import com.library.library.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Show all members
    @GetMapping("/members")
    public String viewMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "members";
    }

    // Show add member form
    @GetMapping("/addMemberForm")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "add-member";
    }

    // Save member
    @PostMapping("/saveMember")
    public String saveMember(@ModelAttribute("member") Member member) {
        memberService.saveMember(member);
        return "redirect:/members";
    }
}
