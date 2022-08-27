package com.lingfly.blog.util.flexmark;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ext.tables.*;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.profile.pegdown.Extensions;
import com.vladsch.flexmark.profile.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.TextCollectingVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BasicSample {
    final private static DataHolder OPTIONS = PegdownOptionsAdapter.flexmarkOptions(
            Extensions.TABLES
    );

    static final MutableDataSet FORMAT_OPTIONS = new MutableDataSet();
    static {
        // copy extensions from Pegdown compatible to Formatting
        FORMAT_OPTIONS.set(Parser.EXTENSIONS, Parser.EXTENSIONS.get(OPTIONS));
    }

    static final Parser PARSER = Parser.builder(OPTIONS).build();
    static final Formatter RENDERER = Formatter.builder(FORMAT_OPTIONS).build();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\Code\\Java\\blog\\src\\main\\java\\com\\lingfly\\blog\\util\\flexmark\\1.md");
        Scanner in = new Scanner(file);
        File file1 = new File("D:\\Code\\Java\\blog\\src\\main\\java\\com\\lingfly\\blog\\util\\flexmark\\index.html");
        PrintWriter out = new PrintWriter(file1);
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.nextLine()).append("\n");
        }
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, List.of(TablesExtension.create()));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(sb.toString());
        Node childOfType = document.getChildOfType(Heading.class);



        VisitHandler<?>[] visitHandlers = TableVisitorExt.VISIT_HANDLERS(new TableVisitor() {
            @Override
            public void visit(TableBlock node) {

            }

            @Override
            public void visit(TableHead node) {

            }

            @Override
            public void visit(TableSeparator node) {

            }

            @Override
            public void visit(TableBody node) {

            }

            @Override
            public void visit(TableRow node) {

            }

            @Override
            public void visit(TableCell node) {

            }

            @Override
            public void visit(TableCaption node) {

            }
        });


        String html = renderer.render(document);

        TextCollectingVisitor visitor = new TextCollectingVisitor();


        Node node = document.getFirstChild();
        while (node != null) {
            if (node instanceof TableBlock t) {
                String s = visitor.collectAndGetText(node);
                System.out.println(s);
            }
            node = node.getNext();
        }
        out.println(html);
        out.close();
    }

}
