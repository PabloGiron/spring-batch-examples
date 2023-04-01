package io.spring.batch.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step() {
		return this.stepBuilderFactory.get("step1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
						System.out.println("Hello world!");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}

//	@Bean
//	public Job job(){
//		return this.jobBuilderFactory.get("job")
//				.start(step())
//				.build();
//	}

	@Bean
	public Step step1(){
		return this.stepBuilderFactory.get("step1")
				.tasklet(helloWorldTasklet())
				.build();
	}

	@Bean
	public Tasklet helloWorldTasklet(){
		return ((stepContribution, chunkContext) -> {
			String name = (String) chunkContext.getStepContext()
					.getJobParameters()
					.get("name");

			System.out.println(String.format("Hello, %s!", name));
			return RepeatStatus.FINISHED;
		});
	}


	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}
