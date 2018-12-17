package com.vaadin.starter.beveragebuddy.ui.views.categorieslist;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.starter.beveragebuddy.backend.User;
import com.vaadin.starter.beveragebuddy.backend.CategoryService;
import com.vaadin.starter.beveragebuddy.backend.ReviewService;
import com.vaadin.starter.beveragebuddy.ui.common.AbstractEditorDialog;

/**
 * A dialog for editing {@link User} objects.
 */
public class Login extends AbstractEditorDialog<User> {

    private final TextField UsernameField = new TextField("Username");
    private final TextField passwordField = new TextField("Password");

    public Login(BiConsumer<User, Operation> itemSaver,
                 Consumer<User> itemDeleter) {
        super("user", itemSaver, itemDeleter);

        addNameField();
        addPasswordField();

    }

    private void addNameField() {
        getFormLayout().add(UsernameField);

        getBinder().forField(UsernameField)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "username must contain at least 3 printable characters",
                        3, null))
                .withValidator(
                        name -> CategoryService.getInstance()
                                .findCategories(name).size() == 0,
                        "username must be unique")
                .bind(User::getName, User::setName);
    }

    private void addPasswordField() {
        getFormLayout().add(passwordField);

        getBinder().forField(passwordField)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "Password must contain at least 3 printable characters",
                        3, null))
                .withValidator(
                        name -> CategoryService.getInstance()
                                .findCategories(name).size() == 0,
                        "Password must be unique")
                .bind(User::getPassword, User::setPassword);
    }



    @Override
    protected void confirmDelete() {
        int reviewCount = ReviewService.getInstance()
                .findReviews(getCurrentItem().getName()).size();
        if (reviewCount > 0) {
            openConfirmationDialog("Delete user",
                    "Are you sure you want to delete the “"
                            + getCurrentItem().getName()
                            + "” user? There are " + reviewCount
                            + " teams associated with this user.",
                    "Deleting the category will mark the associated reviews as “undefined”. "
                            + "You can edit individual reviews to select another category.");
        } else {
            doDelete(getCurrentItem());
        }
    }
}
