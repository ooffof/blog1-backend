package top.cuizilin.website.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class URLConfig {
    @Value("${front-end-url}")
    private String frontEndUrl;

    @Value("${redis-host}")
    private String redisUrl;
}
