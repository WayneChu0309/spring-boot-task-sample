package wayne.springboot.task.api;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallApiService {
    private final String apiUrl = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";

    private final RestTemplate restTemplate;

    public CallApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDataFromApi() {
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
