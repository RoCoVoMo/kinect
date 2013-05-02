/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;

import test.hmmview.deprecated.View;
import test.hmmview.deprecated.model.BooleanModel;
import test.hmmview.deprecated.model.DoubleModel;
import test.hmmview.deprecated.model.FileModel;
import test.hmmview.deprecated.model.GestureTypeModel;
import test.hmmview.deprecated.model.IntegerModel;

public class Control
{
	public Control(Composite parent)
	{
		final BooleanModel isRunnableModel = new BooleanModel();
		final BooleanModel isSpeechEnabledModel = new BooleanModel();
		final IntegerModel statesModel = new IntegerModel();
		final DoubleModel intervalModel = new DoubleModel();
		final DoubleModel frameModel = new DoubleModel();

		final FileModel fileModel = new FileModel();

		try
		{
			// URL url = new URL(
			// "platform:/plugin/de.rocovomo.e4.rcp.hmmrecorder/data/defaultdata.stream");

			Bundle bundle = Platform.getBundle("de.rocovomo.e4.rcp.hmmrecorder");
			URL fileURL = bundle.getEntry("data/defaultdata.stream");
			File file = new File(FileLocator.resolve(fileURL).toURI());

			fileModel.property.setValue(file);

			final GestureTypeModel gestureTypeModel = new GestureTypeModel();

			View view = new View(parent, isRunnableModel, isSpeechEnabledModel,
					statesModel, intervalModel, frameModel, fileModel, gestureTypeModel);
			view.createUI(parent);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Control(Composite parent, File file)
	{
		final BooleanModel isRunnableModel = new BooleanModel();
		final BooleanModel isSpeechEnabledModel = new BooleanModel();
		final IntegerModel statesModel = new IntegerModel();
		final DoubleModel intervalModel = new DoubleModel();
		final DoubleModel frameModel = new DoubleModel();

		final FileModel fileModel = new FileModel();

		fileModel.property.setValue(file);

		final GestureTypeModel gestureTypeModel = new GestureTypeModel();

		View view = new View(parent, isRunnableModel, isSpeechEnabledModel, statesModel,
				intervalModel, frameModel, fileModel, gestureTypeModel);
		view.createUI(parent);

	}
}
