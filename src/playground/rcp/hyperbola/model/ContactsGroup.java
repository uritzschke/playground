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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactsGroup extends Contact {

	private List<Contact> entries;
	private Set<IContactsListener> listeners;
	private String name;
	private ContactsGroup parent;

	public ContactsGroup(ContactsGroup parent, String name) {
		this.name = name;
		this.parent = parent;
	}

	public void addContactsListener(IContactsListener listener) {
		if (parent != null) {
			parent.addContactsListener(listener);
		} else {
			if (listeners == null) {
				listeners = new HashSet<IContactsListener>();
			}
			listeners.add(listener);
		}
	}

	public void addEntry(Contact entry) {
		if (entries == null) {
			entries = new ArrayList<Contact>(5);
		}
		entries.add(entry);
		fireContactsChanged(null);
	}

	protected void fireContactsChanged(ContactsEntry entry) {
		if (parent != null) {
			parent.fireContactsChanged(entry);
		} else {
			if (listeners != null) {
				for (final IContactsListener listener : listeners) {
					listener.contactsChanged(this, entry);
				}
			}
		}
	}

	public Contact[] getEntries() {
		if (entries == null) {
			return new Contact[0];
		}
		return entries.toArray(new Contact[entries.size()]);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ContactsGroup getParent() {
		return parent;
	}

	public void removeContactsListener(IContactsListener listener) {
		if (parent != null) {
			parent.removeContactsListener(listener);
		} else {
			if (listeners != null) {
				listeners.remove(listener);
				if (listeners.isEmpty()) {
					listeners = null;
				}
			}
		}
	}

	public void removeEntry(Contact entry) {
		if (entries != null) {
			entries.remove(entry);
			if (entries.isEmpty()) {
				entries = null;
			}
		}
		fireContactsChanged(null);
	}

	public void rename(String newName) {
		this.name = newName;
		fireContactsChanged(null);
	}

	public void setParent(ContactsGroup parent) {
		if (this.parent != null) {
			throw new IllegalStateException("cannot change parent, once set"); //$NON-NLS-1$
		}
		this.parent = parent;
	}
}
