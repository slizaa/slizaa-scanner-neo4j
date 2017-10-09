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
package org.slizaa.scanner.core.api.importer;

import java.io.File;

import org.slizaa.scanner.core.spi.contentdefinition.IContentDefinitionProvider;
import org.slizaa.scanner.core.spi.parser.IParserFactory;

/**
 * <p>
 * </p>
 * 
 * @author Gerd W&uuml;therich (gerd@gerd-wuetherich.de)
 */
public interface IModelImporterFactory {

  /**
   * <p>
   * </p>
   * 
   * @return
   */
  IParserFactory[] getAllParserFactories();

  /**
   * <p>
   * Creates a new {@link IModelImporter} for the specified {@link IContentDefinitionProvider}.
   * </p>
   * 
   * @param systemDefinition
   *          the system definition that should be analyzed
   * @param databaseDirectory
   *          the directory where the neo4j database should be stored
   * @param parserFactories
   *          the parser factories that provide the parsers that should be used while analyzing the defined system
   * @return a new {@link IModelImporter} instance
   */
  IModelImporter createModelImporter(IContentDefinitionProvider systemDefinition, File databaseDirectory,
      IParserFactory... parserFactories);
}
