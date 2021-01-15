package com.example.EitAssignment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Test {

    private static Map<String, String> parseStringToMap(String string) {
        Map<String, String> resultMap = new HashMap<>();

        /*String [] keyValueList = string.split("[,;]+");

        for (String keyValue : keyValueList) {
            String [] splittedKeyVal = keyValue.split(":");

            if (splittedKeyVal.length == 2) {
                String key = splittedKeyVal[0];
                String value = splittedKeyVal[1];

                resultMap.put(key, value);
            }
        }*/

        while (string.length() > 0) {
            int index1 = string.indexOf("<");

        }

        return resultMap;
    }


    public static void main(String[] args) {
        String url = "http://dbpedia.org/page/Barack_Obama_Sr.";

//        try {

//            URL contentUrl = new URL(url);
//            Scanner sc = new Scanner(contentUrl.openStream());
//            StringBuffer sb = new StringBuffer();
//
//            Map<String, String> map = new HashMap<>();
//            while (sc.hasNext()) {
//                String str = sc.next();
//                System.out.println("New String : " + str);
//                sb.append(sc.next());
//            }
//            String result = sb.toString();
//            System.out.println("Result Before Replace : " + result + "\n\n");
//
//            //result = result.substring(result.indexOf(">Property</th>") + 1);
//            System.out.println("After SubString : " + result + "\n\n");
//            result = result.replaceAll("\\<.*?\\>", "###"); ;
//            //result = result.replaceAll("<[A-Za-z0-9_\"=://]+>", "");
//            System.out.println("Result : \n" + result + "\n\n");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try {
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
