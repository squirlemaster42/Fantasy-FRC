/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.starter.beveragebuddy.ui.views.reviewslist;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.DateRangeValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.starter.beveragebuddy.backend.Category;
import com.vaadin.starter.beveragebuddy.backend.CategoryService;
import com.vaadin.starter.beveragebuddy.backend.Review;
import com.vaadin.starter.beveragebuddy.ui.common.AbstractEditorDialog;

/**
 * A dialog for editing {@link Review} objects.
 */
public class TeamConfirmDialog extends AbstractEditorDialog<Review> {

    private transient CategoryService categoryService = CategoryService
            .getInstance();

    private ComboBox<Category> categoryBox = new ComboBox<>();


    public TeamConfirmDialog(BiConsumer<Review, Operation> saveHandler,
                              Consumer<Review> deleteHandler) {
        super("Team", saveHandler, deleteHandler);


    }


    @Override
    protected void confirmDelete() {
        openConfirmationDialog("Delete Team",
                "Are you sure you want to delete “" + getCurrentItem().getName() + "”?", "");
    }

}
