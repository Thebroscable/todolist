package com.example.demo.repository;

import com.example.demo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM task " +
                    "WHERE due_date = current_date " +
                    "AND is_completed = false " +
                    "ORDER BY due_date, due_time;")
    Collection<Task> findAllTodayTasks();

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM task " +
                    "WHERE is_completed = false " +
                    "AND ( " +
                    "   (due_date > current_date AND due_date <= current_date + interval '7 days') " +
                    "   OR (due_date = current_date AND due_time > current_time) " +
                    ") " +
                    "ORDER BY due_date, due_time;")
    Collection<Task> findAllUpcomingTasks();

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM task " +
                    "WHERE is_completed = true " +
                    "ORDER BY due_date DESC, due_time DESC;")
    Collection<Task> findAllCompletedTasks();

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM task " +
                    "WHERE is_completed = false " +
                    "ORDER BY due_date, due_time;")
    Collection<Task> findAllTasks();
}
