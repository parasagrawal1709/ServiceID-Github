package com.scanner.model;

public class SearchResult {

    private String fileName;
    private int lineNumber;
    private String snippet;

    public SearchResult(String fileName, int lineNumber, String snippet) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.snippet = snippet;
    }

    public String getFileName() { return fileName; }
    public int getLineNumber() { return lineNumber; }
    public String getSnippet() { return snippet; }
}
