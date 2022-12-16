import java.util.Scanner;
import java.lang.Math;

public class Encode
{
    static Scanner usr = new Scanner(System.in);
    public static void main(String args [])
    {
        int input = -1;

        while(input<0)
        {
            System.out.println("Enter a positive integer:");
            input = usr.nextInt();
        } 
    
        int length = (int) Math.ceil((Math.log(input)/Math.log(2))) + 1;
        length =  (int) (length + Math.ceil((double)length/8));

        while(!(length%8 == 0)) length++;
        
        int[] binary = new int[length];

        int bits = (int) (length - Math.ceil((double)length/8)) - 1; 

        for(int i=length-1;i>=0;i--)
        {
            if((double)(i+1)/8 == 1) binary[i] = 1;
            else if((i+1)%8==0) binary[i] = 0;
            else 
            {
                if(input - Math.pow(2, bits) >= 0)
                {
                    binary[i] = 1;
                    input = input - (int)Math.pow(2, bits);
                    bits--;
                }
                else
                {
                    binary[i] = 0;
                    bits--;
                }
            }
        }

        for(int i=length-1;i>=0;i--)
        {
            System.out.print(binary[i]);
        }
    }
}