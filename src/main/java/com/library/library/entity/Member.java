package com.library.library.entity;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role; // STUDENT / FACULTY
    private int maxIssueLimit;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public int getMaxIssueLimit() { return maxIssueLimit; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
    public void setMaxIssueLimit(int maxIssueLimit) { this.maxIssueLimit = maxIssueLimit; }
}
