package org.jsonex.csv;

import lombok.extern.slf4j.Slf4j;
import org.jsonex.core.charsource.ArrayCharSource;
import org.jsonex.core.util.FileUtil;
import org.jsonex.treedoc.TDNode;
import org.junit.Test;

import static org.jsonex.snapshottest.Snapshot.assertMatchesSnapshot;
import static org.junit.Assert.assertEquals;


@Slf4j
public class CSVTest {
  @Test public void testParseAndWriter() {
    TDNode node = CSVParser.get().parse(FileUtil.loadResource(CSVTest.class, "test.csv"));
    assertEquals("[['field1', 'field2', 'field3'], ['v11', 'v12', 'v13'], ['v21', 'v2l1\\nV2l2', 'v23'], ['v31\"v31', 'v32\"\"v32', 'v33']]",
        node.toString());

    CSVOption opt = new CSVOption().setFieldSep('|');
    String str = CSVWriter.get().writeAsString(node, opt);
    assertMatchesSnapshot(str);
    TDNode node1 = CSVParser.get().parse(str, opt);
    assertEquals(node, node1);
  }

  @Test public void testReadField() {
    assertEquals("ab'cd", CSVParser.get().readField(new ArrayCharSource("'ab''cd'"),
        new CSVOption().setQuoteChar('\'')));
  }
}
