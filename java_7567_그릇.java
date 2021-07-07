import java.util.Scanner;

public class java_7567_±×¸© {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		String bowls = scanner.nextLine();
		
		int count = 0;
		
		char preBowl=' ';
		
		for(char nowBowl : bowls.toCharArray()) {
			if (preBowl != nowBowl) {
				count+=10;
			}
			else {
				count+=5;
			}
			
			preBowl = nowBowl;
		}
		
		System.out.println(count);
		
	}

}
