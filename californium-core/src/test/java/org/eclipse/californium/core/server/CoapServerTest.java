/*******************************************************************************
 * Copyright (c) 2019 Bosch Software Innovations GmbH and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Bosch Software Innovations - initial creation
 ******************************************************************************/
package org.eclipse.californium.core.server;

import static org.junit.Assert.assertEquals;

import org.eclipse.californium.category.Small;
import org.eclipse.californium.core.CoapServer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Verifies behavior of {@link CoapServer}.
 */
@Category(Small.class)
public class CoapServerTest {

	@Test
	public void testDestroyWithoutStart() {
		CoapServer server = new CoapServer();
		server.destroy();
	}

	@Test
	public void testStartStopDestroy() {
		// look at nb active thread before.
		int numberOfThreadbefore = Thread.activeCount();

		CoapServer server = new CoapServer();
		server.start();
		server.stop();
		server.destroy();

		// ensure all thread are destroyed
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		// +2 because of static ScheduledThreadPoolExecutor from ExecutorsUtil
		int expectedNumberOfThread = numberOfThreadbefore + 2;
		assertEquals("All news threads created must be destroyed", expectedNumberOfThread, Thread.activeCount());
	}
}
