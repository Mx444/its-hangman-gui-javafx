package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanLogic {
    private String[] parole = { "ciao", "computer" };
    private String parolaDaIndovinare;
    private char[] lettereIndovinate;
    private StringBuilder lettereErrate;
    private int tentativiRimasti;
    private boolean partitaFinita;

    // #region Costruttore
    public HangmanLogic() {
        caricaParoleDaFile();

        Random random = new Random();
        this.parolaDaIndovinare = parole[random.nextInt(parole.length)];

        this.lettereIndovinate = new char[parolaDaIndovinare.length()];
        for (int i = 0; i < lettereIndovinate.length; i++)
            lettereIndovinate[i] = '_';

        this.lettereErrate = new StringBuilder();
        this.tentativiRimasti = 6;
        this.partitaFinita = false;
    }

    // #endregion

    // #region Logica
    public boolean indovinaLettera(char lettera) {

        if (!Character.isLetter(lettera)) {
            System.out.println("Inserisci una lettera valida");
            return false;
        }

        if (partitaFinita)
            return false;

        lettera = Character.toLowerCase(lettera);
        if (lettereErrate.indexOf(String.valueOf(lettera)) >= 0 || contieneLettera(lettereIndovinate, lettera)) {
            System.out.println("Lettera già inserita");
            return false;
        }

        boolean letteraTrovata = false;
        for (int i = 0; i < parolaDaIndovinare.length(); i++) {
            if (parolaDaIndovinare.charAt(i) == lettera) {
                lettereIndovinate[i] = lettera;
                letteraTrovata = true;
            }
        }

        if (!letteraTrovata) {
            lettereErrate.append(lettera);
            tentativiRimasti--;
        }

        if (tentativiRimasti == 0)
            partitaFinita = true;

        if (!new String(lettereIndovinate).contains("_"))
            partitaFinita = true;

        return letteraTrovata;

    }

    // #endregion

    // #region Utils

    private void caricaParoleDaFile() {
        List<String> listaParole = new ArrayList<>();

        try {
            InputStream is = getClass().getResourceAsStream("/resources/words.txt");

            if (is == null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(
                        "src/src/main/java/resources/words.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.trim().isEmpty() && !line.startsWith("//")) {
                            listaParole.add(line.trim().toLowerCase());
                        }
                    }
                }
            } else {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.trim().isEmpty() && !line.startsWith("//")) {
                            listaParole.add(line.trim().toLowerCase());
                        }
                    }
                }
            }

            if (listaParole.isEmpty()) {
                parole = new String[] { "ciao", "computer", "vim", "neovim", "vscode" };
                System.out.println("Nessuna parola trovata nel file, uso quelle predefinite.");
            } else {
                parole = listaParole.toArray(new String[0]);
                System.out.println("Caricate " + parole.length + " parole dal file.");
            }

        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
            parole = new String[] { "ciao", "computer" };
        }
    }

    private boolean contieneLettera(char[] array, char lettera) {
        for (char c : array) {
            if (c == lettera)
                return true;
        }
        return false;
    }

    // #endregion

    // #region disegnoOmino
    private static final String[] disegnoOmino = {
            "+---+\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "/|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "/|\\  |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "    |\n" +
                    "=========",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "    |\n" +
                    "========="
    };

    public String getDisegnoOmino() {
        int indice = 6 - tentativiRimasti;
        if (indice < 0)
            indice = 0;
        if (indice >= disegnoOmino.length)
            indice = disegnoOmino.length - 1;
        return disegnoOmino[indice];
    }

    // #endregion

    // #region Getters
    public String getParolaDaIndovinare() {
        return parolaDaIndovinare;
    }

    public char[] getLettereIndovinate() {
        return lettereIndovinate;
    }

    public StringBuilder getLettereErrate() {
        return lettereErrate;
    }

    public int getTentativiRimasti() {
        return tentativiRimasti;
    }

    public boolean getPartitaFinita() {
        return partitaFinita;
    }
    // #endregion
}
