package spring.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.batch.entity.Customer;

@RestController
public class JobController 
{
	@Autowired
     private JobLauncher joblauncher;
	@Autowired
     private Job job;
	
	@PostMapping("/launch")
	public String importData() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException
	{
		JobParameters jobParameter=new JobParametersBuilder()
				                           .addLong("startAt", System.currentTimeMillis()).toJobParameters();
		joblauncher.run(job, jobParameter);
		
		return "Success";
	}
     
}
