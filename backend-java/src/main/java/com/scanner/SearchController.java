package com.scanner.controller;

import com.scanner.model.SearchResult;
import com.scanner.service.GitCloneService;
import com.scanner.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {

    private final GitCloneService gitService;
    private final SearchService searchService;

    public SearchController(GitCloneService gitService, SearchService searchService) {
        this.gitService = gitService;
        this.searchService = searchService;
    }

    @PostMapping
    public List<SearchResult> search(
            @RequestParam String repoUrl,
            @RequestParam String serviceId
    ) throws Exception {

        String repoPath = gitService.cloneRepo(repoUrl);

        return searchService.search(repoPath, serviceId);
    }
}
