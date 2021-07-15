import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class java_5052 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			Trie trie = new Trie();

			String taa[] = new String[n];

			for (int j = 0; j < n; j++) {
				taa[j] = br.readLine();
				trie.insert(taa[j]);
			}

			// 일관성이 있는지
			Boolean result = true;

			for (int j = 0; j < n; j++) {
				if (!trie.contains(taa[j])) {
					result = false;
					break;
				}
			}

			if (result) {
				bw.write("YES\n");
			} else {
				bw.write("NO\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}

class Trie {
	Boolean is_terminal;
	HashMap<Character, Trie> childNodes = new HashMap<>();

	public Trie() {
		is_terminal = false;
	}

	Boolean isLastNode() {
		return is_terminal;
	}

	void setLastNode(Boolean isLastChar) {
		is_terminal = isLastChar;
	}

	void insert(String word) {
		Trie trie = this;

		for (int i = 0; i < word.length(); i++) {
			trie = trie.childNodes.computeIfAbsent(word.charAt(i), c -> new Trie());
		}

		trie.setLastNode(true);
	}

	boolean contains(String word) {
		Trie thisNode = this;

		for (int i = 0; i < word.length(); i++) {

			if (thisNode.isLastNode())
				return false;
			thisNode = thisNode.childNodes.get(word.charAt(i));

		}

		return true;
	}
}
