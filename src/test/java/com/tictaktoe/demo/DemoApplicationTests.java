package com.tictaktoe.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.tictaktoe.demo.ResponseCode.DRAW;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

	@LocalServerPort
	private int port = 8080;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testPlayTurnAPI() throws Exception {


		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=6",
				String.class)).contains("{\"outputBoard\":\"|---|---|---|\\n| 1 | 2 | 3 |\\n|-----------|\\n| 4 | 5 | X |\\n|-----------|\\n| 7 | 8 | 9 |\\n|---|---|---|\",\"responseCode\":\"OK\",\"responseDescription\":\"Y Turn\",\"turnNumber\":\"1\"}");
	}


	@Test
	public void testCheckAuthenticityOfTurns() throws Exception {


	}

	@Test
	public void testPlayTurnAPITestTurns() throws Exception {



		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=1",
				TurnResponse.class).getTurnNumber()).isEqualTo("1");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=5",
				TurnResponse.class).getTurnNumber()).isEqualTo("2");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=6",
				TurnResponse.class).getTurnNumber()).isEqualTo("3");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=7",
				TurnResponse.class).getTurnNumber()).isEqualTo("4");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=4",
				TurnResponse.class).getTurnNumber()).isEqualTo("5");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=3",
				TurnResponse.class).getTurnNumber()).isEqualTo("6");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=2",
				TurnResponse.class).getTurnNumber()).isEqualTo("7");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=8",
				TurnResponse.class).getTurnNumber()).isEqualTo("8");

		TurnResponse turnResponse =	this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=9",
				TurnResponse.class);

		assertThat(turnResponse.getResponseCode()).isEqualTo(DRAW);
		assertThat(turnResponse.getResponseDescription()).isEqualTo("draw");
		//
	//	assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=6",
	//			TurnResponse.class).getResponseDescription()).isEqualTo("Error board full");
	}


	@Test
	public void testPlayTurnAPIDuplicateTurn() throws Exception {

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/start",
				String.class)).contains("Welcome to tik tac toe, X will take first turn\n" +
				"|---|---|---|\n" +
				"| 1 | 2 | 3 |\n" +
				"|-----------|\n" +
				"| 4 | 5 | 6 |\n" +
				"|-----------|\n" +
				"| 7 | 8 | 9 |\n" +
				"|---|---|---|");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=1",
				TurnResponse.class).getTurnNumber()).isEqualTo("1");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=2",
				TurnResponse.class).getTurnNumber()).isEqualTo("2");



		TurnResponse turnResponse = this.restTemplate.getForObject("http://localhost:" + port + "/playturn?turn=2",
				TurnResponse.class);

		assertThat(turnResponse.getTurnNumber()).isEqualTo("2");
		assertThat(turnResponse.getResponseDescription()).isEqualTo("This Slot is already Taken, please pick another Slot");


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
