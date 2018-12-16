package MathParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(">> ");
        while(true) {
            try {
                System.out.println(MathParser.evaluate(keyboard.readLine()));
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(30);
                System.out.print("Wrong format! Try again.\n>> ");
            }
        }
    }
}
