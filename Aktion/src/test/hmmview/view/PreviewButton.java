package test.hmmview.view;

import java.io.IOException;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import test.hmmview.model.FileModel;

public class PreviewButton {

	private FileModel model;
	
	public PreviewButton(Button button, FileModel model) {
		this.model = model;
		button.addSelectionListener(new PreviewSelectionListener());
		
		
	}
	
	private class PreviewSelectionListener implements SelectionListener {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			try {
				widgeting(e.widget.getDisplay());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	}
	
	private void widgeting(Display display) throws IOException {
		new Preview(display, model);
	}
}
