/*******************************************************************************
 * Copyright (c) 2004, 2005 Jean-Michel Lemieux, Jeff McAffer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Hyperbola is an RCP application developed for the book
 *     Eclipse Rich Client Platform -
 *         Designing, Coding, and Packaging Java Applications
 *
 * Contributors:
 *     Jean-Michel Lemieux and Jeff McAffer - initial implementation
 *******************************************************************************/
package playground.rcp.hyperbola.model;

public class Presence {
	public static final Presence AWAY = new Presence("Away"); //$NON-NLS-1$
	public static final Presence DO_NOT_DISTURB = new Presence("Do Not Disturb"); //$NON-NLS-1$
	public static final Presence INVISIBLE = new Presence("INVISIBLE"); //$NON-NLS-1$
	public static final Presence OFFLINE = new Presence("Offline"); //$NON-NLS-1$
	public static final Presence ONLINE = new Presence("Online"); //$NON-NLS-1$
	private final String value;

	private Presence(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
