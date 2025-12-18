class Complex {
    int real;
    int imag;
    
    Complex(int r, int i) {
        real = r;
        imag = i;
    }
    
    Complex add(Complex c) {
        return new Complex(
            this.real + c.real,
            this.imag + c.imag
        );
    }
    
    Complex subtract(Complex c) {
        return new Complex(
            this.real - c.real,
            this.imag - c.imag
        );
    }
    
    void display() {
        if (imag > 0 && real > 0) {
            if (imag == 1)
                System.out.println(real + " + i");
            else
                System.out.println(real + " + " + imag + "i");
        }
        else if (imag < 0 && real > 0) {
            if (imag == -1)
                System.out.println(real + " - i");
            else
                System.out.println(real + " - " + (-imag) + "i");
        }
        else if ((imag < 0 && real == 0) || (imag > 0 && real == 0)) {
            if (imag == 1)
                System.out.println("i");
            else if (imag == -1)
                System.out.println("-i");
            else
                System.out.println(imag + "i");
        }
        else if ((imag == 0 && real > 0) || (imag == 0 && real < 0) || (imag == 0 && real == 0))
            System.out.println(real);
        else if (imag < 0 && real < 0)
            System.out.println(real + " - " + (-imag) + "i");
        // Removed the extra System.out.println(); that was here
    }
    
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Invalid input!");
            System.out.println("Ex: java Complex 5 6 2 3");
            return;
        }
        int r1 = Integer.parseInt(args[0]);
        int i1 = Integer.parseInt(args[1]);
        int r2 = Integer.parseInt(args[2]);
        int i2 = Integer.parseInt(args[3]);
        Complex c1 = new Complex(r1, i1);
        Complex c2 = new Complex(r2, i2);
        Complex sum = c1.add(c2);
        Complex diff = c1.subtract(c2);
        System.out.print("Addition: ");
        sum.display();
        System.out.print("Subtraction: ");
        diff.display();
    }
}