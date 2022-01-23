package application;

public class Main {
    public static void main(String[] args) {
                System.out.println("Hello Parser");
        try {
            new Parser().parseFile("sample1.txt");
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }
}
