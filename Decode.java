import java.util.Scanner;
import java.lang.Math;

public class Decode
{
    static Scanner usr = new Scanner(System.in);
    static public void main(String[] args)
    {
        String input = "";
        boolean charflag = true, lengthflag = true, bitflag = true;
        
        System.out.println("Enter a Variable Byte: ");
        input = usr.nextLine();

        char[] ch = input.toCharArray();
        int length = ch.length;

        char[] reversed = new char[length];
        int j = length;

        for(int i=0;i<length;i++)
        {
            reversed[j - 1] = ch[i];
            j--;
        }

        while(charflag || lengthflag || bitflag)
        {
            //check for invalid characters
            for(char c:input.toCharArray())
            {
                if(!(c=='0'||c=='1'))
                {
                    charflag = false;
                    break;
                }
            }

            //check for invalid lenght
            if(input.length()%8 !=0) lengthflag = false;


            //check for invalid continuation bit
            for(int i = length-1; i>=0; i--)
            {
                if((double)((i+1)/8) == 1)
                {
                    if(!(reversed[i]=='1'))
                    {
                        bitflag = false;
                        break;
                    }
                }
                else if((i+1)%8==0)
                {
                    if(!(reversed[i]=='0'))
                    {
                        bitflag = false;
                        break;
                    }
                }
            }

            if(!charflag || !lengthflag || !bitflag)
            {
                System.out.println("Invalid Input.\nEnter a Variable Byte:");
                input = usr.nextLine();
                ch = input.toCharArray();
                length = ch.length;
                reversed = new char[length];
                j = length;

                for(int i=0;i<length;i++)
                {
                    reversed[j - 1] = ch[i];
                    j--;
                }
                
                charflag = true; lengthflag = true; bitflag = true;
            }
            else break;
        }

        int bits = (int) (ch.length - Math.ceil((double)ch.length/8)) - 1;
        int sum = 0;

        for(int i=length-1; i>=0; i--)
        {
            if(!((i+1)%8 == 0))
            {
                if(reversed[i]=='1')
                {
                    sum += Math.pow(2, bits);
                    bits--;
                }
                else if(reversed[i]=='0') bits--;
            }
        }

        System.out.println(sum);

    }
}