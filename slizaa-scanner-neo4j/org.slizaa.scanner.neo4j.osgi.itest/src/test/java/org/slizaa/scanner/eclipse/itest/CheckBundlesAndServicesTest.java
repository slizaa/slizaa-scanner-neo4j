/*******************************************************************************
 * Copyright (C) 2017 wuetherich
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.slizaa.scanner.eclipse.itest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.osgi.framework.BundleException;
import org.slizaa.scanner.core.api.graphdb.IGraphDbFactory;
import org.slizaa.scanner.core.api.importer.IModelImporterFactory;

public class CheckBundlesAndServicesTest extends AbstractEclipseTest {

  /**
   * <p>
   * </p>
   * 
   * @throws BundleException
   */
  @Test
  public void testBundleAndServices() throws BundleException {

    // checkStart
    startAllBundles();

    // checkServices
    assertThat(bundleContext().getServiceReference(IModelImporterFactory.class)).isNotNull();
    assertThat(bundleContext().getServiceReference(IGraphDbFactory.class)).isNotNull();
  }
}
