class Lab2IPCutter3{
   public static void main (String [] args){
     if(args.length !=1){
      System.out.println("Please input a valid IP address.");
      System.out.println("Ex: java Lab2IPCutter 163.121.12.30");
      return;
     }
     String ip = args[0];
     while (ip.contains(".")) {
         int dotIndex = ip.indexOf(".");
         System.out.println(ip.substring(0, dotIndex));
         ip = ip.substring(dotIndex + 1);
     }
     System.out.println(ip);
   }
}