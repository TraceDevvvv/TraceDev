package com.example.ports;

public interface SmosServerClient {
    boolean archiveNote(String noteId);
    boolean deleteFromArchive(String noteId);
}