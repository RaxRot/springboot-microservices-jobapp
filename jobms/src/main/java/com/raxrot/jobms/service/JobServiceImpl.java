package com.raxrot.jobms.service;


import com.raxrot.jobms.client.CompanyClient;
import com.raxrot.jobms.client.ReviewClient;
import com.raxrot.jobms.exception.ApiException;
import com.raxrot.jobms.external.Company;
import com.raxrot.jobms.external.JobFullDTO;
import com.raxrot.jobms.external.Review;
import com.raxrot.jobms.model.Job;
import com.raxrot.jobms.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final RestTemplate restTemplate;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<JobFullDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobFullDTO> jobFullDTOS = new ArrayList<>();


        for (Job job : jobs) {
            JobFullDTO jobFullDTO = new JobFullDTO();
            jobFullDTO.setJob(job);
            Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/"+job.getCompanyId(), Company.class);
            jobFullDTO.setCompany(company);

            jobFullDTOS.add(jobFullDTO);
        }
        return jobFullDTOS;
    }

    @Override
    public JobFullDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ApiException("Job not found", HttpStatus.NOT_FOUND));

        JobFullDTO jobFullDTO = new JobFullDTO();
        jobFullDTO.setJob(job);

        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviewList = reviewClient.getReviews(job.getCompanyId());

        jobFullDTO.setReview(reviewList);
        jobFullDTO.setCompany(company);
        return jobFullDTO;
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
