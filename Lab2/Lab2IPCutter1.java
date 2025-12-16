class Lab2IPCutter1{
   public static void main (String [] args){
     if(args.length !=1){
      System.out.println("Please input a valid IP address.");
      System.out.println("Ex: java Lab2IPCutter 163.121.12.30");
      return;
     }
     String ip = args[0];
     String [] parts = ip.split("\\.");
     for(int i= 0; i<parts.length;i++){
        System.out.println(parts[i]);
     }
   }
}