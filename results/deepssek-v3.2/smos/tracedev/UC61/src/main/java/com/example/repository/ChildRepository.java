package com.example.repository;

import java.util.List;
import com.example.model.Child;

/**
 * Repository interface for child data access.
 */
public interface ChildRepository {
    Child findChildByParentAndId(int parentId, int childId);
    List<Child> findChildrenByParent(int parentId);
}