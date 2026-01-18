package com.example.banner.interfaceadapters.controller;

/**
 * Controller port for banner operations.
 */
public interface BannerControllerPort {
    Object deleteBanner(String bannerId, String pointOfRestId, String operatorId);
}