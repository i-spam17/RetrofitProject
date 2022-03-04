package hw5.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@With
@Getter
public class Json400Error {

    public String timestamp;
    @JsonProperty("status")
    public Integer status;
    @JsonProperty("error")
    public String error;
    @JsonProperty("message")
    public String message;
    @JsonProperty("path")
    public String path;

}

