package com.etour.banner.service;

import com.etour.banner.exception.BannerLimitExceededException;
import com.etour.banner.exception.ImageValidationException;
import com.etour.banner.exception.ResourceNotFoundException;
import com.etour.banner.model.Banner;
import com.etour.banner.model.RefreshmentPoint;
import com.etour.banner.repository.BannerRepository;
import com.etour.banner.repository.RefreshmentPointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BannerServiceTest {

    @Mock
    private BannerRepository bannerRepository;

    @Mock
    private RefreshmentPointRepository refreshmentPointRepository;

    @Mock
    private ImageService imageService;

    @Mock
    private EtourClient etourClient;

    @InjectMocks
    private BannerService bannerService;

    private RefreshmentPoint testRefreshmentPoint;
    private MultipartFile validImage;

    @BeforeEach
    void setUp() {
        testRefreshmentPoint = new RefreshmentPoint("rp001", "Central Cafe", 2);
        validImage = new MockMultipartFile(
                "image",
                "test.png",
                "image/png",
                "test image content".getBytes()
        );
    }

    @Test
    void insertBanner_Success() {
        // Arrange
        when(refreshmentPointRepository.findById(testRefreshmentPoint.getId())).thenReturn(Optional.of(testRefreshmentPoint));
        doNothing().when(imageService).validateImage(validImage);
        when(bannerRepository.findByRefreshmentPointId(testRefreshmentPoint.getId())).thenReturn(Collections.emptyList());
        when(imageService.storeImage(validImage)).thenReturn("unique_image_name.png");
        when(imageService.getImageUrl("unique_image_name.png")).thenReturn("/images/unique_image_name.png");

        Banner savedBanner = new Banner("b001", testRefreshmentPoint.getId(), "/images/unique_image_name.png", LocalDateTime.now());
        when(bannerRepository.save(any(Banner.class))).thenReturn(savedBanner);
        doNothing().when(etourClient).notifyBannerInsertion(any(Banner.class), any(RefreshmentPoint.class));

        // Act
        Banner result = bannerService.insertBanner(validImage, testRefreshmentPoint.getId());

        // Assert
        assertNotNull(result);
        assertEquals("b001", result.getId());
        assertEquals(testRefreshmentPoint.getId(), result.getRefreshmentPointId());
        assertEquals("/images/unique_image_name.png", result.getImageUrl());

        verify(refreshmentPointRepository, times(1)).findById(testRefreshmentPoint.getId());
        verify(imageService, times(1)).validateImage(validImage);
        verify(bannerRepository, times(1)).findByRefreshmentPointId(testRefreshmentPoint.getId());
        verify(imageService, times(1)).storeImage(validImage);
        verify(bannerRepository, times(1)).save(any(Banner.class));
        verify(etourClient, times(1)).notifyBannerInsertion(any(Banner.class), any(RefreshmentPoint.class));
    }

    @Test
    void insertBanner_RefreshmentPointNotFound_ThrowsResourceNotFoundException() {
        // Arrange
        when(refreshmentPointRepository.findById(testRefreshmentPoint.getId())).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () ->
                bannerService.insertBanner(validImage, testRefreshmentPoint.getId())
        );
        assertEquals("Refreshment Point not found with ID: rp001", thrown.getMessage());

        verify(refreshmentPointRepository, times(1)).findById(testRefreshmentPoint.getId());
        verifyNoInteractions(imageService, bannerRepository, etourClient);
    }

    @Test
    void insertBanner_ImageValidationFails_ThrowsImageValidationException() {
        // Arrange
        when(refreshmentPointRepository.findById(testRefreshmentPoint.getId())).thenReturn(Optional.of(testRefreshmentPoint));
        doThrow(new ImageValidationException("Invalid image")).when(imageService).validateImage(validImage);

        // Act & Assert
        ImageValidationException thrown = assertThrows(ImageValidationException.class, () ->
                bannerService.insertBanner(validImage, testRefreshmentPoint.getId())
        );
        assertEquals("Invalid image", thrown.getMessage());

        verify(refreshmentPointRepository, times(1)).findById(testRefreshmentPoint.getId());
        verify(imageService, times(1)).validateImage(validImage);
        verifyNoInteractions(bannerRepository, etourClient);
    }

    @Test
    void insertBanner_BannerLimitExceeded_ThrowsBannerLimitExceededException() {
        // Arrange
        when(refreshmentPointRepository.findById(testRefreshmentPoint.getId())).thenReturn(Optional.of(testRefreshmentPoint));
        doNothing().when(imageService).validateImage(validImage);

        List<Banner> existingBanners = Arrays.asList(
                new Banner("b001", testRefreshmentPoint.getId(), "url1", LocalDateTime.now()),
                new Banner("b002", testRefreshmentPoint.getId(), "url2", LocalDateTime.now())
        );
        when(bannerRepository.findByRefreshmentPointId(testRefreshmentPoint.getId())).thenReturn(existingBanners);

        // Act & Assert
        BannerLimitExceededException thrown = assertThrows(BannerLimitExceededException.class, () ->
                bannerService.insertBanner(validImage, testRefreshmentPoint.getId())
        );
        assertTrue(thrown.getMessage().contains("Maximum number of banners (2) reached"));

        verify(refreshmentPointRepository, times(1)).findById(testRefreshmentPoint.getId());
        verify(imageService, times(1)).validateImage(validImage);
        verify(bannerRepository, times(1)).findByRefreshmentPointId(testRefreshmentPoint.getId());
        verifyNoMoreInteractions(imageService); // storeImage should not be called
        verifyNoInteractions(etourClient);
    }

    @Test
    void insertBanner_EtourClientNotificationFails_BannerStillSaved() {
        // Arrange
        when(refreshmentPointRepository.findById(testRefreshmentPoint.getId())).thenReturn(Optional.of(testRefreshmentPoint));
        doNothing().when(imageService).validateImage(validImage);
        when(bannerRepository.findByRefreshmentPointId(testRefreshmentPoint.getId())).thenReturn(Collections.emptyList());
        when(imageService.storeImage(validImage)).thenReturn("unique_image_name.png");
        when(imageService.getImageUrl("unique_image_name.png")).thenReturn("/images/unique_image_name.png");

        Banner savedBanner = new Banner("b001", testRefreshmentPoint.getId(), "/images/unique_image_name.png", LocalDateTime.now());
        when(bannerRepository.save(any(Banner.class))).thenReturn(savedBanner);
        doThrow(new RuntimeException("ETOUR server down")).when(etourClient).notifyBannerInsertion(any(Banner.class), any(RefreshmentPoint.class));

        // Act
        Banner result = bannerService.insertBanner(validImage, testRefreshmentPoint.getId());

        // Assert
        assertNotNull(result); // Banner should still be saved
        assertEquals("b001", result.getId());

        verify(bannerRepository, times(1)).save(any(Banner.class));
        verify(etourClient, times(1)).notifyBannerInsertion(any(Banner.class), any(RefreshmentPoint.class));
        // Verify that the exception was caught and handled (logged)
        // This is hard to test directly without capturing System.err, but we can ensure the flow continued.
    }

    @Test
    void getRefreshmentPoints_Success() {
        // Arrange
        RefreshmentPoint rp1 = new RefreshmentPoint("rp001", "Central Cafe", 3);
        RefreshmentPoint rp2 = new RefreshmentPoint("rp002", "Parkside Bistro", 2);
        List<RefreshmentPoint> fetchedPoints = Arrays.asList(rp1, rp2);

        when(etourClient.fetchRefreshmentPoints()).thenReturn(fetchedPoints);
        when(refreshmentPointRepository.existsById(rp1.getId())).thenReturn(false);
        when(refreshmentPointRepository.existsById(rp2.getId())).thenReturn(true); // Simulate one existing
        when(refreshmentPointRepository.save(rp1)).thenReturn(rp1); // Only rp1 should be saved
        when(refreshmentPointRepository.findAll()).thenReturn(fetchedPoints);

        // Act
        List<RefreshmentPoint> result = bannerService.getRefreshmentPoints();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(rp1));
        assertTrue(result.contains(rp2));

        verify(etourClient, times(1)).fetchRefreshmentPoints();
        verify(refreshmentPointRepository, times(1)).existsById(rp1.getId());
        verify(refreshmentPointRepository, times(1)).existsById(rp2.getId());
        verify(refreshmentPointRepository, times(1)).save(rp1); // Only rp1 should be saved
        verify(refreshmentPointRepository, times(1)).findAll();
    }

    @Test
    void getRefreshmentPoints_NoNewPoints() {
        // Arrange
        RefreshmentPoint rp1 = new RefreshmentPoint("rp001", "Central Cafe", 3);
        List<RefreshmentPoint> fetchedPoints = Collections.singletonList(rp1);

        when(etourClient.fetchRefreshmentPoints()).thenReturn(fetchedPoints);
        when(refreshmentPointRepository.existsById(rp1.getId())).thenReturn(true); // Simulate all existing
        when(refreshmentPointRepository.findAll()).thenReturn(fetchedPoints);

        // Act
        List<RefreshmentPoint> result = bannerService.getRefreshmentPoints();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(rp1));

        verify(etourClient, times(1)).fetchRefreshmentPoints();
        verify(refreshmentPointRepository, times(1)).existsById(rp1.getId());
        verify(refreshmentPointRepository, never()).save(any(RefreshmentPoint.class)); // No new points saved
        verify(refreshmentPointRepository, times(1)).findAll();
    }
}