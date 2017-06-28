package org.slizaa.scanner.importer.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class LRCache {

  /** the instance */
  private static LRCache                         _instance              = new LRCache();

  /** - */
  private LoadingCache<String, Label>            _labelCache            = CacheBuilder.newBuilder()
      .build(new CacheLoader<String, Label>() {
                                                                              public Label load(String key) {
                                                                                return Label.label(key);
                                                                              }
                                                                            });

  /** - */
  private LoadingCache<String, RelationshipType> _relationshipTypeCache = CacheBuilder.newBuilder()
      .build(new CacheLoader<String, RelationshipType>() {
                                                                              public RelationshipType load(String key) {
                                                                                return RelationshipType.withName(key);
                                                                              }
                                                                            });

  public static LRCache instance() {
    return _instance;
  }

  public static Label convert(org.slizaa.scanner.model.Label slizaaLabel) {
    return instance().getLabel(slizaaLabel);
  }

  /**
   * <p>
   * </p>
   *
   * @param slizaaLabels
   * @return
   */
  public static Label[] convert(org.slizaa.scanner.model.Label[] slizaaLabels) {

    //
    Label[] result = new Label[checkNotNull(slizaaLabels.length)];

    //
    for (int i = 0; i < slizaaLabels.length; i++) {
      result[i] = convert(slizaaLabels[i]);
    }

    //
    return result;
  }

  public static RelationshipType convert(org.slizaa.scanner.model.RelationshipType relationshipType) {
    return instance().getRelationship(relationshipType);
  }

  /**
   * <p>
   * </p>
   *
   * @param slizaaLabel
   * @return
   */
  public Label getLabel(org.slizaa.scanner.model.Label slizaaLabel) {
    return _labelCache.getUnchecked(checkNotNull(slizaaLabel.name()));
  }

  public RelationshipType getRelationship(org.slizaa.scanner.model.RelationshipType relationshipType) {
    return _relationshipTypeCache.getUnchecked(checkNotNull(relationshipType.name()));
  }
}
