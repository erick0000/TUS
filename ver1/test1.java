import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class test1{
    public static int[] Clear_List={1,1,1,1};

    public static void main(String[] args){
	ArrayList<Integer> card = new ArrayList<Integer>();
	card=Put_Player1();
	if(card.isEmpty()){
	    System.out.println("Null");
	}
	else{
	    System.out.println(card);
	}
	//Clear_List={0,0,0,0};
	System.out.println(Arrays.toString(Clear_List));
	
    }
    public static boolean Check(){
	int[] card={0,1,2,3};
	for(int i=0; i<card.length-1; i++){
	    for(int l=i+1; l<card.length; l++){
		System.out.printf("%d = %d\n",card[i],card[l]);
		if(card[i]==card[l]){
		    return false;
		    		    
		}
	    }
	}
	int l=1%4;
	if(l==1){
	    return true;
	}else{
	    return false;
	}
	    
    }
    public static  ArrayList<Integer> Put_Player1(){        
	Scanner scan1 = new Scanner(System.in);
	int num = scan1.nextInt();
	
	ArrayList<Integer> Put = new ArrayList<Integer>();

	Scanner scan2 = new Scanner(System.in);
			
	for(int i=0; i<num; i++){
	    int k =scan2.nextInt();
	    Put.add(k-1);
	}
	Collections.sort(Put);//昇順にソート
	return Put;	
    }
}
