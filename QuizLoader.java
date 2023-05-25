import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * クイズ定義ファイルを読み込む機能をまとめたクラス
 */
public class QuizLoader {

    /**
     * ファイル一つを行分割のリストで読み込む
     * 
     * @param filename クイズ定義ファイルへのパス
     * @return ファイルに書き込まれている内容を１行ごとに取得する
     * @throws FileNotFoundException ファイルが存在しなかった
     */
    public static List<String> load(String filename) throws FileNotFoundException{
        FileInputStream fis;
        try {
            fis = new FileInputStream(filename);
        }
        catch (FileNotFoundException e){
            System.out.println(filename + "はありません。");
            throw e;
        }
        Scanner sc = new Scanner(fis, "UTF-8");
        List<String> questions = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            questions.add(line);
        }
        return questions;
    }
}
