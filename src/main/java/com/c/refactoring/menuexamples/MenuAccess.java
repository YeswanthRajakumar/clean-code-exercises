package com.c.refactoring.menuexamples;

import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(List<MenuItem> menuItemsList, Role[] roles) {
        menuItemsList.forEach(menuItem -> setAccessToMenuItems(roles, menuItem));
    }

    private void setAccessToMenuItems(Role[] roles, MenuItem menuItem) {
        for (Role role : roles) {
            if (hasAccess(role, menuItem.writeAccessRole)) {
                setVisibilityAndAccess(menuItem, Constants.WRITE);
                continue;
            }
            if (hasAccess(role, menuItem.readAccessRole))
                setVisibilityAndAccess(menuItem, Constants.READ);
        }
    }

    private boolean hasAccess(Role role, String menuItemAccess) {
        return role.name.equals(menuItemAccess);
    }

    private void setVisibilityAndAccess(MenuItem menuItem, String read) {
        menuItem.setAccess(read);
        menuItem.setVisible(true);
    }

}
