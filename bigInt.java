import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//abcdef
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan= new Scanner(System.in);
        String s1= scan.next();
        String s2= scan.next();
        Solver my_solve= new Solver(s1,s2);
        System.out.println(my_solve.madd());
        System.out.println(my_solve.mmul()); 
    }
    
    
}

class Solver{
    private String first_num;
    private String second_num;
    private String sum;
    private String multi;
    public Solver (String s1,String s2){
        if(s1.length()<=s2.length()){
            first_num=s1;
            second_num=s2;
        }
        else{
            first_num=s2;
            second_num=s1;
        }
    }
    
    public String madd(){
        int first_len= first_num.length();
        int second_len= second_num.length();
        String ret="";
        int i=0;
        int rem=0;
        for(;i<first_len;i++){
            int c1= Character.getNumericValue(first_num.charAt(first_len-1-i));
            int c2= Character.getNumericValue(second_num.charAt(second_len-1-i));
            int sum_c= c1+c2+rem;
            if(sum_c>9){
                rem=1;
                sum_c-=10;
            }
            else{
                rem=0;
            }
            ret=sum_c+ret;
        }
        // i++;
        for(;i<second_len;i++){
            int c2= Character.getNumericValue(second_num.charAt(second_len-1-i));
            int sum_c= c2+rem;
            if(sum_c>9){
                rem=1;
                sum_c-=10;
            }
            else{
                rem=0;
            }
            ret=sum_c+ret;
        }
        if(rem==1)ret=1+ret;
        return ret;
    }
    
    public String mmul_basic(String s1, char s2){
        int c2= Character.getNumericValue(s2);
        String ret="";
        for(int i=0;i<c2;i++){
            // System.out.println('.');
            Solver tmp= new Solver(ret,s1);
            ret=tmp.madd();
        }
        return ret;
    }
    
    public String mmul(){
        for(int i=0;i<first_num.length();i++){
            if(first_num.charAt(i)!='0')break;
            return "0";
        }
        
        for(int i=0;i<second_num.length();i++){
            if(second_num.charAt(i)!='0')break;
            return "0";
        }
        String ret="";
        for(int i=0;i<second_num.length();i++){
            ret+='0';
            String mul_basic= 
                        mmul_basic(first_num,second_num.charAt(i));
            Solver tmp= new Solver(ret,mul_basic);
            ret= tmp.madd();
        }
        return ret;
    }
}
