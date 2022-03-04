package hw5.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@With
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonCategory {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("products")
    public List<JsonProduct> jsonProducts = new ArrayList<JsonProduct>();

}

