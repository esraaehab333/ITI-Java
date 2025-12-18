class Lab2Calc {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Invalid input!");
            System.out.println("Ex: java Lab2Calc 5 + 3");
            return;
        }

        int num1, num2;
        String operator = args[1];

        try {
            num1 = Integer.parseInt(args[0]);
            num2 = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid integer numbers.");
            System.out.println("Ex: java Lab2Calc 5 + 3");
            return;
        }

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;

            case "-":
                result = num1 - num2;
                break;

            case "*":
                result = num1 * num2;
                break;

            case "/":
                if (num2 == 0) {
                    System.out.println("Error: Division by zero is not allowed.");
                    return;
                }
                result = num1 / num2;
                break;

            default:
                System.out.println("Invalid operator!");
                System.out.println("Allowed operators: +  -  *  /");
                return;
        }

        System.out.println("Result = " + result);
    }
}
