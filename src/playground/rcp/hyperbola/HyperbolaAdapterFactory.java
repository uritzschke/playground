package playground.rcp.hyperbola;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import playground.rcp.hyperbola.model.Contact;
import playground.rcp.hyperbola.model.ContactsEntry;
import playground.rcp.hyperbola.model.ContactsGroup;
import playground.rcp.hyperbola.model.Presence;

public class HyperbolaAdapterFactory implements IAdapterFactory {

	private final IWorkbenchAdapter entryAdapter = new IWorkbenchAdapter() {

		@Override
		public Object[] getChildren(Object o) {
			return new Object[0];
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			// TODO
			final ContactsEntry entry = ((ContactsEntry) object);
			final String key = presenceToKey(entry.getPresence());
			return AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, key);
		}

		@Override
		public String getLabel(Object o) {
			final ContactsEntry entry = ((ContactsEntry) o);
			return entry.getName() + "-" + entry.getServer();
		}

		@Override
		public Object getParent(Object o) {
			return ((ContactsEntry) o).getParent();
		}

	};


	private final IWorkbenchAdapter groupAdapter = new IWorkbenchAdapter() {

		@Override
		public Object[] getChildren(Object o) {
			return (((ContactsGroup) o).getEntries());
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			// TODO Auto-generated method stub
			return AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.GROUP);
		}

		@Override
		public String getLabel(Object o) {
			final ContactsGroup group = ((ContactsGroup) o);
			int available = 0;
			final Contact[] entries = group.getEntries();
			for (final Contact contact : entries) {
				if (contact instanceof ContactsEntry) {
					if (((ContactsEntry) contact).getPresence() != Presence.INVISIBLE) {
						available++;
					}
				}
			}
			return group.getName() + " (" + available + "/" + entries.length + ")";
		}

		@Override
		public Object getParent(Object o) {
			return (((ContactsGroup) o).getParent());
		}

	};

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (adapterType == IWorkbenchAdapter.class && adaptableObject instanceof ContactsGroup) {
			return (T) groupAdapter;
		}

		if (adapterType == IWorkbenchAdapter.class && adaptableObject instanceof ContactsEntry) {
			return (T) entryAdapter;
		}

		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class };
	}

	private String presenceToKey(Presence presence) {
		if (presence == Presence.ONLINE) {
			return IImageKeys.ONLINE;
		} else if (presence == Presence.OFFLINE || presence == Presence.INVISIBLE) {
			return IImageKeys.OFFLINE;
		} else if (presence == Presence.DO_NOT_DISTURB) {
			return IImageKeys.DO_NOT_DISTURB;
		} else if (presence == Presence.AWAY) {
			return IImageKeys.AWAY;
		}
		return null;
	}

}
