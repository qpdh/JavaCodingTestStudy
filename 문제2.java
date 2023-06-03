// import java.util.StringTokenizer;

// class Solution {
// // dic[i] = 소문자와 .으로 이루어진 문자열

// // 완벽하게 일치하면 길이만큼 #로 대체

// // .을 k 이하 길이의 알파벳으로 대체했을 때 비속어가 된다면 #로 대체

// //
// public String solution(int k, String[] dic, String chat) {
// StringBuffer answer = new StringBuffer();

// // 채팅 띄어쓰기 단위로 분리
// StringTokenizer st = new StringTokenizer(chat);

// while (st.hasMoreTokens()) {
// String charWord = st.toString();
// // .이 있는 지 N시간
// if (charWord.contains(".")) {
// // .이 있다면
// // 1이상 k이하 길이의 알파벳으로 대체를 하자
// // .이 있으면 그만큼 띄우자
// //
// }

// // 완벽하게 일치하는 지
// else {
// for (int i = 0; i < dic.length; i++) {
// // 비속어 처리
// if (charWord.equals(dic[i])) {
// answer.append(" ");
// for (int j = 0; j < charWord.length(); j++) {
// answer.append("#");
// }
// }
// }
// }

// }

// return answer.toString();
// }
// }
