package ru.roman.dto.stack.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnerDto {

    @JsonProperty("display_name")
    public String displayName;

    @JsonProperty("user_type")
    public String userType;

    @JsonProperty("profile_image")
    public String profileImage;

    @JsonProperty("link")
    public String link;

    @JsonProperty("reputation")
    public String reputation;

    @JsonProperty("user_id")
    public String userId;
}
