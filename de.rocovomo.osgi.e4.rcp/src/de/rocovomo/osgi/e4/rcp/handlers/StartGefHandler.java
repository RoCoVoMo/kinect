/*******************************************************************************
 * Copyright (c) 2012 jnect.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eugen Neufeld - initial API and implementation
 *******************************************************************************/
package de.rocovomo.osgi.e4.rcp.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.jnect.core.KinectManager;

import de.rocovomo.osgi.e4.rcp.editor.HumanDiagramGraphicalEditor;

public class StartGefHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("starting Kinect..");
		KinectManager.INSTANCE.startSkeletonTracking();

		((HumanDiagramGraphicalEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor())
				.setContent(KinectManager.INSTANCE.getSkeletonModel());

		return null;
	}

}
