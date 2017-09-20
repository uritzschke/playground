package playground.rcp.hyperbola;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {}

}
