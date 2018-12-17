package com.vaadin.starter.beveragebuddy.backend;

public class ReviewTable{
    public ReviewTable(){
        Table table = new Table("The Brightest Stars");

        // Define two columns for the built-in container
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Mag",  Float.class, null);

        // Add a row the hard way
        Object newItemId = table.addItem();
        Item row1 = table.getItem(newItemId);
        row1.getItemProperty("Name").setValue("Sirius");
        row1.getItemProperty("Mag").setValue(-1.46f);

        // Add a few other rows using shorthand addItem()
        table.addItem(new Object[]{"Canopus",        -0.72f}, 2);
        table.addItem(new Object[]{"Arcturus",       -0.04f}, 3);
        table.addItem(new Object[]{"Alpha Centauri", -0.01f}, 4);

        // Show exactly the currently contained rows (items)
        table.setPageLength(table.size());
    }
}
