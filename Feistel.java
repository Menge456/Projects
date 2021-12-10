import java.util.ArrayList;


public class Feistel {
	private int[][] product;
	private int rounds;
	private Stack<int[]> keys;
	private Stack<int[]> reflectKeys;
	private boolean isEven;
	private int[] sum;
	
	public Feistel(ArrayList<Integer> message) {
		if(message.size()%2 == 0) 
			product = new int[2][message.size()/2];
		else 
			product = new int[2][message.size()/2 + 1];
		
		//isEven = (message.size()%2 ==0);
		for(int i = 0; i < message.size()/2; i++)
		        product[0][i] = message.get(i);
		      
		for(int i = message.size()/2; i < message.size(); i++)
		        product[1][i-message.size()/2] = message.get(i);
		    
		//gets number of rounds the Feistel will go through
		 
		rounds = (int)(Math.random() * (15) + 3);
		isEven = message.size()%2== 0;
		keys = new Stack<int[]>();
		reflectKeys = new Stack<int[]>();

		//gets the keys I need, which are the same length as halves, but in ArrayLists so I can access them better. Specifically made for xor in mind. 
		for(int i = 0; i < rounds; i++) {
			int[] tempArr;
			if(message.size()%2 == 0) 
				tempArr = new int[message.size()/2];
			else
				tempArr = new int[message.size()/2 + 1];

			for(int j = 0; j < message.size()/2;j++) {
				
				tempArr[j] = (int)(Math.random() * (2));
				
			}
			keys.push(tempArr);
			
		}
		
		
		
		
	}
	
	//invertible equation, xor since I'm too lazy to do anything
	public int[] vertible(int[] key){ // "F = k XOR msg"
	    int[] newRight = new int[product[1].length];
	    //need to later change product[1][i] to newRight[i]  
	    for(int i = 0; i < newRight.length; i++) {
	    	newRight[i] = (key[i] + product[1][i]) % 2;
	      
	    }
	    return newRight;
	}
	
	
	private void round(int round){
		
	    int[] temp;
	    
	    //swapping the left side to former right side after doing function
	    while(round > 0){
	    	
	    
	    	product[1] = vertible(keys.get(0));
	    	
	    	
	    	
	    	product[1] = xor();
	    	
	    	//setting up reflectKeys for decrypt
	    	reflectKeys.push(keys.pop());


	    	temp = product[0];
	    	product[0] = product[1];
	    	product[1] = temp;
	    	round--;
	    	
	    	
	    	
	    }
	    //making keys now compatible with the decode stuff, for some reason this is the cause of the error
	    while(!reflectKeys.isEmpty()) 
	    	keys.stack.add(reflectKeys.pop());
	    
	    temp = product[1];
	    
	    product[1] = product[0];
	    product[0] = temp;
	}
	//100011100011010001011110001101010000011100111000010100111110011101001101011010110110
	
	//																					  111101110110101010011101111001110101000001110011001011110001101110100110110011111001
	//100011100011010001011110001101010000011100111000010100111110011101001101011010110110111101110110101010011101111001110101000001110011001011110001101110100110110011111001
	//10001110001101000101111000110101000001110011100001010011111001110100110101101011011111101110110101010011101111001110101000001110011001011110001101110100110110011111001
	//10001110001101000101111000110101000001110011100001010011111001110100110101101011011011101110110101010011101111001110101000001110011001011110001101110100110110011111001
	//need to add xor function to put into rounds, just adds left and right side together
	private int[] xor() {
		int[] newRight = new int[product[1].length];
	    //need to later change product[1][i] to newRight[i]  
	    for(int i = 0; i < newRight.length; i++) {
	    	newRight[i] = (product[0][i] + product[1][i]) % 2;
	      
	    }
	    return newRight;
	}
	
	
	
	
	public int[] encrypt(){
		
	    round(rounds);
	    sum = new int[product[0].length + product[1].length];
	    
	    
	    
	    int f = 0;
	    for(int x = 0; x < 2; x++)
	    {
	      for(int i = 0; i < product[x].length; i++)
	      {
	        if(f < sum.length){
	        	sum[f] = product[x][i];
	        	
	         
	        }else{
	            throw new IndexOutOfBoundsException("F was too big at: " + f + " while sum's length was " +sum.length);
	        }
	        f++;
	      }
	   
	    }
	    if(isEven == false) {
	    	
		    int[] sumFix = new int[product[0].length + product[1].length - 1];
		  
		    for(int i = 0; i < (sum.length/2)-1; i++) 
		    	sumFix[i] = sum[i];
		    	
		    for(int i = sum.length/2 ; i < sum.length; i++) 
		    	sumFix[i-1] = sum[i];
		    sum = sumFix;
		    
	    }
	    return sum;
	 } 
	public String toString() {
		String ret = "";
		for(int i = 0; i < sum.length; i++)
			ret += sum[i];
		return ret;
	}
	
}
