package wayne.springboot.task.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "collection")
public class ForeignExchangeRate implements Serializable {

    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyyMMdd")
    @Id
    private java.util.Date date;
    @JsonProperty("USD/NTD")
    private String usdToNtd;


}
