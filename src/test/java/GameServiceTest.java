import org.game.model.Choice;
import org.game.model.Question;
import org.game.service.GameResult;
import org.game.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        Choice choiceNext = new Choice();
        choiceNext.setNext(2);
        choiceNext.setText("Перейти далі");

        Choice choiceEnd = new Choice();
        choiceEnd.setEnd("Ти програв");
        choiceEnd.setText("Програш");

        Choice choiceVictory = new Choice();
        choiceVictory.setVictory("Ти переміг!");
        choiceVictory.setText("Перемога");

        Question q1 = new Question();
        q1.setStep(1);
        Map<String, Choice> choices1 = new HashMap<>();
        choices1.put("a", choiceNext);
        choices1.put("b", choiceEnd);
        choices1.put("c", choiceVictory);
        q1.setChoices(choices1);

        Question q2 = new Question();
        q2.setStep(2);
        q2.setChoices(new HashMap<>());

        Map<Integer, Question> questionMap = new HashMap<>();
        questionMap.put(1, q1);
        questionMap.put(2, q2);

        gameService = new GameService(questionMap);
    }

    @Test
    void testProcessTurnRestart() {
        GameResult result = gameService.processTurn(2, "a", true);
        assertEquals(1, result.getStep());
        assertNotNull(result.getQuestion());
    }

    @Test
    void testProcessTurnNullStep() {
        GameResult result = gameService.processTurn(null, null, false);
        assertEquals(1, result.getStep());
        assertNotNull(result.getQuestion());
    }

    @Test
    void testProcessTurnNormalPlay() {
        GameResult result = gameService.processTurn(1, "a", false);
        assertEquals(2, result.getStep());
    }

    @Test
    void testNextStep() {
        GameResult result = gameService.play(1, "a", false);
        assertEquals(2, result.getStep());
        assertNotNull(result.getQuestion());
    }

    @Test
    void testGameOver() {
        GameResult result = gameService.play(1, "b", false);
        assertEquals("Ти програв", result.getGameOverMessage());
        assertEquals(1, result.getStep());
    }

    @Test
    void testVictory() {
        GameResult result = gameService.play(1, "c", false);
        assertEquals("Ти переміг!", result.getVictoryMessage());
        assertEquals(1, result.getStep());
    }

    @Test
    void testInvalidChoice() {
        GameResult result = gameService.play(1, "x", false);
        assertEquals(1, result.getStep());
        assertNotNull(result.getQuestion());
        assertNull(result.getGameOverMessage());
        assertNull(result.getVictoryMessage());
    }

    @Test
    void testNoChoiceProvided() {
        GameResult result = gameService.play(1, null, false);
        assertEquals(1, result.getStep());
        assertNotNull(result.getQuestion());
    }

    @Test
    void testNoQuestionFound() {
        GameResult result = gameService.play(99, "a", false);
        assertEquals(99, result.getStep());
        assertNull(result.getQuestion());
    }
}
