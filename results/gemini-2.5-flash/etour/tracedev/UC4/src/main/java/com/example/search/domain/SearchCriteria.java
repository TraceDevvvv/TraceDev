package com.example.search.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * DTO (Data Transfer Object) representing the criteria for a cultural object search.
 * This object is used to encapsulate search parameters.
 */
public class SearchCriteria {
    private String keyword;
    private String typeFilter;
    private int yearRangeStart;
    private int yearRangeEnd;

    /**
     * Constructs a new SearchCriteria object.
     *
     * @param keyword The main keyword to search for.
     * @param typeFilter An optional filter for the type of cultural object.
     * @param yearRangeStart The start year for a year range filter (inclusive).
     * @param yearRangeEnd The end year for a year range filter (inclusive).
     */
    public SearchCriteria(String keyword, String typeFilter, int yearRangeStart, int yearRangeEnd) {
        this.keyword = keyword;
        this.typeFilter = typeFilter;
        this.yearRangeStart = yearRangeStart;
        this.yearRangeEnd = yearRangeEnd;
    }

    // Getters for all attributes
    public String getKeyword() {
        return keyword;
    }

    public String getTypeFilter() {
        return typeFilter;
    }

    public int getYearRangeStart() {
        return yearRangeStart;
    }

    public int getYearRangeEnd() {
        return yearRangeEnd;
    }

    /**
     * Converts the search criteria into a map suitable for querying external systems like ETOUR.
     * This method is called as per the sequence diagram.
     *
     * @return A Map where keys are query parameters and values are their string representations.
     */
    public Map<String, String> toQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if (keyword != null && !keyword.isEmpty()) {
            queryMap.put("keyword", keyword);
        }
        if (typeFilter != null && !typeFilter.isEmpty()) {
            queryMap.put("type", typeFilter);
        }
        if (yearRangeStart > 0) {
            queryMap.put("yearStart", String.valueOf(yearRangeStart));
        }
        if (yearRangeEnd > 0 && yearRangeEnd >= yearRangeStart) {
            queryMap.put("yearEnd", String.valueOf(yearRangeEnd));
        }
        return queryMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCriteria that = (SearchCriteria) o;
        return yearRangeStart == that.yearRangeStart &&
               yearRangeEnd == that.yearRangeEnd &&
               Objects.equals(keyword, that.keyword) &&
               Objects.equals(typeFilter, that.typeFilter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword, typeFilter, yearRangeStart, yearRangeEnd);
    }
}