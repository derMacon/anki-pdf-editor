package com.dermacon.ankipdfeditor.data.worker.parser;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HtmlCardParserTest {

    @Test
    public void testExtractAllNumbers_numOutSideOfTag() {
//        String in = "<div><123><b>456</b>789<10></div>";
        String in = "asdf{123}asdf{34}asdf";
        int[] actOutput = HtmlCardParser.extractAllNumbers(in);
        int[] expOutput = new int[] {123, 10};
        Assert.assertArrayEquals(expOutput, actOutput);
    }

}