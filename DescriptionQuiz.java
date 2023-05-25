import java.util.Collections;
import java.util.List;

/**
 * 記述型のクイズ機能を提供する
 */
public class DescriptionQuiz implements Quiz {
    final private String question;
    final private String answer;

    /**
     * 記述型クイズクラスを初期化する
     * 
     * @param question 問題文
     * @param answer 正答
     */
    public DescriptionQuiz(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /** {@inheritDoc} */
    @Override
    public String getQuestion() {
        return this.question;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public List<String> getChoices() {
        return Collections.emptyList(); /// XXX: とりあえず空のリスト返すよ
    }

    /** {@inheritDoc} */
    @Override
    public boolean check(String in) {
        return answer.equals(in);
    }

    /** {@inheritDoc} */
    @Override
    public String getAnswer() {
        return this.answer;
    }
    
}
