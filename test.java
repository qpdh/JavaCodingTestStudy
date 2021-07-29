public class test {

	String title;
	String author;
	String publisher;
 
	public test(String title) {
	   System.out.println("클래스 생성자 호출:");
	   this.title = title;
	   System.out.println("제목 = " + title);
	}
 
	public test(String title, String author) {
	   System.out.println("클래스 생성자 호출:");
	   this.title = title;
	   this.author = author;
	   System.out.println("제목 = " + title);
	   System.out.println("작가 = " + author);
	}
 
	public test(String title, String author, String publisher) {
	   System.out.println("클래스 생성자 호출:");
	   this.title = title;
	   this.author = author;
	   this.publisher = publisher;
	   System.out.println("제목 = " + title);
	   System.out.println("작가 = " + author);
	   System.out.println("출판사 = " + publisher);
	}
 
	public static void main(String[] args) {
	   test jumpto = new test("점프 투 파이썬", "박응용", "위키북스");
	   test best = new test("으뜸 파이썬", "박동규");
	   test brain = new test("뇌를 자극하는 파이썬");
 
	}
 }