package com.library.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.library.service.IssueService;

@Controller
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // Issue a book
    @PostMapping("/issue")
    public String issueBook(@RequestParam Long bookId,
                            @RequestParam Long memberId) {

        issueService.issueBook(bookId, memberId);
        return "redirect:/";
    }

    // Return a book
    @PostMapping("/return")
    public String returnBook(@RequestParam Long issueId) {

        issueService.returnBook(issueId);
        return "redirect:/";
    }
}
