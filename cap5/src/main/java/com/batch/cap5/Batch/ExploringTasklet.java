package com.batch.cap5.Batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import java.util.List;

public class ExploringTasklet implements Tasklet {

    Logger LOG = LoggerFactory.getLogger(ExploringTasklet.class);

    private JobExplorer explorer;

    public ExploringTasklet(JobExplorer explorer){
        this.explorer = explorer;
    }

    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext){
        String jobName = chunkContext.getStepContext().getJobName();

        List<JobInstance> instances = explorer.getJobInstances(jobName,0,Integer.MAX_VALUE);
        System.out.println(
                String.format("Existen %d job instances, para el job %s",
                        instances.size(),
                        jobName)
        );

        LOG.info("Tenemos los siguientes resultados:");
        LOG.info("======================================================");

        for( JobInstance instance : instances ){
            List<JobExecution> jobExecutions = this.explorer.getJobExecutions(instance);
            LOG.info(
                    String.format("La instancia %d tiene %s ejecuciones",
                            instance.getInstanceId(),
                            jobExecutions.size()));

            for( JobExecution jobExecution: jobExecutions){
                LOG.info(String.format("Execution %d resulted in Exit Status %s",
                        jobExecution.getId(),
                        jobExecution.getExitStatus())
                );
            }
        }
        return RepeatStatus.FINISHED;
    }

}
