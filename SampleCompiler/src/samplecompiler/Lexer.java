/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package samplecompiler;

import java.util.regex.*;
import java.io.*;
import java.util.*;

public class Lexer {

    //SAMPLE LEXER OUTPUT (HASHMAP)
    private LinkedHashMap<String, String> tokenSamples = new LinkedHashMap();
    
    public void Lexer() throws IOException {
        try {
            // Open the source code file
            File file = new File("C:\\Users\\CG\\Documents\\NetBeansProjects\\SampleCompiler\\src\\samplecompiler\\source.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // Initialize the lexical analyzer
            String[] tokens = new String[(int) file.length()];
            HashMap<String, String> reserved = RESERVED();
            HashMap<String, String> ar_ops = AR_OPS();
            HashMap<String, String> rel_ops = REL_OPS();
            HashMap<String, String> special_symbols = SPECIAL_SYMBOLS();
            List<Character> letters = LETTERS();
            List<Character> digits_char = DIGITS_CHAR();
            List<Character> identifiers = IDENTIFIERS();
            //System.out.println(identifiers);

            // Analyze each token in the source code
            String line;
            System.out.println("LEXER OUTPUT ===============================");
            while ((line = reader.readLine()) != null) {
                // Tokenize each line using regular expressions
                Pattern pattern = Pattern.compile("\\b\\d*\\.?\\d+\\b|\\b\\w+\\b|\\S");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    String currentToken = matcher.group();
                    if (reserved.containsKey(currentToken)) {
                        System.out.println(currentToken + ": " + reserved.get(currentToken));
                    } else if (ar_ops.containsKey(currentToken)) {
                        System.out.println(currentToken + ": " + ar_ops.get(currentToken));
                    } else if (rel_ops.containsKey(currentToken)) {
                        System.out.println(currentToken + ": " + rel_ops.get(currentToken));
                    } else if (special_symbols.containsKey(currentToken)) {
                        System.out.println(currentToken + ": " + special_symbols.get(currentToken));
                    } else if (!reserved.containsKey(currentToken) || !ar_ops.containsKey(currentToken)
                            || !rel_ops.containsKey(currentToken) || !special_symbols.containsKey(currentToken)) {
                        List<Character> token = new ArrayList<>();
                        for (char c : currentToken.toCharArray()) {
                            token.add(c);
                        }
                        if (digits_char.containsAll(token)) {
                            System.out.println(currentToken + ": is a DIGIT");
                        } else if (identifiers.containsAll(token)) {
                            if (!digits_char.contains(currentToken.charAt(0))) {
                                if (!reserved.containsKey(currentToken)) {
                                    System.out.println(currentToken + ": " + "is an IDENTIFIER");
                                } else {
                                    System.out.println(currentToken + ": is a RESERVED word");
                                }
                            } else {
                                System.out.println(currentToken + ": not a valid identifier");
                            }
                        } else {
                            System.out.println(currentToken + ": invalid Identifier name");
                        }

                    } else {
                        System.out.println(currentToken + ": UNKNOWN TOKEN");
                    }
                }
            }
            System.out.println("\n");
//        System.out.println("TOKENS: " + Arrays.toString(tokens));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //SAMPLE LEXER OUTPUT
        //Integer Initialization
        tokenSamples.put("x", "is an IDENTIFIER");
        tokenSamples.put("=", "ASSIGNMENT");
        tokenSamples.put("10", "is a DIGIT");
        tokenSamples.put(";", "END OF LINE");

        //String Initialization
        tokenSamples.put("Sinds", "DATA TYPE");
        tokenSamples.put("123sa", "not a valid identifier");
        tokenSamples.put("=", "ASSIGNMENT");
        tokenSamples.put("\"", "DOUBLE QUOTES");
        tokenSamples.put("asd", "is an IDENTIFIER");
        tokenSamples.put("\"", "ASSIGNMENT");

        System.out.println(tokenSamples + "\n");

        System.out.println("Lexer HM ==========================================");  
        for (String token : tokenSamples.keySet()) {
            String tokens = token;
            String description = tokenSamples.get(token);
            System.out.println(tokens + " : " + description);
        }
        
        System.out.println("\n");
        Parser par = new Parser(tokenSamples);
        
    }

    private static HashMap<String, String> RESERVED() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("cofs", "INTEGER");
        map.put("luts", "FLOAT");
        map.put("Sinds", "STRING");
        map.put("TAPS", "LOGICAL OPERATOR");
        map.put("GINTS", "LOGICAL OPERATOR");
        map.put("DEINS", "LOGICAL OPERATOR");
        map.put("ifever", "CONTROL");
        map.put("ifnot", "CONTROL");
        map.put("unless", "CONTROL");
        map.put("makegawa", "CONTROL");
        map.put("habang", "CONTROL");
        map.put("kung", "CONTROL");
        return map;
    }

    private static HashMap<String, String> SPECIAL_SYMBOLS() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("{", "OPEN CURLY");
        map.put("}", "CLOSE CURLY");
        map.put("(", "OPEN PARENTHESIS");
        map.put(")", "CLOSE PARENTHESIS");
        map.put("[", "OPEN SQUARE");
        map.put("]", "CLOSE SQUARE");
        map.put("=", "ASSIGNMENT");
        map.put(",", "COMMA");
        map.put(";", "END OF LINE");
        map.put("\"", "DOUBLE QUOTES");
        map.put("\'", "SINGLE QUOTES");
        return map;
    }

    private static HashMap<String, String> AR_OPS() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("+", "ADDITION");
        map.put("-", "SUBTRACTION");
        map.put("*", "MULTIPLICATION");
        map.put("/", "DIVISION");
        map.put("%", "MODULO");
        return map;
    }

    private static HashMap<String, String> REL_OPS() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("==", "EQUALITY");
        map.put("!=", "NON EQUALITY");
        map.put("<", "LESS THAN");
        map.put(">", "GREATER THAN");
        map.put("<=", "LESS THAN OR EQUAL TO");
        map.put(">=", "GREATER THAN OR EQUAL TO");
        return map;
    }

    private static List<Character> LETTERS() {
        List<Character> letterChar = new ArrayList<>();
        for (int i = 97; i < 123; i++) {
            letterChar.add((char) i);
        }

        for (int i = 65; i <= 90; i++) {
            letterChar.add((char) i);
        }
        return letterChar;
    }

    private static List<Character> DIGITS_CHAR() {
        List<Character> digits_char = new ArrayList<>();
        for (int i = 48; i < 58; i++) {
            digits_char.add((char) i);
        }
        return digits_char;
    }

    private static List<Character> IDENTIFIERS() {
        List<Character> digits_char = DIGITS_CHAR();
        List<Character> letter = LETTERS();
        List<Character> identifiers = new ArrayList<>();
        identifiers.addAll(letter);
        identifiers.addAll(digits_char);
        identifiers.add((char) 95);
        return identifiers;
    }

    public LinkedHashMap<String, String> getTokenSamples() {
        return tokenSamples;
    }

}

/*
Sources:
Accessing a HashMap from a different class: https://stackoverflow.com/questions/29197899/accessing-a-hashmap-from-a-different-class
*/