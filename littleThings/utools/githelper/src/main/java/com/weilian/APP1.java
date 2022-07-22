package com.weilian;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>The following is a class description.</p>
 *
 * @author Guo S.Y.
 * @version V1.0
 * @since 2022/7/22
 */
public class APP1 {
    public static void main(String[] args) {

        final String CODE_START = "```bash";
        final String CODE_END = "```";
        final String HTML_BEFORE = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <link href=\"../style/basic_layout.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <link href=\"../style/common_style.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div id=\"container\">";
        final String HTML_AFTER = "    </div>\n" +
                "    <div style=\"text-align:center;\">\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        final String H2_B = "<table class=\"table\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" align=\"center\" border=\"0\">\n" +
                "            <tr>\n" +
                "                <th colspan=\"2\" style=\"text-align: left;\">";
        final String H2_M = "</th>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td width=\"80\" height=\"30\">注解</td>\n" +
                "                <td style=\"text-align:left\">";
        final String H2_A = "</td>\n" +
                "            </tr>\n" +
                "        </table>";
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
                if (strings.get(i).length() > 1) {
                    code.add(strings.get(i));
                }
            } while (!strings.get(++i).equals(CODE_END));
            i++;
            html.setCode(code);
            htmls.add(html);
            // System.out.println("html = " + i + "--" + html);

        }

        // System.out.println(htmls.toString());
        for (int i1 = 0; i1 < htmls.size(); i1++) {
            Html html = htmls.get(i1);
            List<String> code = html.getCode();
            List<String> newCode = new ArrayList<>();
            int len = code.size();
            for (int j = 0; j < len; j++) {
                if (code.get(j).startsWith("$")) {
                    newCode.add(code.get(j));
                    for (int k = j - 1; k >= 0 && !code.get(k).startsWith("$"); k--) {
                        if (code.get(k).startsWith("#")) {
                            newCode.add(code.get(k));
                        }

                    }
                }
            }
            System.out.println("改变后" + newCode.toString());
            System.out.println("改变前" + code.toString());

            html.setNewCode(newCode);
            htmls.set(i1, html);
        }

        for (int i1 = 0; i1 < htmls.size(); i1++) {
            Html html = htmls.get(i1);
            File fileWriter = new File("./githelper/" + html.getName() + ".html");
            FileWriter fw = FileWriter.create(fileWriter, StandardCharsets.UTF_8);
            fw.append(HTML_BEFORE + "\n");
            List<String> newCode = html.getNewCode();
            for (int i2 = 0; i2 < html.getNewCode().size(); i2++) {
                if (newCode.get(i2).startsWith("$")) {
                    if (i2 != 0) {
                        fw.append(H2_A);
                    }
                    fw.append(H2_B);
                    fw.append(newCode.get(i2));
                    fw.append(H2_M);
                } else {
                    fw.append(newCode.get(i2));
                }
            }


            fw.append(HTML_AFTER + "\n");
        }

    }
}
