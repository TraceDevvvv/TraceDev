// File: DeleteCulturalObjectTest.java
package com.example.test;

import com.example.agency.presenter.DeleteCulturalObjectPresenter;
import com.example.agency.view.AgencyOperatorView;
import com.example.repository.CulturalObjectRepository;
import com.example.usecase.DeleteCulturalObjectUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteCulturalObjectTest {

    @Mock
    private CulturalObjectRepository repository;

    @Mock
    private AgencyOperatorView view;

    private DeleteCulturalObjectPresenter presenter;

    @BeforeEach
    void setUp() {
        // 组装 UseCase 和 Presenter
        DeleteCulturalObjectUseCase useCase = new DeleteCulturalObjectUseCase(repository);
        presenter = new DeleteCulturalObjectPresenter(view, useCase);
    }

    @Test
    @DisplayName("测试流程：用户确认删除且后台执行成功")
    void testDeleteFlow_Successful() {
        // 1. 准备数据
        String objectId = "CO001";

        // 2. 模拟行为 (Mock Behavior)
        // 当 View 弹出确认框时，模拟用户点击“是”(返回 true)
        when(view.showConfirmationDialog(anyString())).thenReturn(true);
        // 当 Repository 执行删除时，模拟数据库操作成功 (返回 true)
        when(repository.delete(objectId)).thenReturn(true);

        // 3. 执行操作 (Act)
        presenter.requestDelete(objectId);

        // 4. 验证顺序和结果 (Assert)
        InOrder inOrder = inOrder(view, repository);

        // 验证步骤 1: 弹出了确认框
        inOrder.verify(view).showConfirmationDialog(contains(objectId));
        
        // 验证步骤 2: 为了防止重复操作，锁定了 UI 输入
        inOrder.verify(view).blockInputControls();
        
        // 验证步骤 3: 确实调用了后台的删除方法 (Remote Service)
        inOrder.verify(repository).delete(objectId);
        
        // 验证步骤 4: 显示了“成功”信息，而不是错误信息
        inOrder.verify(view).showSuccessMessage(contains("successfully"));
        
        // 验证步骤 5: 解锁了 UI 输入
        inOrder.verify(view).unblockInputControls();
    }
}