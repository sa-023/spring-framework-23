package com.company.service.impl;
import com.company.dto.TaskDTO;
import com.company.entity.Task;
import com.company.mapper.MapperUtil;
import com.company.mapper.ProjectMapper;
import com.company.mapper.TaskMapper;
import com.company.repository.TaskRepository;
import com.company.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @MockBean
    private ProjectMapper projectMapper;
    @MockBean
    private UserRepository userRepository;
    @Mock
    TaskRepository taskRepository;
    @Mock
    TaskMapper taskMapper;
    @Mock
    MapperUtil mapperUtil;
    @InjectMocks
    TaskServiceImpl taskServiceImpl;


    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, -5L})
    void findById_test(long id) {
        // Given
        Task task = new Task();
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskMapper.convertToDto(task)).thenReturn(new TaskDTO());
        // When
        taskServiceImpl.findById(id);
        // Then
        verify(taskRepository).findById(id);
        verify(taskRepository).findById(anyLong());
        verify(taskMapper).convertToDto(any(Task.class));
        verify(taskMapper).convertToDto(task);
//        verify(taskRepository, never()).findById(-5L); // The never() method will prevent the -5L ID from passing.
    }

    @Test
    void findById_bdd_test() {
        // Given
        Task task = new Task();
        given(taskRepository.findById(anyLong())).willReturn(Optional.of(task)); // The given() method comes from the BDDMockito class.
        given(taskMapper.convertToDto(task)).willReturn(new TaskDTO());
        // When
        taskServiceImpl.findById(anyLong());
        // Then
        then(taskRepository).should().findById(anyLong());
        then(taskRepository).should(never()).findById(-5L);
    }















}