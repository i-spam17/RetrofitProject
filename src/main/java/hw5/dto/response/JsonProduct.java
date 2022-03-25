package hw5.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@With
@Getter
public class JsonProduct {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("price")
    public Integer price;
    @JsonProperty("categoryTitle")
    public String categoryTitle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonProduct that = (JsonProduct) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(categoryTitle, that.categoryTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, categoryTitle);
    }
        @Override
    public String toString() {
        return "JsonProduct{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", categoryTitle='" + categoryTitle + '\'' +
                '}';
    }
}
