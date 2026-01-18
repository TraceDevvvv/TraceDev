package com.example.agency;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of NewsService.
 */
public class NewsServiceImpl implements NewsService {
    private NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News getNewsById(int newsId) {
        return newsRepository.findById(newsId);
    }

    @Override
    public boolean updateNews(News updatedNews) {
        try {
            News saved = newsRepository.save(updatedNews);
            return saved != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}