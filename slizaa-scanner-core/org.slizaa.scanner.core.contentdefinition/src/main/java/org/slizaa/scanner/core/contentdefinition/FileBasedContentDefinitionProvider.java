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
package org.slizaa.scanner.core.contentdefinition;

import java.io.File;
import java.io.IOException;

import org.ops4j.pax.url.mvn.MavenResolvers;
import org.slizaa.scanner.spi.content.AnalyzeMode;
import org.slizaa.scanner.spi.content.IContentDefinition;
import org.slizaa.scanner.spi.content.IContentDefinitionProvider;

/**
 * <p>
 * Superclass for all implementations of {@link ITempDefinitionProvider}
 * </p>
 * 
 * @author Gerd W&uuml;therich (gerd@gerd-wuetherich.de)
 */
public class FileBasedContentDefinitionProvider extends AbstractContentDefinitionProvider
    implements IContentDefinitionProvider {

  @Override
  public IContentDefinition createFileBasedContentDefinition(String contentName, String contentVersion,
      File[] binaryPaths, File[] sourcePaths, AnalyzeMode analyzeMode) {

    //
    return super.createFileBasedContentDefinition(contentName, contentVersion, binaryPaths, sourcePaths, analyzeMode);
  }

  /**
   * @param progressMonitor
   */
  protected void onInitializeProjectContent() {

    try {
      File resolvedFile = MavenResolvers.createMavenResolver(null, null).resolve("org.mapstruct", "mapstruct-processor",
          null, "jar", "1.2.0.CR2");

      this.createFileBasedContentDefinition("Module1", "1.2.3", new File[] { resolvedFile }, null,
          AnalyzeMode.BINARIES_ONLY);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
