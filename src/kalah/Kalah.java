package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import old.Game1;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure. Remove this comment (or rather, replace it
 * with something more appropriate)
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {

		Input input = new Input(io);
		ShowUI showUI = new ShowUI(io);
		Player player1 = new Player(6, 4);
		Player player2 = new Player(6, 4);
		Game game = new Game(player1, player2, showUI, input);
		game.play();
	}
}
