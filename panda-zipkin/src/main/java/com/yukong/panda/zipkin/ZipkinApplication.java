package com.yukong.panda.zipkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author yukong
 * @date 2019-04-25 15:52
 */
@SpringBootApplication
@EnableZipkinServer
@EnableDiscoveryClient
public class ZipkinApplication implements CommandLineRunner {
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("env: "+env.getProperty("feign.hystrix.enabled"));
    }
}
