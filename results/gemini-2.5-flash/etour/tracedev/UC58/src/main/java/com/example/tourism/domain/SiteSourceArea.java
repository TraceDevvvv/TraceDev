package com.example.tourism.domain;

/**
 * UI Context Enum: Represents the different areas from which a site might be selected in the UI.
 * Used for contextual checks and business rules.
 * Added to satisfy Audit Recommendation for REQ-EC02, REQ-EC03, REQ-EC04.
 */
public enum SiteSourceArea {
    RESEARCH_RESULTS, // Site selected from a list of research results
    VISITED_SITES,    // Site selected from a list of previously visited sites
    FAVORITES         // Site selected from the user's favorites list
}