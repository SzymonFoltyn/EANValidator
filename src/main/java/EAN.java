

/**
 * Created by Szymon Foltyn on 2017-05-17.
 */
public class EAN {

    private String EANCode;
    private int type;
    private int numberOfDigit;
    private GenerateBarcode barcode = null;

    public EAN(int type, String EANCode) {
        this.type = type;
        this.EANCode = EANCode;
       checkType(type, EANCode);


    }

    private int checkType(int type, String EANCode) {
        if (EANCode == null) {
            throw new RuntimeException("Code is empty");
        }

        if (type == 1 && EANCode.length() >= 8 || EANCode.length() > 13 ){
             numberOfDigit = 8;
             validate(EANCode, type);
        } else if (type == 2 && EANCode.length() >= 13) {
             numberOfDigit = 13;
             validate(EANCode, type);
        } else {
            throw new RuntimeException("Inserted invalid value");
        }
        return numberOfDigit;
    }

    private void validate(String EANCode, int type) {

        String codeWithoutCheckSum = EANCode.substring(0, numberOfDigit - 1);
        int originalCheckSum = Integer.parseInt(EANCode.substring(numberOfDigit - 1, numberOfDigit));
        int evens = sumEven(codeWithoutCheckSum);
        int odds = sumOdd(codeWithoutCheckSum);
        int total = odds + evens;
        int checkSum = getEanVd(total);
        if (!(originalCheckSum == checkSum)) {
            throw new RuntimeException("Invalid code");
        } else {
            String checkedEAN = EANCode.substring(0, numberOfDigit);
            System.out.println("Checking EAN - " + numberOfDigit + " is correct and is -- " + checkedEAN +
                    "--");
            barcode = new GenerateBarcode();
            barcode.createPDF(checkedEAN, type);
        }

    }

    private int getEanVd(int sum) {
        return 10 - (sum % 10);
    }

    private int sumEven(String code) {
        int sumEvens = 0;
        for (int i = 0; i < code.length(); i++) {
            if (isEven(i)) {
                sumEvens += Character.getNumericValue(code.charAt(i));
            }
        }
        return sumEvens;
    }

    private int sumOdd(String code) {
        int sumOdds = 0;
        for (int i = 0; i < code.length(); i++) {
            if (!isEven(i)) {
                sumOdds += (Character.getNumericValue(code.charAt(i)) * 3);
            }
        }
        return sumOdds;
    }

    private boolean isEven(int i) {
        return i % 2 == 0;
    }


}
