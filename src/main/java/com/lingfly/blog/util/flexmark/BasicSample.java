package com.lingfly.blog.util.flexmark;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BasicSample {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\Code\\Java\\blog\\src\\main\\java\\com\\lingfly\\blog\\util\\flexmark\\1.md");
        Scanner in = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.nextLine()).append("\n");
        }
        MutableDataSet mutableDataSet = new MutableDataSet();
        Parser parser = Parser.builder(mutableDataSet).build();
        HtmlRenderer renderer = HtmlRenderer.builder(mutableDataSet).build();

        Node document = parser.parse(sb.toString());
        Node childOfType = document.getChildOfType(Heading.class);
        if (childOfType instanceof Heading h) {
            int level = h.getLevel();
            BasedSequence text = h.getText();
        }
        String html = renderer.render(document);
        System.out.println(html);
    }

}
