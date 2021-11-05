import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class java_7785 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		TreeSet<String> resultTreeSet = new TreeSet<String>(Collections.reverseOrder());

		for (int i = 0; i < n; i++) {
			String data = br.readLine();

			String dataList[] = data.split(" ");

			if (dataList[1].charAt(0) == 'e') {
				resultTreeSet.add(dataList[0]);

			} else if (dataList[1].charAt(0) == 'l') {
				resultTreeSet.remove(dataList[0]);
			}
		}

		Iterator it = resultTreeSet.iterator();

		while (it.hasNext()) {
			bw.write((String)it.next()+"\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
