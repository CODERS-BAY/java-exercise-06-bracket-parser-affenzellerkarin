package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.stream.Stream;

public class Parser {

    private final Deque<Character> elementStack;

    public Parser() {
        elementStack = new ArrayDeque<>();
    }

    public void parseFile(final String filename) throws ParsingException {
        try {
            Path path = Paths.get(Objects.requireNonNull(Parser.class.getClassLoader().getResource(filename)).toURI());
            parse(path);
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
        }
    }

    public Deque<Character> getElementStack() {
        return elementStack;
    }

    private void parse(Path path) throws ParsingException {

        int count = 0;

        try (Stream<String> lineStream = Files.lines(path)) {
            for (String line : lineStream.toList()) {
                // get the line as string
                for (int i = 0; i<line.length(); i++) {
                    elementStack.add(line.charAt(i));
                    if (line.charAt(i) == '{') {
                        count++;}
                    else if (line.charAt(i)=='}') {
                        count--;
                        if (count<0) {throw new ParsingException("Error parsing the file. { expected");}
                    }
                }
//                // get the line as string
                System.out.println(line);
            }
            if (count>0) {throw new ParsingException("Error parsing the file. } expected");}
            System.out.println(count);
            getElementStack();
//            if (count>0) {throw new ParsingException("Error parsing the file. There is/are " + count + " } missing");}
//            else if (count<0) {throw new ParsingException("Error parsing the file. There is/are " + (-count) + " { missing");}

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


//        try (Stream<String> lineStream = Files.lines(path)) {
//            for (String line : lineStream.toList()) {
//                for (int i = 0; i < line.length(); i++) {
//                    // elementStack.add(line.charAt(i));
//                    if (line.charAt(i) == '{') {
//                        elementStack.push(line.charAt(i));
//                        count++;
//                    } else if (line.charAt(i) == '}') {
//                        count--;
//                        try {
//                            elementStack.pop();
//                        } catch (NoSuchElementException e) {
//                            throw new ParsingException("Error parsing the file. { expected");
//                        }
//                    }
//                }
////                // get the line as string
//                System.out.println(line);
//            }
//            if (!elementStack.isEmpty()) {
//                throw new ParsingException("Error parsing the file. } expected");
//            }
//            System.out.println(count);
//            System.out.println(getElementStack());
//
//        } catch(IOException e){
//            System.err.println(e.getMessage());
//        }

}
}


