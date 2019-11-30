package com.dermacon.ankipdfeditor.data.worker.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HtmlCardParserTest {

    @Test
    public void testExtractAllNumbers_numOutSideOfTag() {
        String in = "<div><123><b>456</b>789<10></div>";
        List<Integer> actOutput = HtmlCardParser.extractAllNumbers(in);
        List<Integer> expOutput = Arrays.asList(new Integer[] {123, 10});
        Assert.assertEquals(expOutput, actOutput);
    }

}