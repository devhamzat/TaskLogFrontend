package org.hae.tasklogue.repository;

import org.hae.tasklogue.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
