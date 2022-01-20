import java.util.ArrayList;

public class Huffman {

    static ArrayList<BNode> aNodes = new ArrayList<BNode>();
    static ArrayList<Character> c = new ArrayList<Character>();
    static ArrayList<Integer> num = new ArrayList<Integer>();
    static ArrayList<Character> unChangedMessage = new ArrayList<Character>();
    static String[] newCode;
    static 	BTree tree = new BTree();
    static String encode;
   public Huffman(String t) { 
	   encode = t;
   }
    

   
    //Frequency Function
    public static void findf(){

        for(int i = 0; i<encode.length(); i++){
            String ss = encode.substring(i,i+1);
            char spot = ss.charAt(0);
            boolean add = true;

            for(int j = 0; j< c.size(); j++){
                if(c.get(j).equals(spot)){
                    add = false;
                    num.set(j,num.get(j) + 1);
                }
            } 

            if(add){
                c.add(spot);
                unChangedMessage.add(spot);
                num.add(1);
            }

        }
        newCode = new String[c.size()];
        

    }
    
    public static void encode() {
    	//frequency stuff
    	findf();
        //Step 1  
        
    	//sorting everything
        while(num.size() > 0) {
        	int maxI = 0; 
	        for(int i = 0; i < num.size(); i++) {
	        	
	        	if(num.get(i) > num.get(maxI)) {
	        		maxI = i;
	        	}
	        	
	        }
	        aNodes.add(new BNode(num.remove(maxI),c.remove(maxI)));
	        
	        
        }
        while(aNodes.size() > 1) {
        	BNode combo;
        	int a = aNodes.size()-1;
        	int left = aNodes.get(a).data;
        	int right = aNodes.get(a-1).data;
        	
        	combo = new BNode(left + right);
        	
        	combo.left = aNodes.remove(aNodes.size()-1);
        	combo.right = aNodes.remove(aNodes.size()-1);
        	
        	boolean wentIn = false;
        	
        	//just making sure we're adding to the right spot
        	if(aNodes.size() > 0) {
	        	if(combo.data >= aNodes.get(0).data) {
	        		aNodes.add(0,combo);
	        	}else if (combo.data < aNodes.get(0).data){
	        		
		        	for(int i = 1; i < aNodes.size(); i++) {
		        		BNode b4Node = aNodes.get(i-1);
		        		BNode aftNode = aNodes.get(i);
		        		if(aftNode.data == combo.data) {
		        			aNodes.add(i, combo);
		        			wentIn = true;
		        			break;
		        		}
		        		if(aftNode.data < combo.data && b4Node.data > combo.data) {
		        			aNodes.add(i+1,combo);
		        			wentIn = true;
		        			break;
		        		}

		        	}
		        	if(!wentIn) {
		        		aNodes.add(combo);
		        	}
		      
	        	}
        	}else if(aNodes.size() == 0)
        		aNodes.add(combo);
        	
        }
       
       
        
        
        tree.root = aNodes.get(0);
        showHuffman(tree.root, ""); 
    }
    public static void decode(String decode) {
    	BNode a = tree.root;
    	String ret = "";
    	for(int i = 0; i < decode.length(); i++) {
    		char ss = decode.substring(i,i+1).toCharArray()[0];
    		if(a.left == null && a.right == null) {
    			ret += a.c;
    			
    			a = tree.root;
    		} 
    		if(ss == '1' ) 
    			a = a.right;
    		if(ss == '0') 
    			a = a.left;
    			
    		
    	}
    	if(a.left==null && a.right == null) 
			ret += a.c;
    	
    	
    	System.out.println(ret);
    }
    public static void showHuffman(BNode a, String s){
		if(a.left != null) {
			showHuffman(a.left, s + "0");
		}
		if(a.right != null) {
			showHuffman(a.right, s+ "1");
		}
		
		if(a.left == null && a.right == null) {
			newCode[unChangedMessage.indexOf(a.c)] = s;
		}
	}
    public static String encodedMessage() {
    	String ret = "";
    	char[] encodeChar = encode.toCharArray();
    	for(int i =0; i < encodeChar.length; i++) 
    		ret += newCode[unChangedMessage.indexOf(encodeChar[i])];
    	
    	
    	return ret;
    }

}
