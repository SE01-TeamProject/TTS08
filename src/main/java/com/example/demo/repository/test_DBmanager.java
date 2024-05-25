package com.example.demo.repository;

import com.example.demo.db.*;
import com.example.demo.domain.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class test_DBmanager {
    private repo_Issue repoIssue;
    private repo_Comment repoComment;
    private repo_User repoUser;
    private repo_Project repoProject;
    private repo_Log repoLog;

    public void addPL(String userID, String username, String password, int lvl) {
        PL pl = new PL(userID, username, password, lvl);
        table_User user = new table_User();
        user.setId();
        repoUser.save(pl);
    }
}
