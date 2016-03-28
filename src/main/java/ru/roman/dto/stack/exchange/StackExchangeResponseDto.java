package ru.roman.dto.stack.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StackExchangeResponseDto {

    @JsonProperty("quota_max")
    public Long quotaMax;

    @JsonProperty("items")
    public List<ItemDto> items;

    @JsonProperty("has_more")
    public Boolean hasMore;

    @JsonProperty("quota_remaining")
    public Long quotaRemaining;

}