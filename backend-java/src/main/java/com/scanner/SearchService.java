package com.scanner.service;

import com.scanner.model.SearchResult;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Service
public class SearchService {

    public List<SearchResult> search(String repoPath, String serviceId) throws Exception {

        List<SearchResult> results = new ArrayList<>();

        Files.walk(Paths.get(repoPath))
                .filter(Files::isRegularFile)
                .filter(p ->
                        p.toString().matches(".*\\.(java|yml|yaml|properties|sh|md|xml|json)$")
                )
                .forEach(path -> scanFile(path, serviceId, results));

        return results;
    }

    private void scanFile(Path path, String serviceId, List<SearchResult> results) {

        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {

            String line;
            int lineNum = 1;

            while ((line = br.readLine()) != null) {

                if (line.contains(serviceId)) {
                    results.add(
                            new SearchResult(
                                    path.toString(),
                                    lineNum,
                                    line.trim()
                            )
                    );
                }

                lineNum++;
            }

        } catch (Exception ignored) {}
    }
}
