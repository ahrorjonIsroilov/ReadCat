package ent.readcat.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Permissions {
    //USER PERMISSIONS
    ADD_BOOK(Group.USER),
    GET_BOOK(Group.USER),
    GET_ALL_BOOK(Group.USER),
    DELETE_BOOK(Group.USER),
    EDIT_BOOK(Group.USER),
    ADD_NOTE(Group.USER),
    GET_ALL_NOTE(Group.USER),
    EDIT_NOTE(Group.USER),
    DELETE_NOTE(Group.USER),
    ADD_QUOTE(Group.USER),
    GET_ALL_QUOTE(Group.USER),
    EDIT_QUOTE(Group.USER),
    DELETE_QUOTE(Group.USER),
    ADD_SESSION(Group.USER),
    GET_ALL_SESSION(Group.USER),
    DELETE_SESSION(Group.USER),
    EDIT_SESSION(Group.USER),
    ADD_SHELVE(Group.USER),
    GET_ALL_SHELVE(Group.USER),
    EDIT_SHELVE(Group.USER),
    DELETE_SHELVE(Group.USER),
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
