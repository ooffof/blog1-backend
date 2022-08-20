package top.cuizilin.website.utils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

public class MarkdownUtils {

   public static String markdownToHTML(String markdown){
       List<Extension> extensions = Arrays.asList(TablesExtension.create());
       Parser parser = Parser.builder()
               .extensions(extensions)
               .build();
       HtmlRenderer renderer = HtmlRenderer.builder()
               .extensions(extensions)
               .build();
       Node document = parser.parse(markdown);
       return renderer.render(document);
   }
}
