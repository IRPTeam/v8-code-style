/*******************************************************************************
 * Copyright (C) 2021, 1C-Soft LLC and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     1C-Soft LLC - initial API and implementation
 *******************************************************************************/
package com.e1c.v8codestyle.right.check.itests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IProject;
import org.junit.Before;
import org.junit.Test;

import com._1c.g5.v8.bm.core.IBmObject;
import com._1c.g5.v8.dt.core.platform.IDtProject;
import com._1c.g5.v8.dt.rights.model.ObjectRight;
import com._1c.g5.v8.dt.rights.model.ObjectRights;
import com._1c.g5.v8.dt.rights.model.RoleDescription;
import com._1c.g5.v8.dt.rights.model.util.RightName;
import com._1c.g5.v8.dt.validation.marker.Marker;
import com.e1c.g5.v8.dt.check.settings.CheckUid;
import com.e1c.g5.v8.dt.testing.check.CheckTestBase;
import com.e1c.v8codestyle.right.check.RightInteracitveDelete;
import com.e1c.v8codestyle.right.check.RightInteracitveDeleteMarkedPredefinedData;
import com.e1c.v8codestyle.right.check.RightInteracitveDeletePredefinedData;
import com.e1c.v8codestyle.right.check.RightInteractiveClearDeletionMarkPredefinedData;
import com.e1c.v8codestyle.right.check.RightInteractiveSetDeletionMarkPredefinedData;

/**
 * Tests for all forbidden rights checks:
 *  {@link RightInteracitveDelete},
 *  {@link RightInteracitveDeleteMarkedPredefinedData},
 *  {@link RightInteracitveDeletePredefinedData},
 *  {@link RightInteractiveClearDeletionMarkPredefinedData},
 *  {@link RightInteractiveSetDeletionMarkPredefinedData}.
 *
 * @author Dmitriy Marmyshev
 */
public class RoleRightHasForbiddenTest
    extends CheckTestBase
{

    private static final String CHECK_ID_1 = "right-interactive-delete";

    private static final String CHECK_ID_2 = "right-interactive-delete-marked-predefined-data";

    private static final String CHECK_ID_3 = "right-interactive-delete-predefined-data";

    private static final String CHECK_ID_4 = "right-interactive-clear-deletion-mark-predefined-data";

    private static final String CHECK_ID_5 = "right-interactive-set-deletion-mark-predefined-data";

    private static final String PROJECT_NAME = "RoleRightHasForbidden";

    private static final String FQN_FORBIDDEN_RIGHTS = "Role.ForbiddenRights.Rights";

    private static final String FQN_ALLOWED_RIGHTS = "Role.AllowedRights.Rights";

    private IDtProject dtProject;

    @Override
    protected boolean enableCleanUp()
    {
        return false;
    }

    @Before
    public void initProject() throws Exception
    {
        IProject project = testingWorkspace.getProject(PROJECT_NAME);
        if (project.isAccessible())
        {
            testingWorkspace.waitForBuildCompletion();
            dtProject = dtProjectManager.getDtProject(project);
            assertNotNull(dtProject);
            waitForDD(dtProject);
        }
        else
        {
            dtProject = openProjectAndWaitForValidationFinish(PROJECT_NAME);
        }
        assertNotNull(dtProject);
    }

    /**
     * Test role has forbidden right, check {@link RightInteracitveDelete}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasRightInteracitveDelete() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_FORBIDDEN_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_DELETE);
        assertNotNull(right);

        Marker marker = getFirstMarker(CHECK_ID_1, right, dtProject);
        assertNotNull(marker);

    }

    /**
     * Test role has forbidden right, check {@link RightInteracitveDeleteMarkedPredefinedData}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasRightInteracitveDeleteMarkedPredefinedData() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_FORBIDDEN_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_DELETE_MARKED_PREDEFINED_DATA);
        assertNotNull(right);

        Marker marker = getFirstMarker(CHECK_ID_2, right, dtProject);
        assertNotNull(marker);

    }

    /**
     * Test role has forbidden right, check {@link RightInteracitveDeletePredefinedData}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasRightInteracitveDeletePredefinedData() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_FORBIDDEN_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_DELETE_PREDEFINED_DATA);
        assertNotNull(right);

        Marker marker = getFirstMarker(CHECK_ID_3, right, dtProject);
        assertNotNull(marker);

    }

    /**
     * Test role has forbidden right, check {@link RightInteractiveClearDeletionMarkPredefinedData}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasRightInteractiveClearDeletionMarkPredefinedData() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_FORBIDDEN_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_CLEAR_DELETION_MARK_PREDEFINED_DATA);
        assertNotNull(right);

        Marker marker = getFirstMarker(CHECK_ID_4, right, dtProject);
        assertNotNull(marker);

    }

    /**
     * Test role has forbidden right, check {@link RightInteractiveSetDeletionMarkPredefinedData}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasRightInteractiveSetDeletionMarkPredefinedData() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_FORBIDDEN_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_SET_DELETION_MARK_PREDEFINED_DATA);
        assertNotNull(right);

        Marker marker = getFirstMarker(CHECK_ID_5, right, dtProject);
        assertNotNull(marker);

    }

    /**
     * Test role has no forbidden right, check {@link RightInteracitveDelete}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasNoRightInteracitveDelete() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_ALLOWED_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_DELETE);
        assertNull(right);

        Marker[] markers = markerManager.getNestedMarkers(dtProject.getWorkspaceProject(), top.bmGetId());
        Marker marker = getAnyFirstMarker(CHECK_ID_1, markers);
        assertNull(marker);

    }

    /**
     * Test role has no forbidden right, check {@link RightInteracitveDeleteMarkedPredefinedData}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasNoRightInteracitveDeleteMarkedPredefinedData() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_ALLOWED_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_DELETE_MARKED_PREDEFINED_DATA);
        assertNull(right);

        Marker[] markers = markerManager.getNestedMarkers(dtProject.getWorkspaceProject(), top.bmGetId());
        Marker marker = getAnyFirstMarker(CHECK_ID_2, markers);
        assertNull(marker);

    }

    /**
     * Test role has no forbidden right, check {@link RightInteracitveDeletePredefinedData}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasNoRightInteracitveDeletePredefinedData() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_ALLOWED_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_DELETE_PREDEFINED_DATA);
        assertNull(right);

        Marker[] markers = markerManager.getNestedMarkers(dtProject.getWorkspaceProject(), top.bmGetId());
        Marker marker = getAnyFirstMarker(CHECK_ID_3, markers);
        assertNull(marker);

    }

    /**
     * Test role has no forbidden right, check {@link RightInteractiveClearDeletionMarkPredefinedData}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasNoRightInteractiveClearDeletionMarkPredefinedData() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_ALLOWED_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_CLEAR_DELETION_MARK_PREDEFINED_DATA);
        assertNull(right);

        Marker[] markers = markerManager.getNestedMarkers(dtProject.getWorkspaceProject(), top.bmGetId());
        Marker marker = getAnyFirstMarker(CHECK_ID_4, markers);
        assertNull(marker);

    }

    /**
     * Test role has no forbidden right, check {@link RightInteractiveSetDeletionMarkPredefinedData}
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoleHasNoRightInteractiveSetDeletionMarkPredefinedData() throws Exception
    {
        IBmObject top = getTopObjectByFqn(FQN_ALLOWED_RIGHTS, dtProject);
        assertTrue(top instanceof RoleDescription);
        RoleDescription description = (RoleDescription)top;
        assertEquals(1, description.getRights().size());
        ObjectRights rights = description.getRights().get(0);
        ObjectRight right = getObjectRight(rights, RightName.INTERACTIVE_SET_DELETION_MARK_PREDEFINED_DATA);
        assertNull(right);

        Marker[] markers = markerManager.getNestedMarkers(dtProject.getWorkspaceProject(), top.bmGetId());
        Marker marker = getAnyFirstMarker(CHECK_ID_5, markers);
        assertNull(marker);

    }

    private ObjectRight getObjectRight(ObjectRights rights, RightName name)
    {
        for (ObjectRight right : rights.getRights())
        {
            if (name.getName().equals(right.getRight().getName()))
            {
                return right;
            }

        }
        return null;
    }

    private Marker getAnyFirstMarker(String chekcId, Marker[] markers)
    {
        for (int i = 0; i < markers.length; i++)
        {
            Marker marker = markers[i];
            CheckUid checkUid = checkRepository.getUidForShortUid(marker.getCheckId(), dtProject.getWorkspaceProject());
            if (chekcId.equals(checkUid.getCheckId()))
            {
                return marker;
            }
        }
        return null;
    }

}
