package Task1;

import java.util.List;
import java.util.Set;

public interface RatingSystem {
    boolean registerPlayer(String nickname);
    boolean addRating(String nickname, String game, int points);
    Set<String> getAllGames();
    int getPlayerRating(String nickname, String game);
    List<String> getTopPlayersByGame(String game, int limit);
    List<String> getTopPlayersOverall(int limit);
    boolean isNicknameTaken(String nickname);
}