package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

	@GetMapping("/")
	public List<Price> index() {
		return Arrays.asList(
				new Price(),
				new Price(),
				new Price()
		);
	}

}
