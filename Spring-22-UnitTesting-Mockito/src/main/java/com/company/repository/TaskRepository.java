package com.company.repository;

import com.company.entity.Project;
import com.company.entity.Task;
import com.company.entity.User;
import com.company.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> { // <entityName,primaryKey>
    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.taskStatus <> 'COMPLETE' ") // NOT operator <>
    int totalNonCompletedTasks(String projectCode);
    @Query(value = "SELECT COUNT(*)" + "FROM tasks t JOIN projects p on t.project_id = p.id " + "WHERE p.project_code = ?1 AND t.task_status = 'COMPLETE'",nativeQuery = true)
    int totalCompletedTasks(String projectCode);
    List<Task> findAllByProject(Project project);
    List<Task> findAllByTaskStatusIsNotAndAssignedEmployee(Status status, User assignedEmployee);
    List<Task> findAllByTaskStatusAndAssignedEmployee(Status status, User assignedEmployee);
    List<Task> findAllByAssignedEmployee(User assignedEmployee);




}
