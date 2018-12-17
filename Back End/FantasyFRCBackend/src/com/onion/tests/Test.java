package com.onion.tests;

import com.onion.match.GetBigData;
import com.onion.parser.Parser;
import com.onion.utils.Constants;
import com.onion.utils.HttpReqUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test {
    public static void main(String[] args){
        GetBigData g = new GetBigData();
        g.start();
    }
}
