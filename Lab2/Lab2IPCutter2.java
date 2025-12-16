import java.util.StringTokenizer;
class Lab2IPCutter2{
   public static void main (String [] args){
     if(args.length !=1){
      System.out.println("Please input a valid IP address.");
      System.out.println("Ex: java Lab2IPCutter 163.121.12.30");
      return;
     }
     String ip = args[0];
     StringTokenizer st = new StringTokenizer(ip,".");
     while(st.hasMoreTokens()){
         System.out.println(st.nextToken());
     }
   }
}