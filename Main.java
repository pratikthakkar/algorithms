package com.company;

import java.util.*;

public class Main {

    static char high;
    static char secondHigh;
    static  int highCount=0;
    static int secondHighCount=0;

    public static void main(String[] args) {
    String inputString="aabbcada";
    char[] inputCharArray=inputString.toCharArray();
      LinkedHashMap tempMap=createLinkedMapFromCharArray(inputCharArray);
      System.out.println("tempMap contains:"+tempMap.size());
      printElements(tempMap);
    }

    static LinkedHashMap createLinkedMapFromCharArray(char[] inputCharArray)
    {
        LinkedHashMap tempMap = new LinkedHashMap();
        if(inputCharArray.length>0) {
            //char prevChar = inputCharArray[0];
            for (int i = 0; i < inputCharArray.length; i++) {
                if(tempMap.get(inputCharArray[i])!=null)
                {
                    int count=(int)tempMap.get(inputCharArray[i]);
                    count++;
                    if(count>highCount)
                    {
                        highCount=count;
                        high=inputCharArray[i];
                    }
                    else if(count>secondHighCount)
                    {
                        secondHighCount=count;
                        secondHigh=inputCharArray[i];
                    }
                    tempMap.put(inputCharArray[i],count);
                }
                else {
                    if(inputCharArray[i]!=' ') {
                        tempMap.put(inputCharArray[i], 1);
                    }
                }
            }
        }
        return tempMap;
    }

    static  void printElements(LinkedHashMap tempMap)
    {

      int diff=highCount-secondHighCount;
        System.out.println("diff is :"+diff+" second high count:"+secondHighCount);
        LinkedHashMap cloneMap=(LinkedHashMap)tempMap.clone();
        char prevChar=' ';
        while(secondHighCount>=0) {
            tempMap = cloneMap;
            Iterator it = tempMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                int countClone = (int) cloneMap.get(pair.getKey());
                countClone--;
               char currentChar=(char)pair.getKey();
               if(high!=prevChar && high!=currentChar && diff>1 && prevChar!=' ')
               {
                   //System.out.println(high + " = " + diff+"--<");
                   System.out.print(high);
                   diff--;
               }
                if(countClone>=0) {

                    cloneMap.put(pair.getKey(), countClone);
                    System.out.print(pair.getKey());
                    //System.out.println(pair.getKey() + " = " + pair.getValue());
                }
                prevChar=(char)pair.getKey();

            }
            secondHighCount--;
        }
    }
}
