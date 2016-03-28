package ru.roman.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDto {
    public String questionId;
    public Timestamp date;
    public String title;
    public String ownerName;
    public String url;
    public Boolean isAnswered;
}
