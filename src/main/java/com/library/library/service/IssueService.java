package com.library.library.service;

public interface IssueService {

    void issueBook(Long bookId, Long memberId);

    void returnBook(Long issueId);
}
