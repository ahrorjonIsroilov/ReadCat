package ent.readon.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Permissions {
    //USER PERMISSIONS
    ADD_BOOK(Group.USER),
    DELETE_BOOK(Group.USER),
    EDIT_BOOK(Group.USER),
    ADD_NOTE(Group.USER),
    DELETE_NOTE(Group.USER),
    EDIT_NOTE(Group.USER),
    ADD_QUOTE(Group.USER),
    DELETE_QUOTE(Group.USER),
    EDIT_QUOTE(Group.USER),
    ADD_SESSION(Group.USER),
    DELETE_SESSION(Group.USER),
    EDIT_SESSION(Group.USER),
    //USER PERMISSIONS
    SHOW_USER_LIST(Group.ADMIN),
    ADD_ADMIN(Group.ADMIN),
    DELETE_ADMIN(Group.ADMIN),
    BLOCK_ADMIN(Group.ADMIN),
    ADD_USER(Group.ADMIN),
    DELETE_USER(Group.ADMIN),
    BLOCK_USER(Group.ADMIN);
    private final Group group;

    public boolean isInGroup(Group group) {
        return this.group == group;
    }

    public enum Group {
        ADMIN,
        USER,
        ALL
    }
}
