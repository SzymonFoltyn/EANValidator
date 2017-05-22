

import java.util.Scanner;

/**
 * Created by Szymon Foltyn on 2017-05-17.
 */
public class EANValidator {
    public static void main(String[] args) {

        Scanner sc = null;
        String eanCode = null;
        int type = 0;
        EAN ean = null;

        try {
            showMenu();
            sc = new Scanner(System.in);
            int switcher = sc.nextInt();


            switch (switcher) {
                case 1:
                    type = switcher;
                    System.out.println("Please insert all 8 digit of EAN - 8:");
                    eanCode = sc.next();
                    ean = new EAN(type, eanCode);
                    break;
                case 2:
                    type = switcher;
                    System.out.println("Please insert all 13 digit of EAN - 13:");
                    eanCode = sc.next();
                    ean = new EAN(type, eanCode);
                    break;
                default:
                    System.out.println("Invalid value. Try again!");

            }
        } catch (Exception e) {
            throw new RuntimeException(" Something goes wrong. Try again.");
        } finally {
            sc.close();
        }
    }

    public static void showMenu() {
        System.out.println("------   EAN - 8 and EAN - 13 VALIDATOR   ------");
        System.out.println("What EAN want You valid ?");
        System.out.println("press ( 1 ) - validate EAN - 8");
        System.out.println("press ( 2 ) - validate EAN - 13");
    }
}


