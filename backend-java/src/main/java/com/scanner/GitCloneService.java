package com.scanner.service;

import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class GitCloneService {

    public String cloneRepo(String repoUrl) throws Exception {

        String dir = "repos/" + System.currentTimeMillis();

        Git.cloneRepository()
                .setURI(repoUrl)
                .setDirectory(new File(dir))
                .call();

        return dir;
    }
}
