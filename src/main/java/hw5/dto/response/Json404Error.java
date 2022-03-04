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
public class Json404Error {

    @JsonProperty("status")
    public Integer status;
    @JsonProperty("message")
    public String message;
    @JsonProperty("timestamp")
    public String timestamp;

}
