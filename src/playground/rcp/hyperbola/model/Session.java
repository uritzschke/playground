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

public class Session implements ISession {
	private String name;
	private ContactsGroup rootGroup;
	private String server;

	public Session() {
	}

	public String getName() {
		return name;
	}

	@Override
	public ContactsGroup getRoot() {
		if (rootGroup == null) {
			rootGroup = new ContactsGroup(null, "RootGroup"); //$NON-NLS-1$
		}
		return rootGroup;
	}

	public String getServer() {
		return server;
	}

	public void setSessionDescription(String name, String server) {
		this.name = name;
		this.server = server;
	}
}