package wayne.springboot.task.Scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wayne.springboot.task.api.CallApiService;
import wayne.springboot.task.bean.ForeignExchangeRate;
import wayne.springboot.task.service.ForeignExchangeService;

import java.util.List;


@Component
public class DailyTaskScheduler {

    @Autowired
    CallApiService callApiService;

    @Autowired
    ForeignExchangeService foreignExchangeService;

    @Scheduled(cron = "0 0 18 * * *")
    public Boolean executeTask() {
        String result = callApiService.getDataFromApi();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ForeignExchangeRate[] foreignExchangeRate = objectMapper.readValue(result, ForeignExchangeRate[].class);
            foreignExchangeService.saveForeignExchangeRate(List.of(foreignExchangeRate));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
