package lab1;

/**
 *
 * @author bratizgut
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Parser {

    private class WordStat implements Comparable<WordStat>{
        private final String str; 
        public int count;
        public WordStat(String buf){
            str = buf;
            count = 1;
        }

        @Override
        public boolean equals(Object obj){
            if(obj == null){
                return false;
            }

            if(!WordStat.class.isAssignableFrom(obj.getClass())){
                return false;
            }

            final WordStat other = (WordStat)obj;
            return this.str.equals(other.str);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 17 * hash + Objects.hashCode(this.str);
            return hash;
        }

        @Override
        public int compareTo(WordStat o) {
            return - (this.count - o.count);
        }
    }

    private void addWord(ArrayList<WordStat> statistic, WordStat buf){
        for(WordStat i : statistic){
            if(i.equals(buf)){
                i.count++;
                return;
            }
        }
        statistic.add(buf);
    }

    public void parse(String fileName) throws IOException {

        File file = new File(fileName);
        try (FileReader fr = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fr);
            
            long wordCounter = 0;
            String str[];
            
            ArrayList<WordStat> statistic = new ArrayList<>();
            while((str = Reader.readLine(reader)) != null){
                for(String i : str){
                    if(!i.isEmpty()){
                        wordCounter++;
                        WordStat buf = new WordStat(i);
                        addWord(statistic, buf);
                    } else {
                    }
                }
            }
            Collections.sort(statistic);
            
            try (FileWriter writer = new FileWriter("output.txt")) {
                for(WordStat i : statistic){
                    writer.write(i.str + ";" + i.count + ";" + String.format("%.2f", (double)i.count / wordCounter * 100) + ";\n");
                }
            }
        }
    }
}