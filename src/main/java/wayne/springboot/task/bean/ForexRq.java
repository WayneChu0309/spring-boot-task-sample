package wayne.springboot.task.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ForexRq {

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @JsonProperty("startDate")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @JsonProperty("endDate")
    private LocalDate endDate;

    @JsonProperty("currency")
    private String currency;
}
