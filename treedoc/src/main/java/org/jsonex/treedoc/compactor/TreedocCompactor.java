package org.jsonex.treedoc.compactor;

import org.jsonex.treedoc.TDNode;
import org.jsonex.treedoc.TreeDoc;
import org.jsonex.treedoc.schema.Schema;

import java.util.Map;

/**
 * Treedoc Compactor compacts a collections of Treedocs by externalizing common schemas.
 * <p>
 * Store those schemas in a dictionary. The schema is flattened to keep only a single layer per schema.
 * Complex hierarchical schema is organized by cross-referencing from a parent schema to child schemas
 * so that we maximized the schema re-usability
 * <p>
 * The schema will also store the value statistic information. For low cardinality values such as enum values,
 * we will use this statistic information as dictionary. The actual record will only store the index order only which
 * future compact the object size
 *
 * <p>
 * Schema extraction will also merge similar schemas toa void high number of schemas because of optional fields
 * <P>
 * The schema plus the statistic information will also serve as index help information retrieval to quickly
 * determine if the collection data contains particular value for particular key.
 *
 * <p>
 * by extract schema, we will avoid storing key names repeatedly. For individual records, we will only
 * store the data organized as hierarchical value arrays.
 * <p>
 * On additional compaction it will do is to dedupe the redundant value/objects using reference within a single
 * record.
 * <p>
 * The process will need to two pass the collection data. First path to build the schema dictionary.
 * Second parse will do the actual encoding of the data
 */
public class TreedocCompactor {
  // Store the schema and reference count (root reference only), when we encode the schema, we will order them by the frequency
  Map<Schema, int[]> schemaCount;

  public TreedocCompactor updateSchema(TreeDoc treeDoc) {
    treeDoc.getRoot().foreach(TDNode)
    return this;
  }
}
