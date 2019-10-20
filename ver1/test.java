import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/*
public class test {
 
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(){
            {
                add("Apple");
                add("Orange");
                add("Melon");
		
            }
		
        };
        Collections.shuffle(list);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
 
}
*/
public class test{
    public static List<Integer> Player_cardset = new ArrayList<Integer>(){
		{
		    add(13);
		    add(13);
		    add(14);
		    add(14);
		}
	    };
	
    public static ArrayList<Integer> Player1_card = new ArrayList<Integer>();
    public static ArrayList<Integer> Player2_card = new ArrayList<Integer>();
    public static ArrayList<Integer> Player3_card = new ArrayList<Integer>();
    public static ArrayList<Integer> Player4_card = new ArrayList<Integer>();

    public static ArrayList<Integer> card_list = new ArrayList<Integer>();
    
    public static void main(String[] args) {
	for(int i=0; i<54; i++){
	    if(i==52||i==53){
		card_list.add(100);//100はジョーカー
	    }
	    else{
		card_list.add(i);
	    }
	}
	Set();
	Card_reset();
	System.out.println("");
	Set();
    }
    
    
    public static void Set(){	
       	/*
	  private Image[] Heart = new Image[13];
	  private Image[] Spade = new Image[13];
	  private Image[] Club = new Image[13];
	  private Image[] Diamond = new Image[13];
	  private Image[] Joker = new Image[2];    
	*/
	
	int p1=0,p2=0,p3=0,p4=0;
	//以下手札が多いプレイヤーを選択
	/*
	List<Integer> Player_cardset = new ArrayList<Integer>(){
		{
		    add(13);
		    add(13);
		    add(14);
		    add(14);
		}
		};*/
	Collections.shuffle(Player_cardset);

	System.out.print("[");
	for(int i = 0; i<Player_cardset.size(); i++) {
            System.out.printf("%d ",Player_cardset.get(i));
        }
	System.out.println("]");
	
	//以上選択終了
	//以下手札分配
	//手札の配列Player
	/*
        ArrayList<Integer> Player1_card = new ArrayList<Integer>();
	ArrayList<Integer> Player2_card = new ArrayList<Integer>();
	ArrayList<Integer> Player3_card = new ArrayList<Integer>();
	ArrayList<Integer> Player4_card = new ArrayList<Integer>();
	*/
	/*
	ArrayList<Integer> Player1_mark = new ArrayList<Integer>();
	ArrayList<Integer> Player2_mark = new ArrayList<Integer>();
	ArrayList<Integer> Player3_mark = new ArrayList<Integer>();
	ArrayList<Integer> Player4_mark = new ArrayList<Integer>();
	*/
	
        
	
	
	
	Collections.shuffle(card_list);//card_listをランダムに順番変更

	System.out.print("[");
	for(int i = 0; i<card_list.size(); i++) {
            System.out.printf("%d ",card_list.get(i));
        }
	System.out.println("]");

	
	int list=0;
	for(int i=0; i<4; i++){
	    for(int l=0; l<Player_cardset.get(i); l++){
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
    public static void Card_reset(){
	Player1_card.clear();
	Player2_card.clear();
	Player3_card.clear();
	Player4_card.clear();
    }
    
}



/*
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
		    
		    }/*
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


 */
