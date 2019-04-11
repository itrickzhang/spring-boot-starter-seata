package org.springframework.boot.seata.samples.businessservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author zcb
 * @date 2019年4月9日 上午10:29:19
 */
@SpringBootApplication
@EnableFeignClients
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @FeignClient(value = "storage", url = "http://127.0.0.1:18082")
    public interface StorageService {

        @RequestMapping(path = "/storage/{commodityCode}/{count}")
        String storage(@RequestParam("commodityCode") String commodityCode,
                       @RequestParam("count") int count);

    }

    @FeignClient(value = "order", url = "http://127.0.0.1:18083")
    public interface OrderService {

        @RequestMapping(path = "/order", method = RequestMethod.POST)
        String order(@RequestParam("userId") String userId,
                     @RequestParam("commodityCode") String commodityCode,
                     @RequestParam("orderCount") int orderCount);

    }
}
