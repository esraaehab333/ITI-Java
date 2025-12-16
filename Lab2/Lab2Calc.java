class Lab2Calc{
    public static void main(String[] args) {
        if(args.length != 3){
            System.out.println("Invaild input!. please try again.");
            System.out.println("Ex: java Lab2Calc 5 + 3");
        }
        // the arg[0] is the first number 
        // the args[1] is the operators
        // the args[2] is the secand number
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[2]);
        String operator = args[1];
        int result = 0;
        if (operator.equals("+"))
            result = num1 + num2;
        else if (operator.equals("-"))
            result = num1 - num2;
        else if (operator.equals("*"))
            result = num1 * num2;
        else if (operator.equals("/"))
            result = num1 / num2;
        else{
         System.out.println("Invaild operator!. please try again.");
         System.out.println("Ex: * , + , - or /");
         return ;
        }
        System.out.println("result = "+result);
    }
}