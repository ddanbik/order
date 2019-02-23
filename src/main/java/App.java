import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    private static Map<String, Zamowienie> dane = new HashMap<String, Zamowienie>();

    public static void main(String[] args) {
        System.out.println("Aplikacja wspomagając obsługę zamówień");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-- MENU --");
            System.out.println("1 - Odczyt zamówień (plik)");
            System.out.println("2 - Zapis zamówiń (plik)");
            System.out.println("3 - Dodaj nowe zamówienie");
            System.out.println("4 - Edytuj zamówienie");
            System.out.println("5 - Wyświetl podsumowanie zamówień");
            System.out.println();
            System.out.println("0 - Wyjście z programu");
            System.out.println("-------------");

            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
//                    Zamowienie.wczytajZamowienie("z1.txt");
                    File folder = new File ("pliki");
                    File[] listofFiles = folder.listFiles();
                    for (File listofFile : listofFiles) {
                        Zamowienie zamowienie = Zamowienie.wczytajZamowienie(listofFile.getName());
                        String key = listofFile.getName().replace(".txt", "");
                        dane.put(key, zamowienie);
                    }
                    System.out.println();
                    break;
                case 2:
//                    Zamowienie.zapiszZanowienie(z,"z1.txt");
//                    Zamowienie zamowienie = new Zamowienie();
                    for (Map.Entry<String, Zamowienie> stringZamowienieEntry : dane.entrySet()) {
                        Zamowienie.zapiszZanowienie(stringZamowienieEntry.getValue(),
                                stringZamowienieEntry.getKey() + ".txt");
                    }




                    break;
                case 3:
                    boolean czyDodacPozycje = true;
                    Zamowienie zamowienie = new Zamowienie();
                    System.out.println("Podaj numer zamówiania");
                    dane.put(scanner.next(), zamowienie);
                    do {
                        System.out.println("Podaj nazwe towaru");
                        String nazwa = scanner.next();
                        System.out.println("Podaj cene towaru");
                        double cena = scanner.nextDouble();
                        System.out.println("Podaj ilosc towaru");
                        int ilosc = scanner.nextInt();
                        zamowienie.dodajPozycje(new Pozycja(nazwa, ilosc, cena));
                        System.out.println("Czy dodać kolejną pozycję?");
                        czyDodacPozycje = scanner.nextBoolean();

                    }
                    while (czyDodacPozycje);
                    break;
                case 4:

                    break;
                case 5:
                    for (Map.Entry<String, Zamowienie> stringZamowienieEntry : dane.entrySet()) {
                        System.out.println("Zamowienie o nr: " + stringZamowienieEntry.getKey());
                        System.out.println(stringZamowienieEntry.getValue());
                    }

                    break;
                case 0:
//                    exit = true;
                    System.exit(69);

                    break;
            }

        }
    }
}
