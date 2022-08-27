package com.lingfly.blog.util.flexmark;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ext.tables.*;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;

@SuppressWarnings({ "unchecked", "WeakerAccess" })
public class VisitorSample {
    int wordCount;

    // example of visitor for a node or nodes, just add VisitHandlers<> to the list
    // any node type not handled by the visitor will default to visiting its children
    NodeVisitor visitor = new NodeVisitor(
            new VisitHandler<>(Text.class, this::visit)
    );


    public void visit(Text text) {
        // This is called for all Text nodes. Override other visit handlers for other node types.
        wordCount += text.getChars().unescape().split("\\W+").length;

        // Descending into children
        visitor.visitChildren(text);

        // Count words (this is just an example, don't actually do it this way for various reasons).
    }

    void countWords() {
        Parser parser = Parser.builder().build();
        Node document = parser.parse("Example\n=======\n\nSome more text");
        visitor.visit(document);

        System.out.println(wordCount);  // 4
    }

    public static void main(String[] args) {

    }
}