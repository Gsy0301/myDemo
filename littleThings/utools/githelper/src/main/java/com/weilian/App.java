package com.weilian;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        final String CODE_START = "```bash";
        final String CODE_END = "```";
        final String HTML_BEFORE = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>";
        final String HTML_AFTER = "</body>\n" +
                "</html>";

        final String H2_B = "<h2>";
        final String H2_A = "</h2>";

        System.out.println("Hello World!");
        FileReader fileReader = new FileReader("Git.txt");
        List<String> strings = fileReader.readLines();
        List<Html> htmls = new ArrayList<>();
        int i = 0;
        while (i < strings.size()) {

            String line = strings.get(i);
            Html html = new Html();
            List<String> code = new ArrayList<>();
            if (line.startsWith("-")) {
                html.setName(line.split(" ")[1]);
                html.setZhName(line.split(" ")[line.split(" ").length - 1]);
            }
            i = i + 2;
            do {
                code.add(strings.get(i));
            } while (!strings.get(++i).equals(CODE_END));
            i++;
            html.setCode(code);
            htmls.add(html);
            System.out.println("html = " + i + "--" + html);

        }

        for (int i1 = 0; i1 < htmls.size(); i1++) {
            Html html = htmls.get(i1);
            File fileWriter = new File("./githelper/" + html.getName() + ".html");
            FileWriter fw = FileWriter.create(fileWriter, StandardCharsets.UTF_8);
            fw.append(HTML_BEFORE + "\n");
            for (int i2 = 0; i2 < html.getCode().size(); i2++) {
                fw.append(H2_B);
                fw.append(html.getCode().get(i2));
                fw.append(H2_A + "\n");
            }
            fw.append(HTML_AFTER + "\n");
        }

        for (int i1 = 0; i1 < htmls.size(); i1++) {
            Html html = htmls.get(i1);
            File fileWriter = new File("./githelper/" +  "preload.txt");
            FileWriter fw = FileWriter.create(fileWriter, StandardCharsets.UTF_8);
            fw.append("{\n");
            fw.append("t: 'git " + html.getName() + "',\n");
            fw.append("d: '" + html.getZhName() + "',\n");
            fw.append("p: 'doc/" + html.getName() + ".html'\n");
            fw.append("},\n");
        }
    }
}
