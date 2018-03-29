package cn.edu.zju.zipkinservicehi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class ZipkinServicehiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServicehiApplication.class, args);
	}

	private static final Logger LOGGER = Logger.getLogger(ZipkinServicehiApplication.class.getName());

	@Autowired
    private RestTemplate restTemplate;

	@Bean
    public RestTemplate getRestTemplate() {
	    return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String callHome() {
	    LOGGER.log(Level.INFO, "calling trace service-hi ");
	    return restTemplate.getForObject("http://localhost:8989/miya ", String.class);
    }

    @RequestMapping("/info2")
    public String info() {
	    LOGGER.log(Level.INFO, "calling trace service-hi ");
	    return "I'm service-hi. ";
    }

    @Bean
    public AlwaysSampler defaultSampler() {
	    return new AlwaysSampler();
    }
}
