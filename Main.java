import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * プログラムのエントリーポイントとなるクラス
 */
public class Main {

    /**
     * プログラムのエントリーポイント
     * 
     * @param args 実行時引数 (今回のプログラムでは使用していない)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Quiz quiz;
        System.out.println("こんにちは");
        System.out.println("第1問");
        try {
            quiz = QuizLoader.load("question/002.txt");
        } catch (FileNotFoundException e){
            return;
        }
        System.out.println(quiz.getQuestion());
        System.out.println("答えを選択肢から選んでください");
        for (int i=0; i < quiz.getChoices().size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(quiz.getChoices().get(i));
        }
        String choice = sc.nextLine();
        if (quiz.check(choice)) {
            System.out.println("正解");
        } else {
            System.out.println("不正解");
            System.out.println("正解は" + quiz.getAnswer());
        }
    }


}