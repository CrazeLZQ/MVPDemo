package com.lzq.mvp.http.bean;

import java.util.ArrayList;

/**
 * Created by LZQ on 2017/11/28 09:46.
 * Describe :
 */

public class Test {
    public static class TestOne{
        public String showAllTime;
        public String nodeName;
        public String flag;
        public String nodeId;
        public String isMore;
        public String isHot;
    }


    public static class RSP{
        public ArrayList<TestOne> newsNodes = new ArrayList<>();
    }
}
