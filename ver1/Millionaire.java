//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import java.awt.*;

//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import java.awt.*;
import java.util.Arrays;
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
    
    public static ArrayList<Integer> Player_cardset = new ArrayList<Integer>(){//card_set:各プレイヤーが何枚持つか
	    {
		add(13);
		add(13);
		add(14);
		add(14);
	    }
	};
    
    //Player_card:各プレイヤーの手札情報
    public static ArrayList<Integer> Player1_card = new ArrayList<Integer>();
    public static ArrayList<Integer> Player2_card = new ArrayList<Integer>();
    public static ArrayList<Integer> Player3_card = new ArrayList<Integer>();
    public static ArrayList<Integer> Player4_card = new ArrayList<Integer>();

    public static ArrayList<Integer> card_list = new ArrayList<Integer>();//card_list:カード情報
   
    public static int Clear_NoP=0;
    public static ArrayList<Integer> Clear_List = new ArrayList<Integer>();

    public static ArrayList<Integer> Place_num = new ArrayList<Integer>();//場のカードの数リスト
    public static ArrayList<Integer> Place_mark = new ArrayList<Integer>();//場のカードのマークリスト
    public static ArrayList<Integer> Restrict = new ArrayList<Integer>();//場のカードの縛り
    public static boolean Revol = false;//革命
    public static boolean Stair = false;//階段

    public static int Turn_Player=0;//ターンプレイヤーがどのプレイヤーか
    public static int Final_Player = 0;//最後に出したプレイヤー
    
    public static void main(String[] args) {
	for(int i=0; i<54; i++){
	    if(i==52||i==53){
		card_list.add(100);//100はジョーカー
	    }
	    else{
		card_list.add(i);
	    }
	}
	Game();
	
	
    }
	

    public static void Game(){
	Card_set();
	/*
	Print(0);
	Print(1);
	Print(2);
	Print(3);
	*/
	ArrayList<Integer> List = new ArrayList<Integer>();
	boolean Check=false;
        
	while(Clear_NoP != 3){
	    Print(Turn_Player%4);
	    while(!Check){
		List=Turn(Turn_Player);
		if(List.isEmpty()){
		    System.out.println("NULL");
		}
		Check = Check_MyCard(List);
		if(Check){
		    System.out.println("出せました");
		    System.out.printf("\n");
		}
		else{
		    System.out.println("出せませんせした");
		    System.out.printf("\n");
		}
	    }
	    Reflect(List);
	    List.clear();
	    Check=false;
	}
	
	Card_reset();
    }


    //Card_set:手札分配
    public static void Card_set(){
	Clear_List.add(0);
	Clear_List.add(0);
	Clear_List.add(0);
	Clear_List.add(0);
	
	int p1=0,p2=0,p3=0,p4=0;
	//Player_cardsetをシャッフルし手札が多いプレイヤーを選択	
	Collections.shuffle(Player_cardset);

	Collections.shuffle(card_list);//card_listをシャッフルし配布山札シャッフル

	//以下手札分配
	int list=0;
	for(int i=0; i<4; i++){
	    for(int l=0; l<Player_cardset.get(i); l++){
		if(card_list.get(list)==1){
		    Turn_Player=i;//◆3がターンプレイヤー
		    Final_Player=i;
		}
		
		switch(i){
		case 0:
		    Player1_card.add(card_list.get(list));
		    list++;
		    break;
		case 1:
		    Player2_card.add(card_list.get(list));
		    list++;
		    break;		    
		case 2:
		    Player3_card.add(card_list.get(list));
		    list++;
		    break;
		case 3:
		    Player4_card.add(card_list.get(list));
		    list++;
		    break;
		}
	    }
	}
        Collections.sort(Player1_card);
	Collections.sort(Player2_card);
	Collections.sort(Player3_card);
	Collections.sort(Player4_card);
	//以上手札分配終了

	//手札公開
	System.out.print("[");
	for(int i = 0; i<Player1_card.size(); i++) {
            System.out.printf("%d ",Player1_card.get(i));
        }
	System.out.println("]");

	System.out.print("[");
	for(int i = 0; i<Player2_card.size(); i++) {
            System.out.printf("%d ",Player2_card.get(i));
        }
	System.out.println("]");

	System.out.print("[");
	for(int i = 0; i<Player3_card.size(); i++) {
            System.out.printf("%d ",Player3_card.get(i));
        }
	System.out.println("]");

	System.out.print("[");
	for(int i = 0; i<Player4_card.size(); i++) {
            System.out.printf("%d ",Player4_card.get(i));
        }
	System.out.println("]");	
    }

    
    //手札リセット
    public static void Card_reset(){
	Player1_card.clear();
	Player2_card.clear();
	Player3_card.clear();
	Player4_card.clear();	
	Clear_NoP=0;
	Clear_List.clear();
	Place_num.clear();
	Place_mark.clear();
	Restrict.clear();
	Turn_Player = 0;
	Final_Player = 0;
	Revol = false;
	Stair = false;
    }


    public static ArrayList<Integer> Turn(int turn){
	ArrayList<Integer> Put_Card = new ArrayList<Integer>();
	//if(turn == 1){
	    Put_Card = Put_Player1();
	    //}
	    //else{
	    //Put_Card.add(0);
	    //}
	return Put_Card;
    }
    
    public static ArrayList<Integer> Put_Player1(){        
	Scanner scan1 = new Scanner(System.in);
	int num = scan1.nextInt();

	ArrayList<Integer> Put = new ArrayList<Integer>();
	Scanner scan2 = new Scanner(System.in);

	for(int i=0; i<num; i++){
	    int k =scan2.nextInt();
	    switch(Turn_Player%4){
	    case 0:
		Put.add(Player1_card.get(k-1));
		break;
	    case 1:
		Put.add(Player2_card.get(k-1));
		break;
	    case 2:
		Put.add(Player3_card.get(k-1));
		break;
	    case 3:
		Put.add(Player4_card.get(k-1));
		break;
	    }
	}	
	Collections.sort(Put);//昇順にソート
	//System.out.println(Arrays.toString(Put.toArray()));
	return Put;	
    }
    
    //Check_MyCard:出すカードが正しいか
    public static boolean Check_MyCard(ArrayList<Integer> card){
	if(card.isEmpty()){//パス
		return true;
	}
	else{
	    for(int i=0; i<card.size()-1; i++){
		for(int l=i+1; l<card.size(); l++){
		    if(card.get(i)==card.get(l)){
			return false;
		    }
		}
	    }
	    
	    ArrayList<Integer> card_num = new ArrayList<Integer>();//出すカードの数
	    ArrayList<Integer> card_mark = new ArrayList<Integer>();//出すカードのマーク
	    int Joker = 0;//ジョーカーの数
	    boolean Pair_Check = false;//ペアかどうか
	    boolean Stair_Check = false;//階段かどうか
	    int pair_num = 0;//同じ数字がいくつあるか
	    int mark_num = 0;//同じマークがいくつあるか
	    int stair_num = 0;
	    int total = 0;
	    boolean field = false;
	    ArrayList<Integer> set = new ArrayList<Integer>();
	    set.add(100);
	    
	    for(int i=0; i<card.size(); i++){//リストに挿入
		if(card.get(i)==100){
		    card_num.add(card.get(i));
		    card_mark.add(100);
		    Joker++;
		}
		else{
		    card_num.add(card.get(i)/4);
		    card_mark.add(card.get(i)%4);
		}
	    }
	    if(card.size()>1){
		int proJoker = Joker;

	        for(int i=0; i<card_num.size()-1-Joker; i++){//ペア検証
		    if(card_num.get(i) == card_num.get(i+1)){//同じ数探索
			pair_num++;
		    }
		    if(card_mark.get(i) == card_mark.get(i+1)){//同じマーク探索
			mark_num++;
		    }
		}
		if(card_num.size()==Joker){
		    Pair_Check=true;
		}
		if(pair_num+1==card_num.size()-Joker){//ペア判定
		    if(!(Stair && Joker==2 && card_num.size()==3)){
			card_num.removeAll(set);
			for(int i=0; i<Joker; i++){
			    card_num.add(card_num.get(0));
			}
			Pair_Check=true;
		    }
		}
		
		if(!Pair_Check){
		    for(int i=0; i<card_num.size()-1; i++){//ペアでない場合の階段検証
			if(card_num.get(i+1)==100){
			    if(!Revol){
				card_num.set(i+1, card_num.get(i)+1);
			    }
			    else{
				card_num.remove(i+1);
				card_mark.remove(i+1);
				card_num.add(0,card_num.get(0)-1);
				card_mark.add(0,100);
			    }
			}
			
			if(proJoker==2 && card_num.get(i+1)-card_num.get(i)==3){//階段でジョーカー使う
			    //stair_num++;
			    
			    card_num.removeAll(set);
			    card_mark.removeAll(set);
			    card_num.add(i+1, card_num.get(i)+1);
			    card_num.add(i+2, card_num.get(i)+2);
			    card_mark.add(i+1, 100);
			    card_mark.add(i+2, 100);			
			    proJoker-=2;
			}
			if(proJoker>0 && card_num.get(i+1)-card_num.get(i)==2){//階段でジョーカーが使う
			    //stair_num++;
			    //System.out.println("Check");
			    int k=card_num.indexOf(100);
			    System.out.println(k);
			    card_num.remove(k);
			    card_mark.remove(k);			
			    card_num.add(i+1, card_num.get(i)+1);
			    card_mark.add(i+1, 100);
			    proJoker--;
			}
		    }
		}
		
		System.out.println(Arrays.toString(card_num.toArray()));
		
		for(int i=0; i<card_num.size(); i++){
		    stair_num+=i;
		    total+=card_num.get(i);
		}

		if(mark_num+1==card_num.size()-Joker && card_num.get(0)*card_num.size()+stair_num==total){
		    Stair_Check=true;
		}
		
		if(Pair_Check){//ペアの場合
		    System.out.println("Pair");
		    field=Check_Field(false, Joker, card_num, card_mark);
		    
		    //System.out.println(Arrays.toString(card_num.toArray()));
		    //System.out.println(Arrays.toString(card_mark.toArray()));
		    return field;
		}
		
		else if(Stair_Check){//階段の場合
		    System.out.println("Stair");
		    field=Check_Field(true, Joker, card_num, card_mark);
		    //System.out.println(Arrays.toString(card_num.toArray()));
		    //System.out.println(Arrays.toString(card_mark.toArray()));
		    return field;
		}
		else{//不適切
		    return false;
		}
	    }else{//1枚出しの場合
		System.out.println("Single");
		field=Check_Field(false, Joker, card_num, card_mark);		
		return field;
	    }
	    
	}

    }
    //自分のカードが出せるか判定
    public static boolean Check_Field(boolean stair, int Joker, ArrayList<Integer> card_num, ArrayList<Integer> card_mark){
	int proJoker=Joker;
	if(Place_num.isEmpty()){//場にカードがない
	    if(stair){
		if(card_num.size()>3){
		    if(Revol){
			Revol=false;
		    }
		    else{
			Revol=true;
		    }   
		}
		Stair=true;
		System.out.println("Stair_On");
	    }
	    
	    if(!stair && card_num.size()==4){
		if(Revol){
		    Revol=false;
		}
		else{
		    Revol=true;
		}
	    }
	    Place_num.addAll(card_num);
	    Place_mark.addAll(card_mark);
	    if(stair){
		Restrict.add(4);
	    }
	    else{
		for(int i=0; i<card_num.size(); i++){
		    Restrict.add(4);
		}
	    }
	    //System.out.print("場は");
	    //System.out.println(Arrays.toString(Place_num.toArray()));
	    //System.out.println(Arrays.toString(Place_mark.toArray()));
	    return true;
	}
	
	else{
	    if(Stair==true && stair==true){//階段の場合
		
		if(((!Revol && card_num.get(card_num.size()-1) > Place_num.get(Place_num.size()-1)) || (Revol && card_num.get(0) < Place_num.get(0))) && card_num.size() >= Place_num.size()){//数が正しい&枚数を超えている
		    if(Restrict.contains(4) || card_mark.contains(Restrict.get(0))){//縛りを満たしているか
			if(Restrict.contains(4)){//縛り実行
			    for(int i=0; i<card_mark.size(); i++){
				if(card_mark.get(i)!=100 && Place_mark.contains(card_mark.get(i))){
				    Collections.fill(Restrict, card_mark.get(0));
				    break;
				}
			    }
			}
			Place_num.clear();
			Place_mark.clear();
			Place_num.addAll(card_num);
			Place_mark.addAll(card_mark);
			
			if(card_num.size()>3){//階段革命ならば革命を変化
			    if(Revol){
				Revol=false;
			    }
			    else{
				Revol=true;
			    }
			}
			/*
			System.out.print("場は");
			System.out.println(Arrays.toString(Place_num.toArray()));
			System.out.println(Arrays.toString(Place_mark.toArray()));
			*/
			return true;
		    }
		}
		return false;		
	    }
	    else if(Stair==false && stair==false){//階段ではない
		int true_num=0;
		ArrayList<Integer> Put_Rest = new ArrayList<Integer>();
		if(((!Revol && card_num.get(0) > Place_num.get(0)) || (Revol && card_num.get(0) < Place_num.get(0)) ) && card_num.size()==Place_num.size()){
		    
		    for(int i=0; i<Restrict.size(); i++){//縛り判定
			if(Restrict.get(i)!=4 && !card_mark.contains(Restrict.get(i))){
			    if(proJoker>0){
				proJoker--;
			    }
			    else{
				return false;
			    }
			}
		    }
		    
		    for(int i=0; i<card_mark.size(); i++){//縛り実行
			if(card_mark.get(i)!=100 && (Place_mark.contains(card_mark.get(i)) || Restrict.contains(card_mark.get(i)))){//ジョーカーではない時
			    Put_Rest.add(card_mark.get(i));
			}
			else if(card_mark.get(i)==100){//ジョーカーの時
			    for(int l=3; l>=0; l--){
				if((!card_mark.contains(l) || !Put_Rest.contains(l) )&& (Place_mark.contains(l) || Restrict.contains(l))){
				    System.out.printf("Jokerを%d判定しました\n", l);
				    Put_Rest.add(l);
				    break;
				}
			    }
			}else{
			    Put_Rest.add(4);
			}
		    }
		    System.out.println(Arrays.toString(Put_Rest.toArray()));
		    Restrict.clear();
		    Restrict.addAll(Put_Rest);
		    
		    Place_num.clear();
		    Place_mark.clear();
		    Place_num.addAll(card_num);
		    Place_mark.addAll(card_mark);
		    if(card_num.size()==4){//革命実行
			if(Revol){
			    Revol=false;
			}
			else{
			    Revol=true;
			}
		    }
		    /*
		    System.out.print("場は");
		    System.out.println(Arrays.toString(Place_num.toArray()));
		    System.out.println(Arrays.toString(Place_mark.toArray()));
		    */
		    return true;
		}
		else{
		    return false;
		}
	    }else{
		return false;
	    }
	}
	
    }               

    
    public static void Reflect(ArrayList<Integer> card){
	if(!card.isEmpty()){
	    Final_Player=Turn_Player;

	    
	    switch(Turn_Player%4){
	    case 0:
		if(card.indexOf(100)==card.lastIndexOf(100) && Player1_card.indexOf(100)!=Player1_card.lastIndexOf(100)){
		    Player1_card.removeAll(card);
		    System.out.println("add_OK");
		    Player1_card.add(100);
		}
		else{
		    Player1_card.removeAll(card);
		}
		break;
		
	    case 1:
		if(card.indexOf(100)==card.lastIndexOf(100) && Player2_card.indexOf(100)!=Player2_card.lastIndexOf(100)){
		    Player2_card.removeAll(card);
		    System.out.println("add_OK");
		    Player2_card.add(100);
		}
		else{
		    Player2_card.removeAll(card);
		}
		break;
		
	    case 2:
		if(card.indexOf(100)==card.lastIndexOf(100) && Player3_card.indexOf(100)!=Player3_card.lastIndexOf(100)){
		    Player3_card.removeAll(card);
		    System.out.println("add_OK");
		    Player3_card.add(100);
		}
		else{
		    Player3_card.removeAll(card);
		}
		break;
		
	    case 3:				
		if(card.indexOf(100)==card.lastIndexOf(100) && Player4_card.indexOf(100)!=Player4_card.lastIndexOf(100)){
		    Player4_card.removeAll(card);
		    System.out.println("add_OK");
		    Player4_card.add(100);
		}
		else{
		    Player4_card.removeAll(card);
		}
		break;
	    }
	}
	
	if(Place_num.contains(5) || (Final_Player != Turn_Player && Final_Player == Turn_Player%4+1)){ //8切りor1周回った時
	    Place_num.clear();
	    Place_mark.clear();
	    Turn_Player=Final_Player;
	}
	else{
	    Turn_Player++;
	}

    }
    
    //Print
    public static void Print(int number){
	System.out.printf("手番：%d  ", number+1);
	System.out.print("場のカード：");
	if(Place_num.isEmpty()){
	    System.out.println("なし");
	}
	else{
	    for(int i=0; i<Place_num.size(); i++){
		
		if(Place_mark.get(i)==100){
		    System.out.print("Joker ");
		}
		else{
		    switch(Place_mark.get(i)){
			
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
		    if(Place_num.get(i) == 11 || Place_num.get(i)==12){
			System.out.printf("%d  ",Place_num.get(i) -10);
		    }else{
			System.out.printf("%d ",Place_num.get(i) +3);
		    }		    
		}
	    }
	    System.out.print("\n");
	}

	System.out.print("縛り：");
	for(int i=0; i<Restrict.size(); i++){
	    switch(Restrict.get(i)){
	    case 0:
		System.out.printf("♣  ");
		break;
	    case 1:
		System.out.printf("◆  ");
		break;
	    case 2:
		System.out.printf("♥  ");
		break;
	    case 3:
		System.out.printf("♠  ");
		break;		
	    case 4:
		System.out.printf("なし ");
		break;
	    }
	}
	System.out.print("\n");
	
	ArrayList<Integer> cardset = new ArrayList<Integer>(){//各プレイヤーの手札の枚数
		{
		    add(Player1_card.size());
		    add(Player2_card.size());
		    add(Player3_card.size());
		    add(Player4_card.size());
		}
	    };	
	int left=(number+1)%4;
	int front=(number+2)%4;
	int right=(number+3)%4;
	
	int size=0;
	int num1=0;
	int num2=0;
	
	System.out.printf("Player%d %d枚\n",left+1,cardset.get(left));
	System.out.printf("Player%d %d枚\n",front+1,cardset.get(front));
	System.out.printf("Player%d %d枚\n",right+1,cardset.get(right));
	
	ArrayList<Integer> P_Card = new ArrayList<Integer>();
	
	
	switch(number){
	case 0:
	    P_Card.addAll(Player1_card);
	    break;
	case 1:
	    P_Card.addAll(Player2_card);
	    break;
	case 2:
	    P_Card.addAll(Player3_card);
	    break;
	case 3:
	    P_Card.addAll(Player4_card);
	    break;
	}
	
	for(int i=0; i<P_Card.size(); i++){
	    if(P_Card.get(i)==100){
		System.out.printf("Joker ");
	    }else{
		switch(P_Card.get(i)%4){
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
		if(P_Card.get(i)/4 == 11 || P_Card.get(i)/4==12){
		    System.out.printf("%d  ",P_Card.get(i)/4 -10);
		}else{
		    System.out.printf("%d ",P_Card.get(i)/4 +3);
		}
		if(P_Card.get(i)/4<7){
		    num1=i;
		}
		if(P_Card.get(i)/4<11){
		    num2=i;
		}
	    }
	}
	
	System.out.printf("\n");//カード番号表示
	//num.remove(0);
	for(int i=0; i<P_Card.size(); i++){
	    System.out.printf(" %d  ",i+1);
	    if(i>num1 && i<num2 && i<9){		
		System.out.printf(" ");		
	    }
	}
	System.out.printf("\n\n");
	
    }
}
