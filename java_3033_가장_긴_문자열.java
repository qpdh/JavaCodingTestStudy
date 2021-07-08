import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Vector;

public class java_3033_가장_긴_문자열 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static int MOD = 100003;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int L = Integer.parseInt(br.readLine());

		String enter = br.readLine();

		int minCount = 0;
		int maxCount = L;
		int nowCount = (minCount + maxCount) / 2;

		Boolean isIn;

		int result = 0;

		while (minCount <= maxCount) {
			// key : Hash ��, value : firstIndex
			HashMap<Integer, Vector<Integer>> resultHash = new HashMap<>();
			
			isIn = false;
			nowCount = (minCount + maxCount) / 2;
			//System.out.println(nowCount);
			int firstHash = 0;
			int pow = 1;

			// ���� ���� ��ŭ ���鼭 �ؽ� ����
			for (int firstIndex = 0; nowCount + firstIndex <= L; firstIndex++) {

				isIn = false;
				
				// firstIndex ���� �ؽð� ����
				if (firstIndex == 0) {
					for (int i = 0 + nowCount - 1; i >= 0; i--) {
						firstHash = mod(firstHash + enter.charAt(i) * pow);

						if (i > 0) {
							pow = mod(pow * 7);
						}
					}
				} else {
					firstHash = mod(7 * (firstHash - (enter.charAt(firstIndex - 1) * pow)))
							+ enter.charAt(firstIndex + nowCount - 1);
				}
				
				// �ؽð��� �ߺ�
				if (resultHash.get(firstHash) != null) {
					// �ؽøʿ� ����� firstIndex ���� ��������
					// ������ ���� �ܾ����� ��
					for (Object t : resultHash.get(firstHash).toArray()) {
						isIn = true;
						
						for (int i = 0; i < nowCount; i++) {
							if (enter.charAt(firstIndex + i) != enter.charAt((int) t + i)) {
								isIn = false;
								break;
							}
						}

						// �ߺ��Ǵ� �ܾ ����
						if (isIn) {
							result = nowCount;
							minCount = nowCount + 1;
							break;
						}
					}
					
					resultHash.get(firstHash).add(firstIndex);
				}

				if(isIn)
					break;
				
				// �ؽøʿ� ����� firstIndex�� ����
				else {
					Vector<Integer> t = new Vector<>();
					t.add(firstIndex);
					resultHash.put(firstHash, t);
				}

			}
			
			if(!isIn) {
				if (nowCount == 1) {
					result = 0;
					break;
				}
				maxCount = nowCount - 1;
			}

		}

		bw.write(result+"\n");

		bw.close();
		br.close();

	}

	public static int mod(int n) {
		if (n >= 0)
			return n % MOD;
		return ((-n / MOD + 1) * MOD + n) % MOD;
	}

}
