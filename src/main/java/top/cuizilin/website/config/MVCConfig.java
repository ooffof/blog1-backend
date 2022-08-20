package top.cuizilin.website.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.URL;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Autowired
    private URLConfig urlConfig;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**")
               .allowCredentials(true)
               .allowedOrigins(urlConfig.getFrontEndUrl())
               .allowedHeaders("*")
               .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
               .maxAge(3600);
    }
}
