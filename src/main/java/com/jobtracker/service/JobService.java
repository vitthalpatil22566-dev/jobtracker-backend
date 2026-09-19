package com.jobtracker.service;

import com.jobtracker.entity.Job;
import com.jobtracker.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Get job by ID
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    // Create job
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    // Update job
    public Job updateJob(Long id, Job updatedJob) {

        Job existingJob = getJobById(id);

        existingJob.setCompany(updatedJob.getCompany());
        existingJob.setPosition(updatedJob.getPosition());
        existingJob.setStatus(updatedJob.getStatus());
        existingJob.setAppliedDate(updatedJob.getAppliedDate());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setJobType(updatedJob.getJobType());
        existingJob.setNotes(updatedJob.getNotes());

        return jobRepository.save(existingJob);
    }

    // Delete job
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    // Search jobs
    public List<Job> searchJobs(String keyword) {
        return jobRepository
                .findByCompanyContainingIgnoreCaseOrPositionContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }

    // Filter by status
    public List<Job> getJobsByStatus(String status) {
        return jobRepository.findByStatus(status);
    }
}
