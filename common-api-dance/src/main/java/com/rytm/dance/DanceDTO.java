package com.rytm.dance;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DanceDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("difficulty")
    private String difficulty;
    @JsonProperty("tutorial")
    private String tutorial;
    @JsonProperty("songs")
    private SongDTO[] songs;

}