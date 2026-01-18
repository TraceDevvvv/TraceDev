package com.tourist.feedback.controller;

import com.tourist.feedback.dto.VisitedSiteResponse;
import com.tourist.feedback.service.VisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ViewVisitedSites用例的REST控制器
 * 处理获取用户访问过的站点列表的API请求，包括用户认证验证、数据查询、错误处理和边界情况处理
 * 这个类实现了ViewVisitedSites用例的所有REST API端点
 * 
 * 核心功能：
 * 1. 获取用户访问过的站点列表（核心API）
 * 2. 支持时间范围筛选的API
 * 3. 支持评分筛选的API
 * 4. 支持推荐站点筛选的API
 * 5. 支持搜索功能的API
 * 6. 处理服务器连接中断等边界情况
 * 7. 提供数据导出和统计API
 * 
 * 根据PRD文档的技术规格，实现以下API端点：
 * GET /api/users/{userId}/visited-sites - 获取用户访问过的站点列表
 */
@RestController
@RequestMapping("/api/users/{userId}/visited-sites")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Visitor API", description = "ViewVisitedSites用例的REST API接口")
public class VisitorController {
    
    private final VisitorService visitorService;
    
    /**
     * 获取用户访问过的站点列表（核心API方法）
 * 实现ViewVisitedSites用例的主要功能：显示游客已发布反馈的站点列表
 * 
 * @param userId 用户唯一标识符，必须为已认证的用户ID
 * @param pageable 分页参数，包含页码、每页大小和排序信息
 * 这个方法是ViewVisitedSites用例的入口点，对应事件流程中的第一步
     * 
     * @return 包含访问站点列表的响应实体
     */
    @GetMapping
    @Operation(summary = "获取用户访问过的站点列表", description = "根据用户ID获取其访问过并有反馈记录的站点列表")
    public ResponseEntity<?> getVisitedSites(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long userId,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("收到获取用户访问站点列表请求，用户ID: {}, 分页参数: {}", userId, pageable);
        
        try {
            // 调用Service层获取用户访问过的站点列表
            List<VisitedSiteResponse> visitedSites = visitorService.getVisitedSites(userId, pageable);
            
            // 构建成功响应
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            log.debug("成功返回用户访问站点列表，记录数量: {}", visitedSites.size());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("获取用户访问站点列表时发生异常，用户ID: {}", userId, e);
            
            // 处理服务器连接中断等边界情况
            return handleServiceException(e);
        }
    }
    
    /**
     * 获取用户访问过的站点列表（按时间范围筛选）
 * 支持查询指定时间段内的访问记录，符合用户故事"快速查看历史记录"的按时间排序需求
     * 
     * @param userId 用户唯一标识符
     * @param startDate 开始时间（包含），格式为yyyy-MM-dd HH:mm:ss")
     * 
     * @return 指定时间范围内的访问站点列表响应
     */
    @GetMapping("/by-date-range")
    @Operation(summary = "按时间范围获取访问站点列表", description = "根据用户ID和指定的时间范围获取其访问过的站点列表
     */
    @GetMapping("/by-date-range")
    public ResponseEntity<?> getVisitedSitesByDateRange(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "开始时间（格式：yyyy-MM-dd HH:mm:ss）")
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @Parameter(description = "结束时间（格式：yyyy-MM-dd HH:mm:ss）")
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("收到按时间范围筛选请求，用户ID: {}, 开始时间: {}, 结束时间: {}", userId, startDate, endDate),
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("收到按时间范围筛选请求，用户ID: {}, 开始时间: {}, 结束时间: {}", userId, startDate, endDate);
        
        try {
            // 调用Service层按时间范围查询
            List<VisitedSiteResponse> visitedSites = visitorService.getVisitedSitesByDateRange(userId, startDate, endDate, pageable);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("按时间范围查询用户访问记录时发生异常", e);
            return handleServiceException(e);
        }
    }
    
    /**
     * 获取用户访问过的站点列表（按评分筛选）
     * 支持筛选指定评分范围的站点，符合P1需求中的搜索和筛选功能
     * 
     * @return 指定评分范围内的访问站点列表响应
     */
    @GetMapping("/by-rating")
    @Operation(summary = "按评分获取访问站点列表", description = "根据用户ID和指定的评分范围获取其访问过的站点列表
     */
    @GetMapping("/by-rating")
    public ResponseEntity<?> getVisitedSitesByRating(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "最低评分（1-5）", example = "1")
            @RequestParam(required = false) Integer minRating,
            @Parameter(description = "最高评分（1-5）")
            @RequestParam(required = false) Integer maxRating,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("收到按评分范围筛选请求，用户ID: {}, 最低评分: {}, 最高评分: {}", userId, minRating, maxRating),
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("收到按评分范围筛选请求，用户ID: {}, 最低评分: {}, 最高评分: {}", userId, minRating, maxRating);
        
        try {
            // 调用Service层按评分范围查询
            List<VisitedSiteResponse> visitedSites = visitorService.getVisitedSitesByRating(userId, minRating, maxRating, pageable);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("按评分范围查询用户访问记录时发生异常", e);
            return handleServiceException(e);
        }
    }
    
    /**
     * 获取用户推荐过的站点列表
     * 符合用户故事"分享旅行经历"的需求，用户可以查看自己推荐过的站点
     * 
     * @return 用户推荐过的站点列表响应
     */
    @GetMapping("/recommended")
    @Operation(summary = "获取用户推荐过的站点列表", description = "根据用户ID获取其推荐过的站点列表
     */
    @GetMapping("/recommended")
    public ResponseEntity<?> getRecommendedSites(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long userId,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("收到获取推荐站点列表请求，用户ID: {}", userId);
        
        try {
            List<VisitedSiteResponse> visitedSites = visitorService.getRecommendedSites(userId, pageable);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("查询用户推荐站点时发生异常", e);
            return handleServiceException(e);
        }
    }
    
    /**
     * 获取用户最近访问的站点列表
     * 用于ViewVisitedSites用例的首页显示，展示用户最近的访问记录
     * 
     * @return 用户最近访问的站点列表响应
     */
    @GetMapping("/recent")
    @Operation(summary = "获取用户最近访问的站点列表", description = "根据用户ID获取其最近访问的站点列表
     */
    @GetMapping("/recent")
    public ResponseEntity<?> getRecentlyVisitedSites(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "返回结果数量限制（可选）", example = "10")
            @RequestParam(required = false) Integer limit) {
        
        log.info("收到获取最近访问站点列表请求，用户ID: {}, 限制数量: {}", userId, limit);
        
        try {
            List<VisitedSiteResponse> visitedSites = visitorService.getRecentlyVisitedSites(userId, limit);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("查询用户最近访问站点时发生异常", e);
            return handleServiceException(e);
        }
    }
    
    /**
     * 获取用户访问过的站点统计数据
     * 用于ViewVisitedSites用例的数据分析，显示用户的旅行偏好
     * 
     * @return 用户访问数据的统计信息响应
     */
    @GetMapping("/stats")
    public ResponseEntity<?> getVisitedSiteStats(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long userId) {
        
        log.info("收到获取用户访问统计数据请求，用户ID: {}", userId);
        
        try {
            VisitedSiteStats stats = visitorService.getVisitedSiteStats(userId);
            
            ApiResponse<VisitedSiteStats> response = ApiResponse.success(stats);
            return ResponseEntity.ok(response);
        }
    }
    
    /**
     * 搜索用户访问过的站点
     * 支持按站点名称、位置等进行模糊搜索
     * 
     * @return 匹配搜索关键词的访问站点列表响应
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchVisitedSites(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "搜索关键词（站点名称、位置等）", required = true, example = "埃菲尔铁塔")
            @RequestParam String keyword,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("收到搜索用户访问站点请求，用户ID: {}, 关键词: {}", userId, keyword);
        
        try {
            List<VisitedSiteResponse> visitedSites = visitorService.searchVisitedSites(userId, keyword, pageable);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("搜索用户访问站点时发生异常", e);
            return handleServiceException(e);
        }
    }
    
    /**
     * 导出用户访问过的站点数据
     * 支持导出为CSV格式，符合用户故事"管理个人足迹"中支持导出个人数据的需求
     * 
     * @return 导出数据的响应实体
     */
    @GetMapping("/export")
    public ResponseEntity<?> exportVisitedSites(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "导出格式（CSV/JSON等）", example = "CSV")
            @RequestParam(defaultValue = "CSV") String format) {
        
        log.info("收到导出用户访问数据请求，用户ID: {}, 格式: {}", userId, format);
        
        try {
            String exportData = visitorService.exportVisitedSites(userId, format);
            
            // 返回文件下载响应
            return ResponseEntity.ok()
                    .header("Content-Type", "text/csv; charset=UTF-8")
                    .header("Content-Disposition", "attachment; filename=visited_sites." + format.toLowerCase())
                    .body(exportData);
            
        } catch (Exception e) {
            log.error("导出用户访问数据时发生异常", e);
            return handleServiceException(e);
        }
    }
    
    /**
     * 处理服务异常的统一方法
     * 根据不同的异常类型返回相应的错误响应
     * 特别注意处理服务器连接中断的边界情况
     * 
     * @param e 发生的异常
     * @return 错误响应实体
     */
    private ResponseEntity<ApiResponse<?>> handleServiceException(Exception e) {
        
        log.error("处理服务异常", e);
        
        if (e instanceof AuthenticationException) {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "UNAUTHORIZED", "用户未认证或认证已过期", "请重新登录");
            
        } else if (e instanceof UserNotFoundException) {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "USER_NOT_FOUND", "指定的用户不存在或已被删除", "请检查用户ID是否正确");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            
        } else if (e instanceof DataAccessException) {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "DATA_ACCESS_ERROR", "数据访问失败，请稍后重试");
        } else if (e instanceof ServiceUnavailableException) {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "SERVICE_UNAVAILABLE", "服务器连接中断，请检查网络连接");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
            
        } else {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "INTERNAL_SERVER_ERROR", "服务器内部错误，请联系管理员");
            
        } else {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "UNKNOWN_ERROR", "系统发生未知错误，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}