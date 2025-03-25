package main;

import java.util.Scanner;
import game.HangmanLogic;

public class HangmanApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HangmanLogic hangman = new HangmanLogic();
        System.out.println("1.GUI \n 2.CLI");

        while (!hangman.getPartitaFinita()) {
            System.out.println("Indovina la parola: " + new String(hangman.getLettereIndovinate()));

            if (hangman.getTentativiRimasti() == 6) {
                System.out.println("Lettere errate: " + hangman.getLettereErrate());
                System.out.println("Tentativi rimasti: " + hangman.getTentativiRimasti());
            } else {
                System.out.println("Lettere errate: " + hangman.getLettereErrate());
                System.out.println("Tentativi rimasti: " + hangman.getTentativiRimasti());
                System.out.println(hangman.getDisegnoOmino());
            }

            System.out.println("Inserisci una lettera: ");
            char lettera = sc.next().charAt(0);
            hangman.indovinaLettera(lettera);
        }

        if (hangman.getTentativiRimasti() == 0)
            System.out.println("Hai perso! La parola era: " + hangman.getParolaDaIndovinare());
        else
            System.out.println("Hai vinto! La parola era: " + hangman.getParolaDaIndovinare());

        sc.close();
    }

}
