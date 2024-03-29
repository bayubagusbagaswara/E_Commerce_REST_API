package com.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonPropertyOrder({
        "code",
        "message"
})
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
