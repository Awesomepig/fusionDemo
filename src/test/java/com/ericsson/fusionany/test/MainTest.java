package com.ericsson.fusionany.test;

/**
 * Created by eric on 16-5-17.
 */
public class MainTest {

    public static void mains(String[] args) {
//        String[] preArray = new String[]{"1f60","1f61","1f62","1f63"};
//        String[] subArray = new String[]{"a","b","c","d","e","f"};
//        StringBuilder sb = new StringBuilder("[");
//        for (String pre: preArray) {
//            for (String sub:subArray ) {
//                sb.append("\""+pre+sub+"\",");
//            }
//        }
//        System.out.printf(sb.toString()+"]");
        System.out.printf(Class.class.getClass().getResource("/").getPath());
    }
}
