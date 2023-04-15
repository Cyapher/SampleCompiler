/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package samplecompiler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import samplecompiler.Lexer;

public class Parser {

    private LinkedHashMap<String, String> tokenSamples = new LinkedHashMap();

    public Parser(LinkedHashMap<String, String> tokenSamples) {

        this.tokenSamples = tokenSamples;
        System.out.println("PARSER TOKEN SAMPLES " + this.tokenSamples);
        
        for(String token: tokenSamples.keySet()){
            System.out.println();
        }

//        //NTS: place LHashMap for the tokens
//        String currentToken;
//        Lexer lex = new Lexer();
//        tokenSamples = lex.getTokenSamples();
//        
//        //PARSER TOKEN SAMPLES
//        System.out.println("PARSER TOKEN SAMPLES ===============================");
//        System.out.println(tokenSamples);
//        /*
//        luts x = 10;
//        Sinds 123sa = "asd";
//        
//        habang a TAPS b {
//            luts a;
//            luts b = 12;
//            cofs c = z + 1;
//        }   
//         */        
    }

    public void Advance() {

    }

    /*
    MATCH METHOD ===============================================================
    proc MATCH("")
    
    
     */
    public void Match() {

    }

    public void Error() {

    }
}
