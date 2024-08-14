package org.hae.tasklogue.repository;

import org.hae.tasklogue.entity.TaskVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskVersionRepository extends JpaRepository<TaskVersion, UUID> {
}
