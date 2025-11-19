package Task1;

import java.util.*;

public class Task01 {
    public static void main(String[] args) {
        RatingSystem ratingSystem = new GameRatingSystem();

        // Регистрация игроков
        System.out.println("=== РЕГИСТРАЦИЯ ИГРОКОВ ===");
        String[] players = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry", "Ivy", "Jack"};
        for (String player : players) {
            if (ratingSystem.registerPlayer(player)) {
                System.out.println("Зарегистрирован: " + player);
            }
        }

        // Попытка регистрации существующего игрока
        if (!ratingSystem.registerPlayer("Alice")) {
            System.out.println("Ник Alice уже занят!");
        }

        // Добавление рейтингов
        System.out.println("\n=== ДОБАВЛЕНИЕ РЕЙТИНГОВ ===");
        ratingSystem.addRating("Alice", "Dota 2", 150);
        ratingSystem.addRating("Alice", "CS:GO", 200);
        ratingSystem.addRating("Bob", "CS:GO", 300);
        ratingSystem.addRating("Charlie", "Dota 2", 250);
        ratingSystem.addRating("Diana", "Valorant", 180);
        ratingSystem.addRating("Eve", "Dota 2", 220);

        // Вывод всех игр
        System.out.println("\n=== ВСЕ ИГРЫ НА САЙТЕ ===");
        Set<String> games = ratingSystem.getAllGames();
        games.forEach(game -> System.out.println("- " + game));

        // Рейтинг конкретного игрока
        System.out.println("\n=== РЕЙТИНГ ИГРОКОВ ===");
        System.out.println("Alice в Dota 2: " + ratingSystem.getPlayerRating("Alice", "Dota 2"));
        System.out.println("Bob в CS:GO: " + ratingSystem.getPlayerRating("Bob", "CS:GO"));

        // Топ игроков по игре
        System.out.println("\n=== ТОП-5 ИГРОКОВ В DOTА 2 ===");
        List<String> topDota = ratingSystem.getTopPlayersByGame("Dota 2", 5);
        for (int i = 0; i < topDota.size(); i++) {
            String player = topDota.get(i);
            int rating = ratingSystem.getPlayerRating(player, "Dota 2");
            System.out.println((i + 1) + ". " + player + " - " + rating);
        }

        // Топ игроков overall
        System.out.println("\n=== ТОП-5 ИГРОКОВ (ОБЩИЙ РЕЙТИНГ) ===");
        List<String> topOverall = ratingSystem.getTopPlayersOverall(5);
        for (int i = 0; i < topOverall.size(); i++) {
            String player = topOverall.get(i);
            System.out.println((i + 1) + ". " + player);
        }
    }
}