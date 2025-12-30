package com.library.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.library.entity.IssueRecord;

public interface IssueRepository extends JpaRepository<IssueRecord, Long> {}
