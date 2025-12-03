package Task1;

import java.util.*;

public class GameRatingSystem implements RatingSystem {
    private final Map<String, Player> players;
    private final Set<String> allGames;

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
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Никнейм не может быть пустым");
        }
        if (game == null || game.trim().isEmpty()) {
            throw new IllegalArgumentException("Название игры не может быть пустым");
        }
        if (points <= 0) {
            throw new IllegalArgumentException("Рейтинг должен быть положительным");
        }

        String nick = nickname.trim();
        String gameName = game.trim();

        Player player = players.get(nick);
        if (player == null) {
            return false;
        }

        player.addRating(gameName, points);
        allGames.add(gameName);
        return true;
    }

    @Override
    public Set<String> getAllGames() {
        Set<String> games = new HashSet<>();
        for (Player player : players.values()) {
            games.addAll(player.getGames());
        }
        return games;
    }

    @Override
    public int getPlayerRating(String nickname, String game) {
        if (nickname == null || game == null) {
            return 0;
        }
        Player player = players.get(nickname.trim());
        return player != null ? player.getRating(game.trim()) : 0;
    }

    @Override
    public List<String> getTopPlayersByGame(String game, int limit) {
        if (game == null || limit <= 0) {
            return new ArrayList<>();
        }
        String gameName = game.trim();
        return players.values().stream()
                .filter(p -> p.getRating(gameName) > 0)
                .sorted((p1, p2) -> Integer.compare(p2.getRating(gameName), p1.getRating(gameName)))
                .map(Player::getNickname)
                .limit(limit)
                .toList();
    }

    @Override
    public List<String> getTopPlayersOverall(int limit) {
        if (limit <= 0) {
            return new ArrayList<>();
        }
        return players.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getTotalRating(), p1.getTotalRating()))
                .map(Player::getNickname)
                .limit(limit)
                .toList();
    }

    private static class Player {
        private final String nickname;
        private final Map<String, Integer> ratings;

        public Player(String nickname) {
            this.nickname = nickname;
            this.ratings = new HashMap<>();
        }

        public String getNickname() {
            return nickname;
        }

        public void addRating(String game, int points) {
            ratings.put(game, ratings.getOrDefault(game, 0) + points);
        }

        public int getRating(String game) {
            return ratings.getOrDefault(game, 0);
        }

        public int getTotalRating() {
            return ratings.values().stream().mapToInt(Integer::intValue).sum();
        }

        public Set<String> getGames() {
            return ratings.keySet();
        }
    }
}