package com.tictaktoe.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

	@LocalServerPort
	private int port = 8080;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testPlayTurnAPI() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn",
				String.class)).contains("Welcome to tik tac toe, X will take first turn");
	}

	@Test
	public void testStartAPI() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/start",
				String.class)).contains("Welcome to tik tac toe, X will take first turn\n" +
				"|---|---|---|\n" +
				"| 1 | 2 | 3 |\n" +
				"|-----------|\n" +
				"| 4 | 5 | 6 |\n" +
				"|-----------|\n" +
				"| 7 | 8 | 9 |\n" +
				"|---|---|---|");
	}

}
