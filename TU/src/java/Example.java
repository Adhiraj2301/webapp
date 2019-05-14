/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rahul.yadav
 */
import com.tikona.tiny.service.URLShortener;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.tomcat.util.codec.binary.Base64;
public class Example {
   public static void main(String[] args) 
   {
       //Using Date class
       Date date = new Date();
       //Pattern for showing milliseconds in the time "SSS"
       DateFormat sdf = new SimpleDateFormat("yyyMMddHHmmsSSS");
       String stringDate = sdf.format(date);
       System.out.println(stringDate);
       
       char ch[]=stringDate.toCharArray();
       
       for(int i=0;i<10;i++)
       {
           
           System.out.println(""+ch[i]);
       }
      
      
       
       byte[] bytesEncoded = Base64.encodeBase64(stringDate.getBytes());
System.out.println("encoded value is " + new String(bytesEncoded));

   }
   
}
