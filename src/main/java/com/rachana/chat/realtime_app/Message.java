package com.rachana.chat.realtime_app;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Message {

    @JsonProperty
    private String sender;

    @JsonProperty
    private String content;

    @JsonProperty
    private String timestamp;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDate.now().toString();
    }
}
