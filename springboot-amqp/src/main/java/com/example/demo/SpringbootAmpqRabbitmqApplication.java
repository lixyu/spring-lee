package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SpringBootApplication
public class SpringbootAmpqRabbitmqApplication {

	private final Publisher publisher;
	private final DelayedSender delayedSender;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAmpqRabbitmqApplication.class, args);
	}


	@GetMapping("/send/{message}")
	public String send(@PathVariable("message") String message){
		publisher.publish(message);
		return "SUCCESS";
	}

	@GetMapping("/send2/{message}")
	public String send2(@PathVariable("message") String message){
		delayedSender.send(message);
		return "SUCCESS";
	}

	@GetMapping("/test/{count}")
	public String test(@PathVariable Integer count){
		for (int i=0;i<count;i++){
			publisher.sendTest(UUID.randomUUID().toString());
		}

		return "SUCCESS";
	}

}
