package org.example.controller;

import org.example.dto.MemberJoin;

public class Controller {

    protected static MemberJoin loginedMember = null;

    public static boolean isLogined() {
        return loginedMember != null;
    }

    public void doAction(String cmd, String actionMethodName) {

    }


}
