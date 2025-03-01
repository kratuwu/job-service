package com.ata.job_service;

import com.ata.job_service.entity.SalarySurvey;
import com.ata.job_service.repository.SalarySurveyRepository;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.replaceAll;

@Configuration
@RequiredArgsConstructor
public class SeedSalarySurveyData implements CommandLineRunner {

    private final SalarySurveyRepository salarySurveyRepository;

    @Override
    public void run(String... args) {
        try {
            List<SalarySurvey> salarySurveys = new ArrayList<>();
            CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/salary_survey-3.csv"));
            csvReader.readNext();
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                try {
                    salarySurveys.add(SalarySurvey.builder()
                            .timestamp(row[0])
                            .employer(row[1])
                            .location(row[2])
                            .jobTitle(row[3])
                            .yearsAtEmployer(row[4])
                            .yearsOfExperience(row[5])
                            .salary(new BigDecimal(row[6].replaceAll("[€$£¥,|USD|EUR|NZD|GBP|CAD|AUD]", "").trim()))
                            .signingBonus(row[7])
                            .annualBonus(row[8])
                            .annualStockValueBonus(row[9])
                            .gender(row[10])
                            .additionalComments(row[10])
                            .build());
                } catch (Exception ignored) {
                }
            }
            salarySurveyRepository.saveAll(salarySurveys);
        } catch (Exception ignored) {
        }
    }
}
