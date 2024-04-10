package dk.sdu.mmmi.cbse.score.scoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ScoreServiceApplication {

	private long score = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoreServiceApplication.class, args);
	}

	@GetMapping("/score")
	public String getScore() {
		return String.valueOf(score);
	}

	@GetMapping("/reset")
	public void resetScore() {
		score = 0;
	}

	@GetMapping("/update-score")
	public void updateScore(@RequestParam(value = "point") Long point) {
		score += point;
		System.out.println("Updated score: " + score);
	}



}
