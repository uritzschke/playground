package playground.rcp.hyperbola.views;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddContactDialog extends TitleAreaDialog {

	private Text txtName;
	private Text txtNickname;
	private Text txtServerName;

	private String name;
	private String nickname;
	private String serverText;

	public AddContactDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Create new Contact");
		setMessage("Please enter the details for the new contact", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite area = (Composite) super.createDialogArea(parent);
		final Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		final GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		createName(container);
		createNickname(container);
		createServerText(container);

		return area;

	}

	private void createName(Composite container) {
		final Label lblName = new Label(container, SWT.NONE);
		lblName.setText("Name");
		final GridData dataName = new GridData();
		dataName.grabExcessHorizontalSpace = true;
		dataName.horizontalAlignment = GridData.FILL;

		txtName = new Text(container, SWT.BORDER);
		txtName.setLayoutData(dataName);
	}

	private void createNickname(Composite container) {
		final Label lblNickname = new Label(container, SWT.NONE);
		lblNickname.setText("Nickname");
		final GridData dataNickname = new GridData();
		dataNickname.grabExcessHorizontalSpace = true;
		dataNickname.horizontalAlignment = GridData.FILL;

		txtNickname = new Text(container, SWT.BORDER);
		txtNickname.setLayoutData(dataNickname);
	}

	private void createServerText(Composite container) {
		final Label lblServername = new Label(container, SWT.NONE);
		lblServername.setText("Server");
		final GridData dataServername = new GridData();
		dataServername.grabExcessHorizontalSpace = true;
		dataServername.horizontalAlignment = GridData.FILL;

		txtServerName = new Text(container, SWT.BORDER);
		txtServerName.setLayoutData(dataServername);
	}

	public String getNameText() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public String getServerText() {
		return serverText;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	private void saveInput() {
		name = txtName.getText();
		nickname = txtNickname.getText();
		serverText = txtServerName.getText();
	}

}
