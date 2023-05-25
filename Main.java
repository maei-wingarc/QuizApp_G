import java.io.IOException;

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
        Game game = new Game("question");
        try {
            game.run();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}