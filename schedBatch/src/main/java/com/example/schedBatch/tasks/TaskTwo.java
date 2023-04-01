package com.example.schedBatch.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class TaskTwo implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("=========== This a simple task two (init) ===========");

        Thread.sleep(1500L);


        System.out.println("=========== This a simple task two (end) ===========");
        return RepeatStatus.FINISHED;
    }
}
