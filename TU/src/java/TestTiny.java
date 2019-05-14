
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rahul.yadav
 */
public class TestTiny {
    
     
    public static void main(String[] args) {
      Random rand = new Random();

int  n=0;
String name="123456";
for(int i=0;i<6;i++)
{
    n=rand.nextInt(62);
        System.out.print(""+n);
        
}
    }
}
