package com.raxrot.jobms.repository;

import com.raxrot.jobms.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
