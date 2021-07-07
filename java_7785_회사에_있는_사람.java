import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class java_7785_회사에_있는_사람 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		HashMap<String, Boolean> resultHashMap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			String data = br.readLine();

			String dataList[] = data.split(" ");

			if (dataList[1].equals("enter")) {
				resultHashMap.put(dataList[0], true);

			} else if (dataList[1].equals("leave")) {
				resultHashMap.put(dataList[0], false);
			}
		}

		Object[] resultArray = resultHashMap.keySet().toArray();
		Arrays.sort(resultArray, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				int minLength;

				if (o1.toString().length() < o2.toString().length()) {
					minLength = o1.toString().length();
				} else {
					minLength = o2.toString().length();
				}

				for (int i = 0; i < minLength; i++) {
					if (o2.toString().charAt(i) < o1.toString().charAt(i)) {
						return -1;
					} else if (o2.toString().charAt(i) > o1.toString().charAt(i)) {
						return 1;
					}
				}

				if (minLength == o1.toString().length()) {
					return 1;
				} else {
					return -1;
				}

			}

		});

		for (

				int i = 0; i < resultArray.length; i++) {
			if (resultHashMap.get(resultArray[i])) {
				System.out.println(resultArray[i]);
			}
		}

		br.close();
	}
}
