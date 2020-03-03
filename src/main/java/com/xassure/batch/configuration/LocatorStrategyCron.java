package com.xassure.batch.configuration;

import com.xassure.batch.model.LocatorStrategy;
import com.xassure.batch.repository.LocatorRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@Configuration
@Log4j2
public class LocatorStrategyCron {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private LocatorRepository locatorRepository;

    @Scheduled(cron = "0,30 * * * * *")
    public void locatorCron(){

        log.info(" ==== inside cron job ==== ");

        GroupOperation groupOperation = group("pageName", "elementName", "locatorTimeList.locator")
                .min("locatorTimeList.time").as("minTime")
                .max("locatorTimeList.time").as("maxTime")
                .avg("locatorTimeList.time").as("avgTime");
        Aggregation aggregation = newAggregation(
                unwind("locatorTimeList"),
                groupOperation
        );
        AggregationResults<?> aggregationResults = mongoTemplate.aggregate(aggregation, "page-locatorcsv", LocatorStrategy.class);
        List<LocatorStrategy> locatorStrategyList = (List<LocatorStrategy>) aggregationResults.getMappedResults();
        locatorRepository.deleteAll();
        locatorRepository.saveAll(locatorStrategyList);

        log.info(" ==== exit cron job ==== " );
    }

}
