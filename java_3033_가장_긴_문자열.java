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
			// key : Hash 값, value : firstIndex
			HashMap<Integer, Vector<Integer>> resultHash = new HashMap<>();
			
			isIn = false;
			nowCount = (minCount + maxCount) / 2;
			//System.out.println(nowCount);
			int firstHash = 0;
			int pow = 1;

			// 단위 글자 만큼 돌면서 해시 저장
			for (int firstIndex = 0; nowCount + firstIndex <= L; firstIndex++) {

				isIn = false;
				
				// firstIndex 기준 해시값 생성
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
				
				// 해시값이 중복
				if (resultHash.get(firstHash) != null) {
					// 해시맵에 저장된 firstIndex 전부 가져오기
					// 실제로 같은 단어인지 비교
					for (Object t : resultHash.get(firstHash).toArray()) {
						isIn = true;
						
						for (int i = 0; i < nowCount; i++) {
							if (enter.charAt(firstIndex + i) != enter.charAt((int) t + i)) {
								isIn = false;
								break;
							}
						}

						// 중복되는 단어가 존재
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
				
				// 해시맵에 저장된 firstIndex가 없음
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
