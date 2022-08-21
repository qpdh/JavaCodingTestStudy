import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

/*  1. 혈통 계산하기
 자식 부 모 : 쌍이 순서대로 주어지지 않음
 부 모 먼저 계산하기 위하여 위상정렬이 필요
 모가 없는지 어떻게 판단하는가?
    -> 위상정렬
*/
public class java_5021 {
    static int N, M;
    // [아들1 : {부,모}, 아들2 : {부,모}]
    static HashMap<String, ArrayList<String>> familyTree;
    static HashMap<String, Double> bloodTree;
    static int globalIndex = 0;

    static ArrayList<String> topologicalResult;
    static Vector<Boolean> isVisited;

    static double dfs(String name) {
        // 이미 저장된 값이 있으면 추가로 처리 안함
        if (bloodTree.get(name) != -1) {
            return bloodTree.get(name);
        }

        // 내가 가장 위면,
        // 왕족이 아니여서 0 이거나,
        // 초대 왕이여서 1인 경우
        if (familyTree.get(name) == null) {
            bloodTree.put(name, 0d);
            return bloodTree.get(name);
        }

        double fatherBlood = dfs(familyTree.get(name).get(0));
        double motherBlood = dfs(familyTree.get(name).get(1));

        // (부+모)/2
        bloodTree.put(name, (fatherBlood + motherBlood) / 2);

        return bloodTree.get(name);
    }

    static void dfsAll() {
        for (String name : bloodTree.keySet()) {
            dfs(name);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가족 정보의 수
        N = Integer.parseInt(st.nextToken());
        // 계승받기를 원하는 사람
        M = Integer.parseInt(st.nextToken());

        familyTree = new HashMap<>();
        bloodTree = new HashMap<>();

        // 혈통 정리

        String king = br.readLine();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String son = st.nextToken();

            if (familyTree.get(son) == null) {
                familyTree.put(son, new ArrayList<>());
            }

            String father = st.nextToken();
            familyTree.get(son).add(father);
            String mother = st.nextToken();
            familyTree.get(son).add(mother);

            bloodTree.put(son, -1d);
            bloodTree.put(father, -1d);
            bloodTree.put(mother, -1d);
        }

        // 왕은 1로 고정
        bloodTree.put(king, 1d);

        // 위상 정렬
        dfsAll();

        for (String name : bloodTree.keySet()) {
            System.out.println(String.format("name : %s\t\tvalue : %f", name,
                    bloodTree.get(name)));
        }

        String successor = br.readLine();
        for (int i = 1; i < M; i++) {
            String competitor = br.readLine();

            if (bloodTree.getOrDefault(successor, 0d) < bloodTree.getOrDefault(competitor, 0d)) {
                successor = competitor;
            }
        }

        bw.write(successor + "\n");

        br.close();
        bw.close();
    }
}
