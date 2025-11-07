package com.rytm.dance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("artist")
    private String artist;

}
