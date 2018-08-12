package com.simple_bot;

import net.sf.saxon.xqj.SaxonXQDataSource;
import org.apache.commons.io.IOUtils;

import javax.xml.xquery.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public interface AbstractXQueryTest {
    default void checkEvaluate(String path) throws IOException, XQException {
        assertEquals(evaluateExpression(path), getResult(path));
    }

    default String getResult(String path) throws IOException {
        return replaceLineBreak(IOUtils.toString(this.getClass().getResourceAsStream(path + ".txt")));
    }

    default String evaluateExpression(String path) throws XQException, IOException {
        XQDataSource ds = new SaxonXQDataSource();
        XQConnection conn = ds.getConnection();
        String query = IOUtils.toString(this.getClass().getResourceAsStream(path + ".xq"));
        query = query.replaceAll("FILE", this.getClass().getResource(path + ".xml").getPath());
        XQPreparedExpression exp = conn.prepareExpression(query);
        XQResultSequence result = exp.executeQuery();

        StringBuilder sb = new StringBuilder();
        while (result.next()) {
            sb.append(result.getItemAsString(null)).append("\n");
        }
        return replaceLineBreak(sb.toString());
    }

    default String replaceLineBreak(String str) {
        return str.replaceAll("\r\n", "").replaceAll("\n", "");
    }
}
