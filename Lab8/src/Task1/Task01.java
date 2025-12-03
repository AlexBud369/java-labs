package Task1;

import java.util.*;

/*
* Есть сайт, на котором рассчитывается рейтинг игроков различных сетевых игр.
* Игрок при регистрации указывает ник, а так же список игр, в которые он играет.
*Задача – написать программу, которая:
*- регистрирует игроков в системе (должна быть проверка, занят ли ник);
*- добавляет рейтинг игроку, в случае его выигрыша в игре;
- выводит список игр, в которые играют все игроки на сайте;
- выводит рейтинг по имени игрока и игре;
- выводит 10 лучших игроков в определенной игре;
- выводит 10 лучших игроков с учетом всех игр.


* */

public class Task01 {
    public static void main(String[] args) {
        RatingSystem ratingSystem = new GameRatingSystem();

        String[] players = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry", "Ivy", "Jack"};
        for (String player : players) {
            if (ratingSystem.registerPlayer(player)) {
                System.out.println("Зарегистрирован: " + player);
            }
        }

        if (!ratingSystem.registerPlayer("Alice")) {
            System.out.println("Ник Alice уже занят!");
        }

        ratingSystem.addRating("Alice", "Dota 2", 150);
        ratingSystem.addRating("Alice", "CS:GO", 200);
        ratingSystem.addRating("Bob", "CS:GO", 300);
        ratingSystem.addRating("Charlie", "Dota 2", 250);
        ratingSystem.addRating("Diana", "Valorant", 180);
        ratingSystem.addRating("Eve", "Dota 2", 220);
        ratingSystem.addRating("Alice", "Valorant", 120);
        ratingSystem.addRating("Bob", "Dota 2", 100);

        Set<String> games = ratingSystem.getAllGames();
        games.forEach(game -> System.out.println("- " + game));

        System.out.println("Alice в Dota 2: " + ratingSystem.getPlayerRating("Alice", "Dota 2"));
        System.out.println("Bob в CS:GO: " + ratingSystem.getPlayerRating("Bob", "CS:GO"));

        List<String> topDota = ratingSystem.getTopPlayersByGame("Dota 2", 5);
        for (int i = 0; i < topDota.size(); i++) {
            String player = topDota.get(i);
            int rating = ratingSystem.getPlayerRating(player, "Dota 2");
            System.out.println((i + 1) + ". " + player + " - " + rating);
        }

        List<String> topOverall = ratingSystem.getTopPlayersOverall(5);
        for (int i = 0; i < topOverall.size(); i++) {
            String player = topOverall.get(i);
            System.out.println((i + 1) + ". " + player);
        }
    }
}