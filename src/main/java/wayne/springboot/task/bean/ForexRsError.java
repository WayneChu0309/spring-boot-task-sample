package wayne.springboot.task.bean;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("error")
public class ForexRsError {
        private String code;
        private String message;
}
