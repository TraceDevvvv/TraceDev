package com.tourist.feedback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ViewVisitedSites用例的响应数据传输对象
 * 包含用户访问过的站点信息：站点ID、名称、访问时间、评分和评论等核心信息
 * 这是ViewVisitedSites用例的核心响应数据结构，用于前端展示和API交互
 * 
 * 对应API文档中的响应格式：
 * {
 *   "siteId": 1,
 *   "siteName": "埃菲尔铁塔",
 *   "visitedDate": "2024-01-15",
 *   "feedbackRating": 5,
 *   "feedbackComment": "非常棒的体验！"
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "访问过的站点信息响应对象")
public class VisitedSiteResponse {
    
    /**
     * 站点唯一标识符
     * 用于站点详情页面的跳转和识别
     */
    @Schema(description = "站点ID", example = "1")
    private Long siteId;
    
    /**
     * 站点名称
     * 用户友好显示，便于用户识别访问过的景点
     */
    @Schema(description = "站点名称", example = "埃菲尔铁塔")
    private String siteName;
    
    /**
     * 站点地理位置
     * 显示站点的详细地址信息，帮助用户回忆具体位置
     */
    @Schema(description = "站点地理位置", example = "法国巴黎战神广场")
    private String siteLocation;
    
    /**
     * 站点类型
     * 用于分类和筛选，如历史遗迹、自然景观、博物馆等
     */
    @Schema(description = "站点类型", example = "历史遗迹")
    private String siteCategory;
    
    /**
     * 实际访问日期
     * 用户访问该站点的具体日期，前端会格式化为"yyyy-MM-dd"格式
     * 使用ISO日期时间格式确保JSON序列化的标准性
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(description = "访问日期", example = "2024-01-15")
    private LocalDateTime visitedDate;
    
    /**
     * 用户评分（1-5分）
     * 范围从1分（非常不满意）到5分（非常满意）
     * 用于显示用户对站点的满意度
     */
    @Schema(description = "用户评分（1-5分）", example = "5")
    private Integer feedbackRating;
    
    /**
     * 用户评论内容
     * 用户对该站点的详细评价和体验分享
     * 可以为空，因为不是所有用户都写评论
     */
    @Schema(description = "用户评论", example = "非常棒的体验！夜景特别美丽！")
    private String feedbackComment;
    
    /**
     * 反馈创建时间
     * 用户提交反馈的时间，通常晚于实际访问时间
     * 用于排序和显示用户活动的时效性
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "反馈创建时间", example = "2024-01-16 14:30:00")
    private LocalDateTime feedbackCreatedAt;
    
    /**
     * 是否推荐给其他游客
     * true:推荐，false:不推荐
     * 用于显示用户的推荐倾向，帮助其他用户决策
     */
    @Schema(description = "是否推荐", example = "true")
    private Boolean isRecommended;
    
    /**
     * 站点图片URL
     * 站点的展示图片链接，用于前端展示
     * 可以为空，如果站点没有配置图片
     */
    @Schema(description = "站点图片URL", example = "https://example.com/images/eiffel-tower.jpg")
    private String siteImageUrl;
    
    /**
     * 获取评分星级的字符串表示
     * 将1-5的数字评分转换为星级表示（如★★★★★）
     * 用于前端友好显示
     * 
     * @return 星级字符串，例如"★★★★★"表示5星
     */
    public String getRatingStars() {
        if (feedbackRating == null || feedbackRating < 1) {
            return "";
        }
        
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < feedbackRating; i++) {
            stars.append("★");
        }
        // 添加空星
        for (int i = feedbackRating; i < 5; i++) {
            stars.append("☆");
        }
        return stars.toString();
    }
    
    /**
     * 获取简化的日期字符串（仅年月日）
     * 用于需要简洁日期显示的场合
     * 
     * @return 格式为"yyyy-MM-dd"的日期字符串
     */
    public String getShortVisitedDate() {
        if (visitedDate == null) {
            return null;
        }
        return visitedDate.toLocalDate().toString();
    }
    
    /**
     * 检查评论是否为空或空白
     * 用于前端决定是否显示评论内容
     * 
     * @return true表示有评论内容，false表示评论为空
     */
    public boolean hasComment() {
        return feedbackComment != null && !feedbackComment.trim().isEmpty();
    }
    
    /**
     * 获取评论预览（截取前50个字符）
     * 用于列表视图的简洁展示
     * 如果评论超过50个字符，添加"…"表示省略
     * 
     * @return 评论预览字符串
     */
    public String getCommentPreview() {
        if (!hasComment()) {
            return "";
        }
        
        if (feedbackComment.length() <= 50) {
            return feedbackComment;
        }
        
        return feedbackComment.substring(0, 50) + "…";
    }
    
    /**
     * 验证响应数据的完整性
     * 确保必需字段不为空，用于数据质量检查
     * 
     * @return true表示数据完整有效，false表示数据不完整
     */
    public boolean isValid() {
        return siteId != null && 
               siteName != null && 
               !siteName.trim().isEmpty() && 
               visitedDate != null && 
               feedbackRating != null &&
               feedbackRating >= 1 && 
               feedbackRating <= 5;
    }
    
    /**
     * 构建方法：从站点和反馈信息创建响应对象
     * 这是一个便捷的静态工厂方法，简化对象创建过程
     * 
     * @param siteId 站点ID
     * @param siteName 站点名称
     * @param siteLocation 站点位置
     * @param siteCategory 站点类型
     * @param visitedDate 访问日期
     * @param feedbackRating 评分
     * @param feedbackComment 评论
     * @param feedbackCreatedAt 反馈创建时间
     * @param isRecommended 是否推荐
     * @param siteImageUrl 站点图片URL
     * @return 构建好的VisitedSiteResponse对象
     */
    public static VisitedSiteResponse build(
            Long siteId, 
            String siteName, 
            String siteLocation,
            String siteCategory,
            LocalDateTime visitedDate, 
            Integer feedbackRating, 
            String feedbackComment,
            LocalDateTime feedbackCreatedAt,
            Boolean isRecommended,
            String siteImageUrl) {
        
        VisitedSiteResponse response = new VisitedSiteResponse();
        response.siteId = siteId;
        response.siteName = siteName;
        response.siteLocation = siteLocation;
        response.siteCategory = siteCategory;
        response.visitedDate = visitedDate;
        response.feedbackRating = feedbackRating;
        response.feedbackComment = feedbackComment;
        response.feedbackCreatedAt = feedbackCreatedAt;
        response.isRecommended = isRecommended;
        response.siteImageUrl = siteImageUrl;
        
        return response;
    }
    
    /**
     * 获取站点的完整信息字符串
     * 用于日志记录和调试
     * 
     * @return 包含所有字段的格式化字符串
     */
    @Override
    public String toString() {
        return String.format(
            "VisitedSiteResponse{siteId=%d, siteName='%s', visitedDate=%s, rating=%d}",
            siteId, siteName, 
            visitedDate != null ? visitedDate.toLocalDate() : null, 
            feedbackRating
        );
    }
}