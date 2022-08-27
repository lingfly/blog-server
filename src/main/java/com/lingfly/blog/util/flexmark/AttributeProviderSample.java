package com.lingfly.blog.util.flexmark;

import com.vladsch.flexmark.ast.AutoLink;
import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.AttributeProviderFactory;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.html.MutableAttributes;
import com.vladsch.flexmark.util.misc.Extension;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;import java.util.Collections;
import java.util.List;

public class AttributeProviderSample {
    static class SampleExtension implements HtmlRenderer.HtmlRendererExtension {
        @Override
        public void rendererOptions(@NotNull final MutableDataHolder options) {
            // add any configuration settings to options you want to apply to everything, here
        }

        @Override
        public void extend(final HtmlRenderer.Builder rendererBuilder, @NotNull final String rendererType) {
            rendererBuilder.attributeProviderFactory(SampleAttributeProvider.Factory());
        }

        static SampleExtension create() {
            return new SampleExtension();
        }
    }

    static class SampleAttributeProvider implements AttributeProvider {


        static AttributeProviderFactory Factory() {
            return new IndependentAttributeProviderFactory() {
                @Override
                public @NotNull AttributeProvider apply(@NotNull LinkResolverContext context) {
                    return  new SampleAttributeProvider();
                }

            };
        }

        @Override
        public void setAttributes(@NotNull Node node, @NotNull AttributablePart part, @NotNull MutableAttributes attributes) {
            if (node instanceof AutoLink && part == AttributablePart.LINK) {
                // Put info in custom attribute instead
                attributes.replaceValue("class", "my-autolink-class");
            }
        }
    }

    static String commonMark(String markdown) {
        MutableDataHolder options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, List.of(new Extension[] { AutolinkExtension.create(), SampleExtension.create() }));

        // change soft break to hard break
        options.set(HtmlRenderer.SOFT_BREAK, "<br/>");

        Parser parser = Parser.builder(options).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        final String html = renderer.render(document);
        return html;
    }

    public static void main(String[] args) {
        String html = commonMark("http://github.com/vsch/flexmark-java");
        System.out.println(html); // output: <p><a href="http://github.com/vsch/flexmark-java" class="my-autolink-class">http://github.com/vsch/flexmark-java</a></p>

        html = commonMark("hello\nworld");
        System.out.println(html); // output: <p>hello<br/>world</p>
    }
}
