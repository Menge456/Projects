import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		/*Steps
		 * Set Huffman and Feistel
		 * Run Huffman encode 
		 * Run Feistel encode 
		 * See what happens
		 */
		
		System.out.print("Message: ");
		String msg = sc.nextLine();
		

		Huffman h = new Huffman(msg);
		h.encode();
		//ask whether or not making a character array into int array would keep the same values.
		//it doesn't use .parseInt()
		String encoded = h.encodedMessage();
		System.out.println(encoded.length()%2 ==0);
		System.out.println(encoded);
		ArrayList<Integer> message = new ArrayList<Integer>();
		for(int i = 0; i < encoded.length();i++) 
			message.add(Integer.parseInt(encoded.substring(i,i+1)));
		Feistel f = new Feistel(message);
		f.encrypt();
		System.out.println(f.toString());
		f.encrypt();
		System.out.println(f.toString());
		System.out.println(f.toString().equals(encoded));
		
		h.decode(f.toString());
		
	}

}
