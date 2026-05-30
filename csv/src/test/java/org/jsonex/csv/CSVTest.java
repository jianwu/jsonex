package org.jsonex.csv;

import lombok.extern.slf4j.Slf4j;
import org.jsonex.core.charsource.ArrayCharSource;
import org.jsonex.core.charsource.ParseRuntimeException;
import org.jsonex.core.util.FileUtil;
import org.jsonex.treedoc.TDNode;
import org.jsonex.treedoc.json.TDJSONParser;
import org.junit.Test;

import static org.jsonex.snapshottest.Snapshot.assertMatchesSnapshot;
import static org.junit.Assert.assertEquals;


@Slf4j
public class CSVTest {
  private static final String TEST_CSV = """
      field1,field2,field3,field4
      v11,v12,v13,1
      v21, "v2l1,
      V2l2" ,v23,true
      "v31""v31","v32""\""v32",v33,"3"
      v41,v42,v43,
      """;

  private static final String TEST_CSV_1 = """
      v41,v42,v43,
      """;

  private static final String TEST_OBJ = """
      "@key","field1","field2"
      k1,v11,v12
      k2,v21,v22
      """;
  private static final String TEST_CSV_WITH_JSON = """
      val,count,percent
      "[{""kind"":""exact"",""field_path"":""k8s_environment"",""value"":""production""}]",73,0.24333333333333335
      "[{""kind"":""exact"",""field_path"":""k8s_environment"",""value"":""production""},{""kind"":""regex"",""field_path"":""k8s_namespace""}]",61,0.20333333333333334
      """;

  private void testParseAndWrite(CSVOption opt, String csv) {
    TDNode node = CSVParser.get().parse(csv, opt);
    assertMatchesSnapshot("parsed", node.toString());
    String str = CSVWriter.get().writeAsString(node, opt.setFieldSep('|'));
    assertMatchesSnapshot("asString", str);
    TDNode node1 = CSVParser.get().parse(str, opt);
    assertEquals(node, node1);
  }

  @Test public void testParseAndWriteWithoutHeader() {
    testParseAndWrite(new CSVOption().setIncludeHeader(false), TEST_CSV);
  }

  @Test public void testParseAndWriteWithHeader() {
    testParseAndWrite(new CSVOption(), TEST_CSV);
  }

  @Test public void testParseAndWriteWithHeader_1() {
    testParseAndWrite(new CSVOption().setIncludeHeader(false), TEST_CSV_1);
  }


  @Test public void testParseAndWriteObj() {
    testParseAndWrite(new CSVOption(), TEST_OBJ);
  }

  @Test public void testParseAndWriteJson() {
    testParseAndWrite(new CSVOption(), TEST_CSV_WITH_JSON);
  }


  @Test public void testJSONValue() {
    String json = "[{f1: v1, f2: {a: 1, b: 2}}, {f2:'', f3: 3}]";
    assertMatchesSnapshot(CSVWriter.get().writeAsString(TDJSONParser.get().parse(json)));
  }

  @Test public void testReadField() {
    assertEquals("ab'cd", CSVParser.get().readField(new ArrayCharSource("'ab''cd'"),
        new CSVOption().setQuoteChar('\''), null));
  }

  @Test public void testReadFieldMissingQuote() {
    String error = "";
    try {
      CSVParser.get().readField(new ArrayCharSource("'ab''cd"), new CSVOption().setQuoteChar('\''), null);
    } catch (ParseRuntimeException e) {
      error = e.getMessage();
    }
    assertEquals("Can't find matching quote at position:5;line:0;col:5, Bookmark(line=0, col=7, pos=7), digest:", error);
  }
}
