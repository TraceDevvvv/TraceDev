package com.example;

/**
 * View model for a single note.
 */
public class NoteViewModel {
    private String content;
    private String formattedDate;

    /**
     * Constructs a NoteViewModel with the given content and formatted date.
     *
     * @param content the note content
     * @param formattedDate the formatted date string
     */
    public NoteViewModel(String content, String formattedDate) {
        this.content = content;
        this.formattedDate = formattedDate;
    }

    /**
     * Gets the note content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the formatted date.
     *
     * @return the formatted date
     */
    public String getFormattedDate() {
        return formattedDate;
    }
}