//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import java.awt.*;

//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import java.awt.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
public class Millionaire{
    public static void main(String[] args){
	JFrame fr = new JFrame("Shogi4");
	
	fr.setSize(800,800);
	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fr.getContentPane().setBackground(new Color(255, 255, 255));
	
	Motion panel = new Motion();
	panel.setOpaque(false);
	fr.add(panel);
    
	fr.setVisible(true);
    }        
}
*/

public class Millionaire{
    public static void main(String[] args) {

       	/*
	  private Image[] Heart = new Image[13];
	  private Image[] Spade = new Image[13];
	  private Image[] Club = new Image[13];
	  private Image[] Diamond = new Image[13];
	  private Image[] Joker = new Image[2];    
	*/
	
	int p1=0,p2=0,p3=0,p4=0;
	//以下手札が多いプレイヤーを選択
	int select1 = (int)(Math.random()*4);
	int select2 = select1;

	while(select2 == select1){
	    select2 = (int)(Math.random()*4);
	}
	
	switch(select1){
	case 0:
	    p1=14;
	    break;
	case 1:
	    p2=14;
	    break;
	case 2:
	    p3=14;
	    break;
	case 3:
	    p4=14;
	    break;
	}
	switch(select2){
	case 0:
	    p1=14;
	    break;
	case 1:
	    p2=14;
	    break;
	case 2:
	    p3=14;
	    break;
	case 3:
	    p4=14;
	    break;
	}
	if(p1==0){
	    p1=13;
	}
	if(p2==0){
	    p2=13;
	}
	if(p3==0){
	    p3=13;
	}
	if(p4==0){
	    p4=13;
	}
	//以上選択終了
	//以下手札分配
	//手札の配列Player
        ArrayList<Integer> Player1_num = new ArrayList<Integer>();
	ArrayList<Integer> Player2_num = new ArrayList<Integer>();
	ArrayList<Integer> Player3_num = new ArrayList<Integer>();
	ArrayList<Integer> Player4_num = new ArrayList<Integer>();
	
	ArrayList<Integer> Player1_mark = new ArrayList<Integer>();
	ArrayList<Integer> Player2_mark = new ArrayList<Integer>();
	ArrayList<Integer> Player3_mark = new ArrayList<Integer>();
	ArrayList<Integer> Player4_mark = new ArrayList<Integer>();
	
	int card_list[] = new int[54];
	
	for(int i=0; i<card_list.length; i++){
	    card_list[i] =i;
	    if(i==52||i==53){
		card_list[i] =100;
	    }
	}

	/*
	for(int i=0; i<card_list.length; i++){
	    System.out.println(card_list[i]);    
	}
	*/
	//card_listをランダムに順番変更
	for(int i=0; i<card_list.length; i++){
	    int rnd = (int)(Math.random()*(double)card_list.length);
	    int change = card_list[i];
	    card_list[i] = card_list[rnd];
	    card_list[rnd]=change;
	}

	//System.out.println("変更完了");
	/*
	for(int i=0; i<card_list.length; i++){
	    System.out.println(card_list[i]);    
	}
	*/
	for(int i=0; i<p1; i++){
	    if(card_list[i]==100){
		Player1_num.add(new Integer(100));
	    }else{
		Player1_num.add(new Integer(card_list[i]));
	    }
	}
	for(int i=0; i<p2; i++){
	    if(card_list[i+p1]==100){
		Player2_num.add(new Integer(100));
	    }else{
		Player2_num.add(new Integer(card_list[i+p1]));
	    }
	}
	for(int i=0; i<p3; i++){
	    if(card_list[i+p1+p2]==100){
		Player3_num.add(new Integer(100));
	    }else{
		Player3_num.add(new Integer(card_list[i+p1+p2]));
	    }
	}
	for(int i=0; i<p4; i++){
	    //System.out.println(i+p1+p2+p3);
	    if(card_list[i+p1+p2+p3]==100){
		Player4_num.add(new Integer(100));
	    }else{
		Player4_num.add(new Integer(card_list[i+p1+p2+p3]));
	    }
	}

	Collections.sort(Player1_num);
	//System.out.println(Player1_num);
	Collections.sort(Player2_num);
	//System.out.println(Player2_num);
	Collections.sort(Player3_num);
	//System.out.println(Player3_num);
	Collections.sort(Player4_num);
	//System.out.println(Player4_num);
	
	//以上手札分配終了
	
	for(int i=0; i<Player1_num.size(); i++){
	    if(Player1_num.get(i)==100){
		Player1_mark.add(new Integer(Player1_num.get(i)));
	    }
	    else{
		Player1_mark.add(new Integer(Player1_num.get(i)%4));
		Player1_num.set(i,Player1_num.get(i)/4);
	    }
	}
	for(int i=0; i<Player2_num.size(); i++){
	    if(Player2_num.get(i)==100){
		Player2_mark.add(new Integer(Player2_num.get(i)));
	    }
	    else{
		Player2_mark.add(new Integer(Player2_num.get(i)%4));
		Player2_num.set(i,Player2_num.get(i)/4);
	    }
	}
	for(int i=0; i<Player3_num.size(); i++){
	    if(Player3_num.get(i)==100){
		Player3_mark.add(new Integer(Player3_num.get(i)));
	    }
	    else{
		Player3_mark.add(new Integer(Player3_num.get(i)%4));
		Player3_num.set(i,Player3_num.get(i)/4);
	    }
	}	
	for(int i=0; i<Player4_num.size(); i++){
	    if(Player4_num.get(i)==100){
		Player4_mark.add(new Integer(Player4_num.get(i)));
	    }
	    else{
		Player4_mark.add(new Integer(Player4_num.get(i)%4));
		Player4_num.set(i,Player4_num.get(i)/4);
	    }
	}
	/*
	System.out.println(Player1_num);
	System.out.println(Player1_mark);
	System.out.printf("\n");
	System.out.println(Player2_num);
	System.out.println(Player2_mark);
	System.out.printf("\n");
	System.out.println(Player3_num);
	System.out.println(Player3_mark);
	System.out.printf("\n");
	System.out.println(Player4_num);
	System.out.println(Player4_mark);
	*/
	
	int Place[][]=null;
	int Card_num = 0;
	boolean Revol = false;
	int Final_Player=0;
	
	int TurnPlayer = 1;//(int)(Math.random()*4) +1 ;
	
	Print(p2,p3,p4,Player1_num,Player1_mark);

	while(TurnPlayer!=0){
	    if(Final_Player==TurnPlayer){
		Final_Player=0;
		Place =null;
	    }	    
	    if(TurnPlayer==1){
		int Put_Card[];//Put\Card:置くカードの要素数
		Put_Card = Start_Player1();
		int[][] Put_Field= new int[Put_Card.length][2];//Put_field:置くカードの配列
		
		for(int i=0; i<Put_Card.length;i++){
		    Put_Field[i][0]=Player1_num.get(Put_Card[i]);
		    Put_Field[i][1]=Player1_mark.get(Put_Card[i]);
		}//置くカードの要素数を実際の強さとしてPut_fieldに挿入
		
		//Check();
		Place = Put(Put_Field,Place);
		/*System.out.println(Put_Field);
		for(int i=0; i< Place.length; i++){
		    System.out.printf("%d ",Place[i][0]);
		    System.out.printf("%d\n",Place[i][1]);
		    
		    }*/
		Print(p2,p3,p4,Player1_num,Player1_mark);
		TurnPlayer+=1;
	    }else{
		switch(TurnPlayer){
		case 2:
		    if(p2!=0){
			//Start_ai();
			//Put();
			TurnPlayer+=1;
		    }else{
			TurnPlayer+=1;
		    }
		    Print(p2,p3,p4,Player1_num,Player1_mark);
		    break;
		case 3:
		    if(p3!=0){
			//Start_ai();
			//Put();
			TurnPlayer+=1;
		    }else{
			TurnPlayer=1;
		    }
		    Print(p2,p3,p4,Player1_num,Player1_mark);
		    break;
		case 4:
		    if(p4!=0){
			//Start_ai();
			//Put();
			TurnPlayer=1;
		    }else{
			TurnPlayer=1;
		    }
		    Print(p2,p3,p4,Player1_num,Player1_mark);
		    break;
		}
	    }
	    if(p1*p2==0 && p1*p3==0 && p1*p4==0 && p2*p3==0 && p2*p4==0 && p3*p4==0){
		TurnPlayer=0;
	    }
	    
	    if(TurnPlayer==4){
		TurnPlayer=0;
	    }
	    
	}
	System.out.printf("GameOver\n");

    }


    //Put
    public static int[][] Put(int[][] card, int[][] field){
	for(int i=0; i<card.length; i++){
	    System.out.println(card[0][i]);
	    System.out.println(card[1][i]);
	    System.out.printf("\n");
	}
	if(field==null){
	    //System.out.printf("Null\n");
	    field = new int[card.length][3];
	    
	    for(int i=0; i<card.length; i++){
		field[0][i]=card[0][i];
		//System.out.println(field[i][0]);
		field[1][i]=card[1][i];
		//System.out.println(field[i][1]);
		field[2][i]=0;
		}
	}else{
	    for(int i=0; i<field.length; i++){
		//	field[i][0][0]=card;
		/*
		System.out.println(field[i][0]);
		System.out.println(field[i][1]);
		System.out.printf("\n");
		*/
	    }
	}
	return field;
    }


    
    //Check
    public static void Check(boolean Rev, boolean Stair, int[][] card, int[][][] field){
	if(!Rev){
	    
	}
	
    }


    

    //Print
    public static void Print(int a,int b, int c, ArrayList<Integer> num, ArrayList<Integer> mark){


	
	System.out.printf("                      Player3 %d枚\n",b);
	System.out.printf("Player2 %d枚                                Player4 %d枚\n",a,c);
	for(int i=0; i<num.size(); i++){
	    switch(mark.get(i)){
	    case 0:
		System.out.printf("♣ ");
		break;
	    case 1:
		System.out.printf("◆ ");
		break;
	    case 2:
		System.out.printf("♥ ");
		break;
	    case 3:
		System.out.printf("♠ ");
		break;
	    }
	    if(num.get(i)==100){
		System.out.printf("Joker");
	    }else if(num.get(i) == 11 || num.get(i)==12){
		System.out.printf("%d ",num.get(i)-10);
	    }else{
		System.out.printf("%d ",num.get(i)+3);
	    }
	}
	System.out.printf("\n");
	//num.remove(0);
	for(int i=0; i<num.size(); i++){
	    System.out.printf(" %d  ",i+1);
	    if(num.get(i)>7){
		System.out.printf(" ");
	    }
	}
	System.out.printf("\n");
    }




    
    public static int[] Start_Player1(){        
	Scanner scan1 = new Scanner(System.in);
	int num = scan1.nextInt();

	int Put[] = new int[num];
	Scanner scan2 = new Scanner(System.in);
	
	for(int i=0; i<num; i++){
	    int k =scan2.nextInt();
	    Put[i]=k-1;
	}
	return Put;	
    }


    
}
    
