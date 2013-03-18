package test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GridLayoutSimpleDmo {
  public static void main(String[] args) {
    Display display = new Display();
    final Shell shell = new Shell(display);

    GridLayout gridLayout = new GridLayout(4, false);
    gridLayout.verticalSpacing = 8;

    shell.setLayout(gridLayout);

    Label label = new Label(shell, SWT.NULL);
    label.setText("Title: ");

    Text title = new Text(shell, SWT.SINGLE | SWT.BORDER);
    GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
    gridData.horizontalSpan = 3;
    title.setLayoutData(gridData);

    label = new Label(shell, SWT.NULL);
    label.setText("Author(s): ");

    Text authors = new Text(shell, SWT.SINGLE | SWT.BORDER);
    gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
    gridData.horizontalSpan = 3;
    authors.setLayoutData(gridData);

    label = new Label(shell, SWT.NULL);
    label.setText("Cover: ");

    gridData = new GridData();
    gridData.verticalSpan = 3;
    label.setLayoutData(gridData);

    CLabel cover = new CLabel(shell, SWT.NULL);

    gridData = new GridData(GridData.FILL_HORIZONTAL);
    gridData.horizontalSpan = 1;
    gridData.verticalSpan = 3;
    gridData.heightHint = 100;
    gridData.widthHint = 100;

    cover.setLayoutData(gridData);

    label = new Label(shell, SWT.NULL);
    label.setText("Pages");

    Text pages = new Text(shell, SWT.SINGLE | SWT.BORDER);
    pages.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

    label = new Label(shell, SWT.NULL);
    label.setText("Publisher");

    Text publisher = new Text(shell, SWT.SINGLE | SWT.BORDER);
    publisher.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

    label = new Label(shell, SWT.NULL);
    label.setText("Rating");

    Combo rating = new Combo(shell, SWT.READ_ONLY);
    rating.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
    rating.add("5");
    rating.add("4");
    rating.add("3");
    rating.add("2");
    rating.add("1");

    label = new Label(shell, SWT.NULL);
    label.setText("Abstract:");

    Text bookAbstract = new Text(shell, SWT.WRAP | SWT.MULTI | SWT.BORDER | SWT.H_SCROLL
        | SWT.V_SCROLL);
    gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
    gridData.horizontalSpan = 3;
    gridData.grabExcessVerticalSpace = true;

    bookAbstract.setLayoutData(gridData);

    Button enter = new Button(shell, SWT.PUSH);
    enter.setText("Enter");

    gridData = new GridData();
    gridData.horizontalSpan = 4;
    gridData.horizontalAlignment = GridData.END;
    enter.setLayoutData(gridData);

    title.setText("Professional Java Interfaces with SWT/JFace");
    authors.setText("Jack Li Guojie");
    pages.setText("500pp");
    publisher.setText("John Wiley & Sons");
//    cover.setBackground(new Image(display, "yourFile.gif"));
    bookAbstract.setText("SWT/JFace. ");

    shell.setSize(450, 400);
    shell.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}