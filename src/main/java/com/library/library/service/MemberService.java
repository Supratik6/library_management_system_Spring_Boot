package com.library.library.service;

import java.util.List;
import com.library.library.entity.Member;

public interface MemberService {

    List<Member> getAllMembers();

    void saveMember(Member member);
}
