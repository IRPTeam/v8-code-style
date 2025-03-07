/*******************************************************************************
 * Copyright (C) 2022, 1C-Soft LLC and others.
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
package com.e1c.v8codestyle.bsl.check;

import static com._1c.g5.v8.dt.bsl.model.BslPackage.Literals.FUNCTION_STYLE_CREATOR;
import static com._1c.g5.v8.dt.bsl.model.BslPackage.Literals.OPERATOR_STYLE_CREATOR;
import static com._1c.g5.v8.dt.platform.IEObjectTypeNames.COLOR;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import com._1c.g5.v8.dt.bsl.model.FunctionStyleCreator;
import com._1c.g5.v8.dt.bsl.model.OperatorStyleCreator;
import com._1c.g5.v8.dt.mcore.Type;
import com._1c.g5.v8.dt.mcore.TypeItem;
import com._1c.g5.v8.dt.mcore.util.McoreUtil;
import com.e1c.g5.v8.dt.check.CheckComplexity;
import com.e1c.g5.v8.dt.check.ICheckParameters;
import com.e1c.g5.v8.dt.check.components.BasicCheck;
import com.e1c.g5.v8.dt.check.settings.IssueSeverity;
import com.e1c.g5.v8.dt.check.settings.IssueType;
import com.e1c.v8codestyle.check.StandardCheckExtension;
import com.e1c.v8codestyle.internal.bsl.BslPlugin;

/**
 * Checks use constructor New Color.
 *
 * @author Artem Iliukhin
 */
public final class NewColorCheck
    extends BasicCheck
{

    private static final String CHECK_ID = "new-color"; //$NON-NLS-1$

    @Override
    public String getCheckId()
    {
        return CHECK_ID;
    }

    @Override
    protected void configureCheck(CheckConfigurer builder)
    {
        builder.title(Messages.NewColorCheck_Using_new_color)
            .description(Messages.NewColorCheck_Use_style_elements_not_specific_values)
            .complexity(CheckComplexity.NORMAL)
            .severity(IssueSeverity.MINOR)
            .issueType(IssueType.WARNING)
            .extension(new StandardCheckExtension(getCheckId(), BslPlugin.PLUGIN_ID))
            .module()
            .checkedObjectType(OPERATOR_STYLE_CREATOR)
            .checkedObjectType(FUNCTION_STYLE_CREATOR);
    }

    @Override
    protected void check(Object object, ResultAcceptor resultAceptor, ICheckParameters parameters,
        IProgressMonitor monitor)
    {
        if (object instanceof OperatorStyleCreator)
        {
            Type type = ((OperatorStyleCreator)object).getType();
            addResultAceptor(object, resultAceptor, type);
        }
        else if (object instanceof FunctionStyleCreator)
        {
            List<TypeItem> types = ((FunctionStyleCreator)object).getTypes();
            if (!types.isEmpty())
            {
                TypeItem type = types.get(0);
                addResultAceptor(object, resultAceptor, type);
            }
        }
    }

    private void addResultAceptor(Object object, ResultAcceptor resultAceptor, TypeItem type)
    {
        String name = McoreUtil.getTypeName(type);
        if (COLOR.equalsIgnoreCase(name))
        {
            resultAceptor.addIssue(Messages.NewColorCheck_Use_style_elements, object);
        }
    }
}
