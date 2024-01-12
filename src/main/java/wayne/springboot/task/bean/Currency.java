package wayne.springboot.task.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonRootName("currency")
public class Currency {

    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private LocalDate date;
    private String usd;
}
