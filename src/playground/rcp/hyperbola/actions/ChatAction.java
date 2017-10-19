package playground.rcp.hyperbola.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import playground.rcp.hyperbola.Application;
import playground.rcp.hyperbola.IImageKeys;
import playground.rcp.hyperbola.editor.ChatEditor;
import playground.rcp.hyperbola.editorinput.ChatEditorInput;
import playground.rcp.hyperbola.model.ContactsEntry;

public class ChatAction extends Action implements ISelectionListener, IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "playground.rcp.hyperbola.chat";
	private IStructuredSelection selection;

	public ChatAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Chat");
		setToolTipText("Chat with the selected contact.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.CHAT));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) selection;
			setEnabled(this.selection.size() == 1 && this.selection.getFirstElement() instanceof ContactsEntry);
		} else {
			// other selections
			setEnabled(false);
		}

	}



	@Override
	public String getId() {
		return ID;
	}



	@Override
	public void run() {
		final Object item = selection.getFirstElement();
		final ContactsEntry entry = (ContactsEntry) item;
		final IWorkbenchPage page = window.getActivePage();
		final ChatEditorInput input = new ChatEditorInput(entry.getName());
		try {
			page.openEditor(input, ChatEditor.ID);
		} catch (final PartInitException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

}
