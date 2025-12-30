package com.library.library.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.library.library.entity.Book;
import com.library.library.entity.IssueRecord;
import com.library.library.entity.Member;
import com.library.library.repository.BookRepository;
import com.library.library.repository.IssueRepository;
import com.library.library.repository.MemberRepository;
import com.library.library.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final IssueRepository issueRepository;

    public IssueServiceImpl(BookRepository bookRepository,
                            MemberRepository memberRepository,
                            IssueRepository issueRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.issueRepository = issueRepository;
    }

    @Override
    public void issueBook(Long bookId, Long memberId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (book.getCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        book.setCopies(book.getCopies() - 1);
        bookRepository.save(book);

        IssueRecord record = new IssueRecord();
        record.setBook(book);
        record.setMember(member);
        record.setIssueDate(LocalDate.now());

        issueRepository.save(record);
    }

    @Override
    public void returnBook(Long issueId) {

        IssueRecord record = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        record.setReturnDate(LocalDate.now());

        Book book = record.getBook();
        book.setCopies(book.getCopies() + 1);

        bookRepository.save(book);
        issueRepository.save(record);
    }
}
