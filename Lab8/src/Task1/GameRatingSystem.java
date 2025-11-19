package Task1;

import java.util.*;

public class GameRatingSystem implements RatingSystem {
    private Map<String, Player> players;
    private Set<String> allGames;

    public GameRatingSystem() {
        this.players = new HashMap<>();
        this.allGames = new HashSet<>();
    }

    @Override
    public boolean registerPlayer(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Никнейм не может быть пустым");
        }
        String nick = nickname.trim();
        if (players.containsKey(nick)) {
            return false;
        }
        players.put(nick, new Player(nick));
        return true;
    }

    @Override
    public boolean addRating(String nickname, String game, int points) {
        if (points <= 0) {
            throw new IllegalArgumentException("Рейтинг должен быть положительным");
        }
        Player player = players.get(nickname);
        if (player == null) {
            return false;
        }
        player.addRating(game, points);
        allGames.add(game);
        return true;
    }

    @Override
    public Set<String> getAllGames() {
        return new HashSet<>(allGames);
    }

    @Override
    public int getPlayerRating(String nickname, String game) {
        Player player = players.get(nickname);
        return player != null ? player.getRating(game) : 0;
    }

    @Override
    public List<String> getTopPlayersByGame(String game, int limit) {
        return players.values().stream()
                .filter(p -> p.getRating(game) > 0)
                .sorted((p1, p2) -> Integer.compare(p2.getRating(game), p1.getRating(game)))
                .map(Player::getNickname)
                .limit(limit)
                .toList();
    }

    @Override
    public List<String> getTopPlayersOverall(int limit) {
        return players.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getTotalRating(), p1.getTotalRating()))
                .map(Player::getNickname)
                .limit(limit)
                .toList();
    }

    @Override
    public boolean isNicknameTaken(String nickname) {
        return players.containsKey(nickname);
    }

    // Внутренний класс Player
    private static class Player {
        private String nickname;
        private Map<String, Integer> ratings;

        public Player(String nickname) {
            this.nickname = nickname;
            this.ratings = new HashMap<>();
        }

        public String getNickname() { return nickname; }

        public void addRating(String game, int points) {
            ratings.put(game, ratings.getOrDefault(game, 0) + points);
        }

        public int getRating(String game) {
            return ratings.getOrDefault(game, 0);
        }

        public int getTotalRating() {
            return ratings.values().stream().mapToInt(Integer::intValue).sum();
        }
    }
}