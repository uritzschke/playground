package playground.rcp.hyperbola;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import playground.rcp.hyperbola.views.ContactsView;

public class Perspective implements IPerspectiveFactory {

	public static final String PERSPECTIVE_ID = "playground.rcp.hyperbola.perspective"; //$NON-NLS-1$
	
	@Override	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(ContactsView.ID, false, IPageLayout.LEFT, 1.0f, layout.getEditorArea());
	}
}
