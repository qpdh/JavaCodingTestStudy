import java.util.Scanner;

public class java_9086_¹®ÀÚ¿­ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		scanner.nextLine();
		
		for (int i = 0; i < T; i++) {
			String str = scanner.nextLine();

			System.out.println(str.charAt(0) + "" + str.charAt(str.length() - 1));
		}
	}
}
