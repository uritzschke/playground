package playground.rcp.hyperbola.views;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;

import playground.rcp.hyperbola.HyperbolaAdapterFactory;
import playground.rcp.hyperbola.model.Contact;
import playground.rcp.hyperbola.model.ContactsEntry;
import playground.rcp.hyperbola.model.ContactsGroup;
import playground.rcp.hyperbola.model.IContactsListener;
import playground.rcp.hyperbola.model.Session;

public class ContactsView extends ViewPart {

	public static final String ID = "playground.rcp.hyperbola.views.contacts";
	private final IAdapterFactory adapterFactory = new HyperbolaAdapterFactory();
	private Session session;
	private TreeViewer treeViewer;

	public ContactsView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		initializeSession();
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		Platform.getAdapterManager().registerAdapters(adapterFactory, Contact.class);
		getSite().setSelectionProvider(treeViewer);
		treeViewer.setLabelProvider(new WorkbenchLabelProvider());
		treeViewer.setContentProvider(new BaseWorkbenchContentProvider());
		treeViewer.setInput(session.getRoot());
		session.getRoot().addContactsListener(new IContactsListener() {
			@Override
			public void contactsChanged(ContactsGroup contacts, ContactsEntry entry) {
				treeViewer.refresh();
			}
		});

	}

	@Override
	public void dispose() {
		Platform.getAdapterManager().unregisterAdapters(adapterFactory);
		super.dispose();
	}

	private void initializeSession() {
		session = new Session();
		final ContactsGroup root = session.getRoot();
		final ContactsGroup friendsGroup = new ContactsGroup(root, "Friends");
		root.addEntry(friendsGroup);
		friendsGroup.addEntry(new ContactsEntry(friendsGroup, "Alize", "aliz", "localhost"));
		friendsGroup.addEntry(new ContactsEntry(friendsGroup, "Sydney", "syd", "localhost"));
		final ContactsGroup otherGroup = new ContactsGroup(root, "Other");
		root.addEntry(otherGroup);
		otherGroup.addEntry(new ContactsEntry(otherGroup, "Nadine", "nad", "localhost"));
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

}
