import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class App {

    private static Map<String, Zamowienie> dane = new HashMap<String, Zamowienie>();
    private static Set<Klient> klients = new HashSet<Klient>();

    public static void main(String[] args) {
        System.out.println("Aplikacja wspomagając obsługę zamówień");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-- MENU --");
            System.out.println("1 - Odczyt zamówień (plik)");
            System.out.println("2 - Zapis zamówień (plik)");
            System.out.println("3 - Dodaj nowe zamówienie");
            System.out.println("4 - Edytuj zamówienie");
            System.out.println("5 - Wyświetl podsumowanie zamówień");
            System.out.println("6 - Dodaj klienta");
            System.out.println("7 - Wyświetl bazę klientów");
            System.out.println("8 - Zapis bazy klientów (plik)");
            System.out.println("9 - Odczyt bazy klientów (plik)");
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
//                    boolean czyDodacPozycje = true;
                    Zamowienie zamowienie = new Zamowienie();
                    System.out.println("Podaj numer zamówiania");
                    dane.put(scanner.next(), zamowienie);
//                    do {
//                        System.out.println("Podaj nazwe towaru");
//                        String nazwa = scanner.next();
//                        System.out.println("Podaj cene towaru");
//                        double cena = scanner.nextDouble();
//                        System.out.println("Podaj ilosc towaru");
//                        int ilosc = scanner.nextInt();
//                        zamowienie.dodajPozycje(new Pozycja(nazwa, ilosc, cena));
////                        System.out.println("Czy dodać kolejną pozycję?");
////                        czyDodacPozycje = scanner.next();
//                        System.out.println("Czy dodac kolejną pozycję? (t/n)");
//                        String znak = scanner.next();
//                        czyDodacPozycje = znak.equals("t");
//
//                    }
//                    while (czyDodacPozycje);
                    dodajNowaPozycje(scanner, zamowienie);
                    break;
                case 4:
                    System.out.println("Podaj numer zamówienia:");
                    String numerZamowienia = scanner.next();
                    Zamowienie poszukiwaneZamowienie = dane.get(numerZamowienia);
                    if (poszukiwaneZamowienie != null) {
                        boolean czyEdytowacPozycje = true;
                        if (czyEdytowacPozycje) {
                            do {
                                System.out.println("Podaj numer pozycji na zamówiniu");
                                int numerPozycji = scanner.nextInt();
                                System.out.println("Podaj nazwe towaru");
                                String nazwa = scanner.next();
                                System.out.println("Podaj cene towaru");
                                double cena = scanner.nextDouble();
                                System.out.println("Podaj ilosc towaru");
                                int ilosc = scanner.nextInt();
                                poszukiwaneZamowienie.edytujPozycje(numerPozycji, nazwa, ilosc, cena);
                                System.out.println("Czy edytowac kolejną pozycję? (t/n)");
                                String znak = scanner.next();
                                czyEdytowacPozycje = znak.equals("t");

                            }
                            while (czyEdytowacPozycje);
                            System.out.println("Czy dodać nową pozycję? (t/)");
                            boolean nowaPozycja = scanner.next().equals("t");
                            if (nowaPozycja) {
                                //metoda dodająca pozycję

                                dodajNowaPozycje(scanner, poszukiwaneZamowienie);

                            }
                        }
                    } else {
                        System.out.println("Nie udało się znaleźć zamówienia o nr: " + numerZamowienia);
                    }


                    break;
                case 5:
                    System.out.println("#################################");
                    for (Map.Entry<String, Zamowienie> stringZamowienieEntry : dane.entrySet()) {
                        System.out.println("Zamowienie o nr: " + stringZamowienieEntry.getKey());
                        System.out.println(stringZamowienieEntry.getValue());
                        System.out.println("#################################");
                    }
                    break;
                case 6:

                    System.out.println("Czy chcesz dodać klienta indywidualnego czy firmę? (i/f)");

                    if (scanner.next().equals("i")) {

                        System.out.println("Podaj imię");

                        String imie = scanner.next();

                        System.out.println("Podaj nazwisko");

                        String nazwisko = scanner.next();

                        System.out.println("Podaj pesel");

                        Long pesel = scanner.nextLong();

                        System.out.println("Podaj adres");

                        String adres = scanner.next();



                        try {

                            Klient klient = new Klient(imie, nazwisko, pesel, adres);

                            klients.add(klient);

                        } catch (IllegalArgumentException e) {

                            System.out.println("Nie można dodać klienta do bazy ponieważ dane są nieprawidłowe");

                        }

                    } else {

                        System.out.println("Podaj nazwę firmy");

                        String nazwa = scanner.next();

                        System.out.println("Podaj NIP");

                        Long nip = scanner.nextLong();

                        System.out.println("Podaj adres");

                        String adres = scanner.next();

                        try {

                            Klient klient = new Klient(nazwa, nip, adres);

                            klients.add(klient);

                        } catch (IllegalArgumentException e) {

                            System.out.println("Nie można dodać klienta do bazy ponieważ dane są nieprawidłowe");

                        }

                    }

                    break;
                case 7:
                    for (Klient klient : klients) {
//                        System.out.println(klient);
                        if (klient.isFirma()) {
                            System.out.println("Klient firmowy: \n" +
                                    klient.getNazwaFirmy() + ", " +
                                    klient.getNip() + ", " +
                                    klient.getAdres());
                        } else {
                            System.out.println("Klient indywidualny: \n" +
                                    klient.getImie() + ", " +
                                    klient.getNazwisko() + ", " +
                                    klient.getPesel() + ", " +
                                    klient.getAdres());
                        }
                    }

                    break;
                case 8:

                    try {
                        PrintWriter printWriter = new PrintWriter("pliki/baza.txt");

                            for (Klient klient : klients) {
                                if (klient.isFirma()) {
                                    printWriter.print("f");
                                    printWriter.print("~");
                                    printWriter.print(klient.getNazwaFirmy());
                                    printWriter.print("~");
                                    printWriter.print(klient.getNip());
                                    printWriter.print("~");
                                    printWriter.print(klient.getAdres());
                                    printWriter.print("\n");

                                } else {
                                    printWriter.print("i");
                                    printWriter.print("~");
                                    printWriter.print(klient.getImie());
                                    printWriter.print("~");
                                    printWriter.print(klient.getNazwisko());
                                    printWriter.print("~");
                                    printWriter.print(klient.getPesel());
                                    printWriter.print("~");
                                    printWriter.print(klient.getAdres());
                                    printWriter.print("\n");
                                }
                        }

                        printWriter.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Nie ma takiego pliku");
                    }

                    break;
                case 9:
                    try {
                        Scanner scanner1 = new Scanner(new File("pliki/baza.txt"));

                        while (scanner1.hasNextLine()) {
                            String odczyt = scanner1.nextLine();
                            String[] split = odczyt.split("~");
                            Klient klient = null;

                            if (split[0].equals("i")) {
                                klient = new Klient( split[1], split[2], Long.valueOf(split[3]), split[4]);
                            } else if (split[0].equals("f")) {
                                klient = new Klient(split[1], Long.valueOf(split[2]), split[3]);
                            } else {
                                System.out.println("Błędne dane");
                            }
//                            System.out.println(klient);

//                                  NIE DODAJE DO SETA

                            klients.add(klient);



                        }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

                    break;
                case 0:
//                    exit = true;
                    System.exit(69);

                    break;
            }

        }
    }

    public static void dodajNowaPozycje(Scanner scanner, Zamowienie zamowienie) {
        boolean czyDodacPozycje = true;
        do {
            System.out.println("Podaj nazwe towaru");
            String nazwa = scanner.next();
            System.out.println("Podaj cene towaru");
            double cena = scanner.nextDouble();
            System.out.println("Podaj ilosc towaru");
            int ilosc = scanner.nextInt();
            zamowienie.dodajPozycje(new Pozycja(nazwa, ilosc, cena));
//                        System.out.println("Czy dodać kolejną pozycję?");
//                        czyDodacPozycje = scanner.next();
            System.out.println("Czy dodac kolejną pozycję? (t/n)");
            String znak = scanner.next();
            czyDodacPozycje = znak.equals("t");

        }
        while (czyDodacPozycje);


    }

    public static String nazwaTowaru (Scanner scanner) {
        System.out.println("Podaj nazwe towaru");
        return scanner.next();
    }

    public static double caneTowaru (Scanner scanner) {
        System.out.println("Podaj cene towaru");
        return scanner.nextDouble();
    }

    public static int iloscTowaru (Scanner scanner) {
        System.out.println("Podaj ilosc towaru");
        return scanner.nextInt();
    }

//    public static void edytujPozycje


}
