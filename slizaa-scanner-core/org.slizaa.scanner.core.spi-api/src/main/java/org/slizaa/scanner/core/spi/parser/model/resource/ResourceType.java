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
package org.slizaa.scanner.core.spi.parser.model.resource;

import org.slizaa.scanner.core.spi.parser.model.Label;

/**
 * <p>
 * Enum that defines the type of a resource (SOURCE or BINARY).
 * </p>
 * 
 * @author Gerd W&uuml;therich (gerd@gerd-wuetherich.de)
 * 
 * @noextend This class is not intended to be extended by clients.
 */
public enum ResourceType implements Label {

	/** source content */
	SOURCE,

	/** binary content */
	BINARY;
}