package ru.roman.dto;


import java.util.List;

public class SearchResponseDto {
    public List<QuestionDto> data;
    public Long quotaRemaining;
    public Long quotaMax;
    public Boolean hasMore;
}
