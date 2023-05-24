import java.util.List;

public interface Quiz {
    String getQuestion();
    List<String> getChoices();
    boolean check(String in);
    String getAnswer();
}
