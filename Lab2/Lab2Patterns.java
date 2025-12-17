class Patterns {
    public static void main(String[] args) {

        int rows = 4;
        int middleSpaces = 8;

        for (int i = 1; i <= rows; i++) {

            for (int j = 1; j <= i + middleSpaces + (2 * i - 1); j++) {
                if (j <= i) {
                    System.out.print("*");
                } else if (j <= i + middleSpaces) {
                    System.out.print(" ");
                } else {
                    if ((j - (i + middleSpaces)) % 2 == 1) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            }

            middleSpaces -= 2;
            System.out.println();
        }
    }
}
