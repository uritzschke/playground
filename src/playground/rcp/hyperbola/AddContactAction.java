package playground.rcp.hyperbola;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import playground.rcp.hyperbola.model.ContactsEntry;
import playground.rcp.hyperbola.model.ContactsGroup;
import playground.rcp.hyperbola.views.AddContactDialog;

public class AddContactAction extends Action implements ISelectionListener, IWorkbenchAction {

	public final static String ID = "playground.rcp.hyperbola.addContact";
	private final IWorkbenchWindow window;
	private IStructuredSelection selection;

	public AddContactAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Add Contact...");
		setToolTipText("Add a contact to your contacts list");
		setImageDescriptor(
				AbstractUIPlugin.imageDescriptorFromPlugin("playground.rcp.hyperbola", IImageKeys.ADD_CONTACT));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	@Override
	public void run() {
		final AddContactDialog d = new AddContactDialog(window.getShell());
		final int code = d.open();
		if (code == Window.OK) {
			final Object item = selection.getFirstElement();
			final ContactsGroup group = (ContactsGroup) item;
			final ContactsEntry entry = new ContactsEntry(group, d.getNameText(), d.getNickname(), d.getServerText());
			group.addEntry(entry);
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		// Selection containing elements
		if (incoming instanceof IStructuredSelection) {
			selection = (IStructuredSelection) incoming;
			setEnabled(selection.size() == 1 && selection.getFirstElement() instanceof ContactsGroup);
		} else {
			// Other selections (e.g. containing text or of other kinds
			setEnabled(false);
		}
	}

}
