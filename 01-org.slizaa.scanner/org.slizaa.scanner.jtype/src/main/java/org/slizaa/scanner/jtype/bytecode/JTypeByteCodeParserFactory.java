/*******************************************************************************
 * Copyright (c) 2011-2015 Slizaa project team.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Slizaa project team - initial API and implementation
 ******************************************************************************/
package org.slizaa.scanner.jtype.bytecode;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.slizaa.scanner.jtype.model.internal.bytecode.PostProcessor;
import org.slizaa.scanner.jtype.model.internal.primitvedatatypes.IPrimitiveDatatypeNodeProvider;
import org.slizaa.scanner.jtype.model.internal.primitvedatatypes.PrimitiveDatatypeNodeProvider;
import org.slizaa.scanner.spi.content.IContentDefinition;
import org.slizaa.scanner.spi.content.IContentDefinitions;
import org.slizaa.scanner.spi.parser.IParser;
import org.slizaa.scanner.spi.parser.IParserFactory;

/**
 * <p>
 * The {@link IParserFactory} to create instances of {@link JTypeByteCodeParser}.
 * </p>
 * 
 * @author Gerd W&uuml;therich (gerd@gerd-wuetherich.de)
 */
public class JTypeByteCodeParserFactory extends IParserFactory.Adapter implements IParserFactory {

  /** - */
  Map<IContentDefinitions, IPrimitiveDatatypeNodeProvider> _datatypeNodeProviderMap;

  /**
   * <p>
   * Creates a new instance of type {@link JTypeByteCodeParserFactory}.
   * </p>
   */
  public JTypeByteCodeParserFactory() {

    //
    _datatypeNodeProviderMap = new HashMap<>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IParser createParser(IContentDefinitions contentDefinition) {
    return new JTypeByteCodeParser(this, _datatypeNodeProviderMap.get(contentDefinition));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void batchParseStart(IContentDefinitions contentDefinitions, Object graphDatabase, IProgressMonitor subMonitor)
      throws Exception {

    //
    synchronized (_datatypeNodeProviderMap) {
      if (!_datatypeNodeProviderMap.containsKey(contentDefinitions)) {
        _datatypeNodeProviderMap.put(contentDefinitions,
            new PrimitiveDatatypeNodeProvider((GraphDatabaseService) graphDatabase));
      }
    }
  }
  
  @Override
  public void batchParseStartContentDefinition(IContentDefinition contentDefinition) throws Exception {
    // TODO Auto-generated method stub
    super.batchParseStartContentDefinition(contentDefinition);
  }

  @Override
  public void batchParseStopContentDefinition(IContentDefinition contentDefinition) throws Exception {
    // TODO Auto-generated method stub
    super.batchParseStopContentDefinition(contentDefinition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void batchParseStop(IContentDefinitions contentDefinition, Object graphDatabase, IProgressMonitor subMonitor) {

    //
    ((GraphDatabaseService) graphDatabase).execute("MATCH (n:DIRECTORY)-[:CONTAINS*]->(t:PACKAGE) set n :PACKAGE ");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void beforeDeleteResourceNode(final Object node) {

    //
    PostProcessor.deleteAllJTypeRelatedNodesForResourceNode((Node) node);
  }
}
