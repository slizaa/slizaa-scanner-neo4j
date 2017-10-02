package org.slizaa.scanner.core.contentdefinition;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slizaa.scanner.core.contentdefinition.utils.NameAndVersionInfo;
import org.slizaa.scanner.core.spi.contentdefinition.AbstractContentDefinitionProvider;
import org.slizaa.scanner.core.spi.contentdefinition.AnalyzeMode;
import org.slizaa.scanner.core.spi.contentdefinition.IContentDefinitionProvider;

public class DirectoryBasedContentDefinitionProvider extends AbstractContentDefinitionProvider
    implements IContentDefinitionProvider {

  /** - */
  private List<File> _directoriesWithBinaryArtifacts;

  /**
   * <p>
   * Creates a new instance of type {@link DirectoryBasedContentDefinitionProvider}.
   * </p>
   */
  public DirectoryBasedContentDefinitionProvider() {
    _directoriesWithBinaryArtifacts = new ArrayList<>();
  }

  
  
  public boolean add(File e) {
    return _directoriesWithBinaryArtifacts.add(e);
  }



  public boolean addAll(Collection<? extends File> c) {
    return _directoriesWithBinaryArtifacts.addAll(c);
  }



  /**
   * @param progressMonitor
   */
  protected void onInitializeProjectContent() {

    // collect dirs
    for (File directories : _directoriesWithBinaryArtifacts) {

      //
      for (File artifact : collectJars(directories)) {

        NameAndVersionInfo info = NameAndVersionInfo.resolveNameAndVersion(artifact);

        if (!info.isSource()) {

          this.createFileBasedContentDefinition(info.getName(), info.getVersion(), new File[] { artifact }, null,
              AnalyzeMode.BINARIES_ONLY);
        }
      }
    }
  }

  /**
   * <p>
   * </p>
   *
   * @param directory
   * @return
   */
  private List<File> collectJars(File directory) {

    // path
    Path path = directory.toPath();

    // create result
    final List<File> result = new ArrayList<>();

    //
    try {
      Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          if (!attrs.isDirectory()) {
            result.add(file.toFile());
          }
          return FileVisitResult.CONTINUE;
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    //
    return result;
  }
}
