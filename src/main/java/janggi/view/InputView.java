package janggi.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readHanSetup() {
        System.out.println("[한(漢) 차림 선택]");
        System.out.println("1: 마상차림  2: 상마차림  3: 마상상마  4: 상마마상(기본)");
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public String readChosetup() {
        System.out.println("[초(楚) 차림 선택]");
        System.out.println("1: 마상차림  2: 상마차림  3: 마상상마  4: 상마마상(기본)");
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public String readPosition(String teamDisplayName) {
        System.out.printf("%s의 차례입니다. 이동할 좌표를 입력하세요 (예: 11 21, 종료: end)%n", teamDisplayName);
        System.out.print("> ");
        return scanner.nextLine().trim();
    }
}
