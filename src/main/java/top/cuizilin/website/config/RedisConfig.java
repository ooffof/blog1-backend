package top.cuizilin.website.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class RedisConfig {
   @Autowired
   private URLConfig urlConfig;

    @Bean
    public LettuceConnectionFactory connectionFactory(){
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(urlConfig.getRedisUrl(), 6379);
        redisConfig.setDatabase(1);
        return new LettuceConnectionFactory(redisConfig);
    }
}
