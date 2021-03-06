package playground.rcp.hyperbola;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import playground.rcp.hyperbola.actions.AddContactAction;
import playground.rcp.hyperbola.actions.ChatAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private IWorkbenchAction addContactAction;
	private IWorkbenchAction chatAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		final IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
		coolBar.add(toolbar);
		toolbar.add(addContactAction);
		toolbar.add(chatAction);
	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		final MenuManager hyperbolaMenu = new MenuManager("&Hyperbola", "hyperbola");
		hyperbolaMenu.add(addContactAction);
		hyperbolaMenu.add(new Separator());
		hyperbolaMenu.add(chatAction);
		hyperbolaMenu.add(new Separator());
		hyperbolaMenu.add(exitAction);
		final MenuManager helpMenu = new MenuManager("&Help", "help");
		helpMenu.add(aboutAction);
		menuBar.add(hyperbolaMenu);
		menuBar.add(helpMenu);
	}

	protected void fillTrayItem(IMenuManager trayItem) {
		trayItem.add(aboutAction);
		trayItem.add(exitAction);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
		addContactAction = new AddContactAction(window);
		register(addContactAction);
		chatAction = new ChatAction(window);
		register(chatAction);
	}

}
