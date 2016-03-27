package ru.roman.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto {

    @JsonProperty("creation_date")
    public Long creationDate;

    @JsonProperty("tags")
    public List<String> tags;

    @JsonProperty("title")
    public String title;

    @JsonProperty("link")
    public String link;

    @JsonProperty("last_edit_date")
    public Long lastEditDate;

    @JsonProperty("score")
    public String score;

    @JsonProperty("answer_count")
    public String answerCount;

    @JsonProperty("owner")
    public OwnerDto owner;

    @JsonProperty("last_activity_date")
    public Long lastActivityDate;

    @JsonProperty("question_id")
    public String questionId;

    @JsonProperty("view_count")
    public String viewCount;

    @JsonProperty("is_answered")
    public String isAnswered;
}