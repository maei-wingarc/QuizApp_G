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
        game.run();
    }
}