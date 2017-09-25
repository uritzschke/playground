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
 * See http://eclipsercp.org
 *
 * Contributors:
 *     Jean-Michel Lemieux and Jeff McAffer - initial implementation
 *******************************************************************************/
package playground.rcp.hyperbola.model;

public class ContactsEntry extends Contact {

	private final ContactsGroup group;
	private final String name;
	private final String nickname;
	private Presence presence;
	private final String server;

	public ContactsEntry(ContactsGroup group, String name, String nickname,
			String server) {
		this.group = group;
		this.name = name;
		this.nickname = nickname;
		this.server = server;
		this.presence = Presence.OFFLINE;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	@Override
	public ContactsGroup getParent() {
		return group;
	}

	public Presence getPresence() {
		return presence;
	}

	public String getServer() {
		return server;
	}

	public void setPresence(Presence presence) {
		this.presence = presence;
		group.fireContactsChanged(this);
	}
}
