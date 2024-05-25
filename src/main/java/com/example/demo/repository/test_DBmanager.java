package com.example.demo.repository;

import com.example.demo.db.*;
import java.time.LocalDateTime;

public class test_DBmanager {
    private repo_Issue repoIssue;
    private repo_Comment repoComment;
    private repo_User repoUser;
    private repo_Project repoProject;
    private repo_Log repoLog;

    public void addPL(String userID, String username, String password) {
        table_User user = new table_User();
        user.setId(userID);
        user.setUsername(username);
        user.setPassword(password);
        user.setLevel(1);
        repoUser.save(user);
    }
    public void addDev(String userID, String username, String password) {
        table_User user = new table_User();
        user.setId(userID);
        user.setUsername(username);
        user.setPassword(password);
        user.setLevel(2);
        repoUser.save(user);
    }
    public void addTester(String userID, String username, String password) {
        table_User user = new table_User();
        user.setId(userID);
        user.setUsername(username);
        user.setPassword(password);
        user.setLevel(3);
        repoUser.save(user);
    }

    public void addProject(Long PID, String title) {
        table_Project project = new table_Project();
        project.setPid(PID);
        project.setTitle(title);
        repoProject.save(project);
    }
    public void addIssue(Long ID, Long PID, String title, String description, String id_reporter, String id_assignee, int priority, int type) {
        table_Issue issue = new table_Issue();
        issue.setId(ID);
        issue.setPriority(priority);
        issue.setType(type);
        issue.setTitle(title);
        issue.setDescription(description);
        issue.setPid(repoProject.getReferenceById(PID));
        issue.setReporter(repoUser.getReferenceById(id_reporter));
        issue.setAssignee(repoUser.getReferenceById(id_assignee));
        issue.setStatus(0);
        repoIssue.save(issue);
    }
	public void addLog(Long ID, Long PID, String message) {
        table_Log log = new table_Log();
        LocalDateTime t=LocalDateTime.now();
        log.setId(ID);
        log.setProject_id(repoProject.getReferenceById(PID));
        log.setLog(message);
        log.setDate(t);
        repoLog.save(log);
    }
    public void addComment(Long CID, Long Issue_ID, String message) {
        table_Comment comment = new table_Comment();
        LocalDateTime t=LocalDateTime.now();
        comment.setCid(CID);
        comment.setIssue_id(repoIssue.getReferenceById(Issue_ID));
        comment.setComment(message);
        comment.setDate(t);
        repoComment.save(comment);
    }
}
