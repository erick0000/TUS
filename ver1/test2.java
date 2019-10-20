import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class test2{
    public static ArrayList<Integer> Place_num = new ArrayList<Integer>();
    public static ArrayList<Integer> Place_mark = new ArrayList<Integer>();
    public static ArrayList<Integer> Restrict = new ArrayList<Integer>();
    public static boolean Revol = true;//革命
    public static boolean Stair = true;//階段
    public static void main(String[] args) {
	Place_num.add(5);
	Place_num.add(6);
	Place_num.add(7);
	//Place_num.add(5);
	Place_mark.add(0);
	Place_mark.add(0);
	Place_mark.add(0);
	//Place_mark.add(3);
	Restrict.add(4);
	Restrict.add(4);
	Restrict.add(4);
	Restrict.add(4);
	/*
	ArrayList<Integer> card_num = new ArrayList<Integer>();
	ArrayList<Integer> card_mark = new ArrayList<Integer>();

	card_num.add(5);
	card_num.add(5);
	card_mark.add(1);
	card_mark.add(3);
	*/
	ArrayList<Integer> card = new ArrayList<Integer>(){
		{
		    add(21);
		    add(25);
		    add(100);
		    //add(15);
		}
	    };
	System.out.println(Arrays.toString(Place_num.toArray()));
	boolean i = Check_MyCard(card);
	if(i){
	    System.out.println("OK");
	    System.out.println(Arrays.toString(Place_num.toArray()));
	}
	
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



    public static boolean iii(ArrayList<Integer> card_num, ArrayList<Integer> card_mark){
	int true_num=0;		   
	if(card_num.get(0) > Place_num.get(0) && card_num.size()==Place_num.size()){
	    for(int i=0; i<Restrict.size(); i++){//縛り判定
		if(Restrict.get(i)!=4 && !card_mark.contains(Restrict.get(i))){
		    System.out.println("False");
		    return false;
		}
	    }
	    return true;
	}
	return false;
    }
    
}
