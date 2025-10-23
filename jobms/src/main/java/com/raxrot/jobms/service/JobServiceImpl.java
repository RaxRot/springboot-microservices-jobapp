package com.raxrot.jobms.service;


import com.raxrot.jobms.exception.ApiException;
import com.raxrot.jobms.model.Job;
import com.raxrot.jobms.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ApiException("Job not found", HttpStatus.NOT_FOUND));
        return job;
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Job jobToUpdate = jobRepository.findById(id)
                .orElseThrow(() -> new ApiException("Job not found", HttpStatus.NOT_FOUND));
        jobToUpdate.setTitle(job.getTitle());
        jobToUpdate.setDescription(job.getDescription());
        jobToUpdate.setMinSalary(job.getMinSalary());
        jobToUpdate.setMaxSalary(job.getMaxSalary());
        jobToUpdate.setLocation(job.getLocation());
        Job updatedJob = jobRepository.save(jobToUpdate);
        return updatedJob;
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ApiException("Job not found", HttpStatus.NOT_FOUND));
        jobRepository.delete(job);
    }
}
