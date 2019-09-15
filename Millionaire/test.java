public class test{
    public static void main(String[] args){
	Class cs=new Class();
	cs.i=1;
	//System.out.printf("%d",cs.i);
	int[][] a=new int[2][3];

	a[0][0]=0;
	a[0][1]=1;
	a[0][2]=2;

	a[1][0]=3;
	a[1][1]=4;
	a[1][2]=5;

	//a = null;

	
	
	//if(a==null){
	//	for(int i=0;i<a.length;i++){
	System.out.printf("%d",a.length);
	//}
	//}
    }

    public static void Print(boolean a){
	if(a){
	    System.out.printf("a");
	}
    }
    
}

class Class{
    int i=0;
}
