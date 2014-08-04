package com.perfect.keywordGroup;

import com.google.common.io.Files;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
/**
 * Created by vbzer_000 on 2014/7/22.
 */
public class Application {
    public static void run(String[] args) throws IOException, BiffException, InterruptedException {
        Map<String, Set<String>> keyMatchMap = getPreMatchWords(args[1]);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "GBK"));
        String s = br.readLine();
        if (s == null)
            return;
        String[] columns = s.split(",");
        boolean find = false;
        int i = 0;
        for (; i < columns.length; i++) {
            String col = columns[i];
            if (col.contains("关键词")) {
                break;
            }
        }
        Map<String, String> keywordLineMap = new HashMap<String, String>();
        int count = 0;
        while ((s = br.readLine()) != null) {
            count++;
            String[] content = s.split(",");
            if (content == null) {
                System.out.println(content);
                continue;
            }
            String keyword = content[i];
            keywordLineMap.put(keyword, s);
        }
        br.close();
        System.out.println(count + " total = " + keywordLineMap.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Task task = new Task(keywordLineMap, keyMatchMap, 0, keywordLineMap.size() - 1);
        forkJoinPool.execute(task);
        while (!task.isDone()) {
            Thread.sleep(500);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[0]+"_output"), "GBK"));
        for (Map.Entry<String, String> entrySet : keywordLineMap.entrySet()) {
// System.out.println(entrySet.getKey() + "==" + entrySet.getValue());
            bw.write(entrySet.getValue());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static Map<String, Set<String>> getPreMatchWords(String preMatch) throws IOException, BiffException {
        String ext = Files.getFileExtension(preMatch);
        Map<String, Set<String>> keyGroupMap = new HashMap<String, Set<String>>();
        if (ext.equals("xls") || ext.equals("xlsx")) {
            Workbook workbook = Workbook.getWorkbook(new File(preMatch));
            Sheet sheet = workbook.getSheet(0);
            Cell[] groupArray = sheet.getColumn(0);
            int row = 0;
            for (Cell group : groupArray) {
                String groupName = group.getContents();
                Cell[] rowArray = sheet.getRow(row++);
                HashSet<String> hashSet = new HashSet<String>(rowArray.length - 1);
                for(int i = 1; i < rowArray.length ; i++){
                    Cell rowCell = rowArray[i];
                    hashSet.add(rowCell.getContents());
                }
                keyGroupMap.put(groupName,hashSet);
            }
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(preMatch), "UTF-8"));
            String s = null;
            while ((s = br.readLine()) != null) {
                if (s.isEmpty()) {
                    continue;
                }
// System.out.println(s);
                String[] keyValue = s.split("=");
                String key = keyValue[0];
                String value = keyValue[1];
                HashSet<String> hashSet = new HashSet<String>();
                String[] preKeys = value.split(",");
                hashSet.addAll(Arrays.asList(preKeys));
                keyGroupMap.put(key, hashSet);
            }
            br.close();
        }
        return keyGroupMap;
    }
}
