package com.xassure.batch;

import com.xassure.batch.model.LocatorStrategy;
import com.xassure.batch.repository.LocatorRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@SpringBootApplication
@EnableMongoAuditing
@EnableScheduling
@Log4j2
public class LocatorSchedulerApplication {


	public static void main(String[] args) {
		SpringApplication.run(LocatorSchedulerApplication.class, args);
	}

}
