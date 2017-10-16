package org.slizaa.scanner.core.spi.contentdefinition.filebased;

import static org.slizaa.scanner.core.spi.internal.Preconditions.checkNotNull;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.slizaa.scanner.core.spi.contentdefinition.AnalyzeMode;
import org.slizaa.scanner.core.spi.contentdefinition.ContentType;
import org.slizaa.scanner.core.spi.contentdefinition.IContentDefinition;
import org.slizaa.scanner.core.spi.contentdefinition.filebased.internal.FileBasedContentDefinition;

public class FileBasedContentDefinitionFactory {

  /**
   * <p>
   * </p>
   * 
   * @param contentName
   * @param contentVersion
   * @param binaryPaths
   * @param sourcePaths
   * @param analyzeMode
   * @return
   * @throws CoreException
   */
  public static IContentDefinition createFileBasedContentDefinition(String contentName, String contentVersion,
      File[] binaryPaths, File[] sourcePaths, AnalyzeMode analyzeMode) {

    // asserts
    checkNotNull(contentName);
    checkNotNull(contentVersion);
    checkNotNull(binaryPaths);
    checkNotNull(analyzeMode);

    FileBasedContentDefinition result = new FileBasedContentDefinition();

    result.setAnalyzeMode(analyzeMode);
    result.setName(contentName);
    result.setVersion(contentVersion);

    for (File binaryPath : binaryPaths) {
      result.addRootPath(binaryPath, ContentType.BINARY);
    }

    if (sourcePaths != null) {
      for (File sourcePath : sourcePaths) {
        result.addRootPath(sourcePath, ContentType.SOURCE);
      }
    }

    // initialize the result
    result.initialize();

    //
    return result;
  }
}