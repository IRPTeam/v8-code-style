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
package com.e1c.v8codestyle.bsl.strict.check;

import static com.e1c.v8codestyle.bsl.strict.check.StrictTypeAnnotationCheckExtension.PARAM_CHECK_ANNOTATION_IN_MODULE_DESCRIPTION;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IProgressMonitor;

import com._1c.g5.v8.dt.bsl.documentation.comment.BslDocumentationComment;
import com._1c.g5.v8.dt.bsl.documentation.comment.IDescriptionPart;
import com._1c.g5.v8.dt.bsl.documentation.comment.TypeSection.FieldDefinition;
import com.e1c.g5.v8.dt.check.CheckComplexity;
import com.e1c.g5.v8.dt.check.ICheckParameters;
import com.e1c.g5.v8.dt.check.settings.IssueSeverity;
import com.e1c.g5.v8.dt.check.settings.IssueType;
import com.e1c.v8codestyle.bsl.comment.check.FieldDefinitionTypeCheck;
import com.e1c.v8codestyle.bsl.strict.StrictTypeUtil;

/**
 * Checks the documentation comment {@link FieldDefinition field} that has section with types definition.
 * By default it not respect {@code //@strict-types} annotation in module header.
 *
 * @author Dmitriy Marmyshev
 */
public class DocCommentFieldTypeCheck
    extends FieldDefinitionTypeCheck
{

    private static final String CHECK_ID = "doc-comment-field-type-strict"; //$NON-NLS-1$

    @Override
    public String getCheckId()
    {
        return CHECK_ID;
    }

    @Override
    protected void configureCheck(CheckConfigurer builder)
    {
        builder.title(Messages.DocCommentFieldTypeCheck_title)
            .description(Messages.DocCommentFieldTypeCheck_description)
            .complexity(CheckComplexity.NORMAL)
            .severity(IssueSeverity.MAJOR)
            .issueType(IssueType.CODE_STYLE)
            .delegate(FieldDefinition.class);
        builder.parameter(PARAM_CHECK_ANNOTATION_IN_MODULE_DESCRIPTION, Boolean.class, Boolean.FALSE.toString(),
            Messages.StrictTypeAnnotationCheckExtension_Check__strict_types_annotation_in_module_desctioption);

    }

    @Override
    protected void checkDocumentationCommentObject(IDescriptionPart object, BslDocumentationComment root,
        DocumentationCommentResultAcceptor resultAceptor, ICheckParameters parameters, IProgressMonitor monitor)
    {
        if (monitor.isCanceled() || parameters.getBoolean(PARAM_CHECK_ANNOTATION_IN_MODULE_DESCRIPTION)
            && !StrictTypeUtil.hasStrictTypeAnnotation(root.getModule()))
        {
            return;
        }

        FieldDefinition fieldDef = (FieldDefinition)object;

        if (isFieldTypeEmpty(fieldDef))
        {
            String message = MessageFormat.format(Messages.DocCommentFieldTypeCheck_Field__N__has_no_type_definition,
                fieldDef.getName());

            resultAceptor.addIssue(message, fieldDef.getName().length());
        }
    }

}
