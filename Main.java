import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("こんにちは");
        System.out.println("第1問");
        System.out.println("内容");
        try {
            QuizLoader.load("README.md").forEach(System.out::println);
        } catch (FileNotFoundException e){
            return;
        }
        System.out.println("答えを選択肢から選んでください");
        System.out.println(sc.nextLine());
        System.out.println("正解");
    }


}