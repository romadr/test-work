package ru.roman.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StackExchangeResponseDto {

    @JsonProperty("quota_max")
    public String quotaMax;

    @JsonProperty("items")
    public List<ItemDto> items;

    @JsonProperty("has_more")
    public String hasMore;

    @JsonProperty("quota_remaining")
    public String quotaRemaining;

}