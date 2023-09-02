package spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import spring.batch.entity.Customer;
import spring.batch.repository.CustomerRepository;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig 
{
	@Autowired
	  private StepBuilderFactory stepBuilder;
	@Autowired
  private JobBuilderFactory jobBuilder;
	
  
	@Autowired
  private CustomerRepository customerRepo;
  
  @Bean
  public FlatFileItemReader<Customer> reader()
  {
	   FlatFileItemReader<Customer> itemReader=new FlatFileItemReader<>();
	   itemReader.setResource(new FileSystemResource("src/main/resources/Customer.csv"));
	   itemReader.setName("csvreader");
	   itemReader.setLinesToSkip(1);
	   itemReader.setLineMapper(linerMapper());
	   return itemReader;
  }

   private LineMapper<Customer> linerMapper() 
   {
	   DefaultLineMapper<Customer> lineMapper=new DefaultLineMapper<>();
	   DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
	  lineTokenizer.setDelimiter(",");
	  lineTokenizer.setStrict(true);
	  lineTokenizer.setNames("customerId","gender","age","annual_Income","spending_Score");
	  BeanWrapperFieldSetMapper<Customer> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
	  fieldSetMapper.setTargetType(Customer.class);
	  
	  lineMapper.setLineTokenizer(lineTokenizer);
	  lineMapper.setFieldSetMapper(fieldSetMapper);
	return lineMapper;
   }
   @Bean
  public CustomerProcessor processor()
  {
	  return new CustomerProcessor();
  }
   @Bean
   public RepositoryItemWriter<Customer> writer()
   {
	   RepositoryItemWriter<Customer> writer=new RepositoryItemWriter<>();
	   writer.setRepository(customerRepo);
	   writer.setMethodName("save");
	   return writer;
	 }
   
   @Bean
   public Step step1()
   {
	  return stepBuilder.get("csv-step").<Customer,Customer>chunk(10)
	   .reader(reader())
	   .processor(processor())
	   .writer(writer())
	   
	   .build();
   }
   @Bean
   public Job job()
   {
	   return jobBuilder.get("importCustomerInfo")
			   .flow(step1())
			   .end().build();
   }
   
   
  
}
