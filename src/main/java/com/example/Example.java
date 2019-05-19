package com.example;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Example {

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }

    @RequestMapping("/")
    String home() {
        return "Hello";
    }

    @RequestMapping("/myEsInfo")
    String myElasticsearch() throws IOException {
    	RestHighLevelClient client = new RestHighLevelClient(
    			RestClient.builder(new HttpHost("my-elasticsearch", 9200, "http")));
    	
    	MainResponse mr = client.info(RequestOptions.DEFAULT);
    	
    	StringBuilder info = new StringBuilder();
    	info.append("Elasticsearchの情報");
    	info.append("<br>");
    	info.append("nodeName: " + mr.getNodeName());
    	info.append("<br>");
    	info.append("clusterName: " + mr.getClusterName());

    	client.close();
    	
       return info.toString();
    }

}
