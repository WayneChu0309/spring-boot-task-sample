package wayne.springboot.task.bean;

import lombok.Data;

import java.util.List;

@Data
public class ForexRs {

    private ForexRsError error;

    private List<Currency> currencies;


}
