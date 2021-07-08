import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_5052 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());

			// n�� ��ȭ��ȣ �Է� �ޱ�
			String stringArray[] = new String[n];

			for (int j = 0; j < n; j++) {
				stringArray[j] = br.readLine();
			}

			// �ϰ��� üũ
			checkConsistency(stringArray);
		}

		br.close();
		bw.close();
	}

	public static void checkConsistency(String[] stringArray) throws IOException {

		for (int i = 0; i < stringArray.length - 1; i++) {
			for (int j = i + 1; j < stringArray.length; j++) {
				// ���̰� ������ ���� �ʿ� ����
				if (stringArray[i].length() == stringArray[j].length())
					continue;

				// ���̰� ª���� �������� ���� �� ��
				int minStringLength = (stringArray[i].length() < stringArray[j].length()) ? stringArray[i].length()
						: stringArray[j].length();

				if (compareString(stringArray[i], stringArray[j], minStringLength)) {
					bw.write("NO\n");
					return;
				}
			}

		}
		bw.write("YES\n");
		return;
	}

	public static Boolean compareString(String a, String b, int minStringLength) throws IOException {
		// ���ڿ� ��
		for (int k = 0; k < minStringLength; k++) {
			if (a.charAt(k) != b.charAt(k))
				return false;
		}
		return true;
	}

}
