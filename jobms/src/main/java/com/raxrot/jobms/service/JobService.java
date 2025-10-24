package com.raxrot.jobms.service;


import com.raxrot.jobms.external.JobFullDTO;
import com.raxrot.jobms.model.Job;

import java.util.List;

public interface JobService {
    Job createJob(Job job);
    List<JobFullDTO> findAll();
    JobFullDTO getJobById(Long id);
    Job updateJob(Long id,Job job);
    void deleteJob(Long id);
}
