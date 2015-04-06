package ver1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        QuestionsGenerator g = new QuestionsGenerator("questions.txt");
        g.read();
        String[] questions = g.get();
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
        }

        sc.close();
    }

}
