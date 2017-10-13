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
package org.slizaa.scanner.core.impl.importer;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.util.function.Supplier;

import org.slizaa.scanner.core.api.importer.IModelImporter;
import org.slizaa.scanner.core.api.importer.IModelImporterFactory;
import org.slizaa.scanner.core.impl.importer.internal.parser.ModelImporter;
import org.slizaa.scanner.core.spi.contentdefinition.IContentDefinitionProvider;
import org.slizaa.scanner.core.spi.parser.IParserFactory;

/**
 * <p>
 * </p>
 * 
 * @author Gerd W&uuml;therich (gerd@gerd-wuetherich.de)
 */
public class ModelImporterFactory implements IModelImporterFactory {

  /** - */
  private Supplier<IParserFactory[]> _parserFactorySupplier;

  /**
   * <p>
   * </p>
   * 
   * @param parserFactorySupplier
   */
  public ModelImporterFactory(Supplier<IParserFactory[]> parserFactorySupplier) {
    _parserFactorySupplier = checkNotNull(parserFactorySupplier);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IParserFactory[] getAllParserFactories() {
    return _parserFactorySupplier.get();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IModelImporter createModelImporter(IContentDefinitionProvider systemDefniition, File databaseDirectory,
      IParserFactory... parserFactories) {

    //
    return new ModelImporter(systemDefniition, databaseDirectory, parserFactories);
  }

  @Override
  public IModelImporter createModelImporterWithAllParserFactories(IContentDefinitionProvider systemDefinition,
      File databaseDirectory) {

    //
    return new ModelImporter(systemDefinition, databaseDirectory, getAllParserFactories());
  }

}
