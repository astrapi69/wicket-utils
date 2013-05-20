package com.nitorcreations.wicket.components.form;

import org.apache.commons.lang3.StringUtils;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.ValidationError;

/**
 * A text field with maximum length. The maximum length defaults to 100 characters.
 *
 * @param <T>
 */
public class MaxLengthTextField<T> extends TextField<T> {
    private static final long serialVersionUID = 3076076534157779121L;

    public static final int DEFAULT_MAX_LENGTH = 100;

    public static final String VALIDATOR_KEY = "MaximumLengthExceeded";

    public static final String ATTRIBUTE_NAME = "maxlength";

    private final int maximumLength;

    public MaxLengthTextField(String id) {
        this(id, null, null, null);
    }

    public MaxLengthTextField(String id, Integer maxLength) {
        this(id, maxLength, null, null);
    }

    public MaxLengthTextField(String id, Class<T> type) {
        this(id, null, null, type);
    }

    public MaxLengthTextField(String id, IModel<T> model) {
        this(id, null, model, null);
    }

    public MaxLengthTextField(String id, IModel<T> model, Class<T> type) {
        this(id, null, model, type);
    }

    public MaxLengthTextField(String id, Integer maxLength, Class<T> type) {
        this(id, maxLength, null, type);
    }

    public MaxLengthTextField(String id, Integer maxLength, IModel<T> model) {
        this(id, maxLength, model, null);
    }

    public MaxLengthTextField(String id, Integer maxLength, IModel<T> model, Class<T> type) {
        super(id, model, type);
        this.maximumLength = maxLength == null ? DEFAULT_MAX_LENGTH : maxLength;
    }

    @Override
    public void validate() {
        super.validate();
        if (StringUtils.isNotEmpty(getInput()) && getInput().length() > getMaximumLength()) {
            error(new ValidationError().addMessageKey(VALIDATOR_KEY).setVariable("maxLength", getMaximumLength()));
        }
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        tag.put(ATTRIBUTE_NAME, maximumLength);
    }

    protected int getMaximumLength() {
        return maximumLength;
    }
}
