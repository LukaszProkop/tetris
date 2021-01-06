package com.epam.prejap.tetris.block;

import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = "Block")
public class BlockFeedTest {

    @Test
    public void shallContainLBlock() {
        //given
        BlockFeed feed = new BlockFeed();

        //when
        boolean containsLBlock = feed.blocks()
                .stream()
                .map(Supplier::get)
                .anyMatch(e -> e instanceof LBlock);

        //then
        assertTrue(containsLBlock);
    }

    @Test(dependsOnMethods = "shallContainLBlock")
    public void shallContainOnlyOneLBlock() {
        //given
        BlockFeed feed = new BlockFeed();

        //when
        List<Block> blocks = feed.blocks()
                .stream()
                .map(Supplier::get)
                .filter(e -> e instanceof LBlock)
                .collect(Collectors.toList());

        //then
        assertEquals(blocks.size(), 1);
    }

    /**
     * Another way to implement above LBlock tests for IBlock
     *                    ▼ ▼ ▼ ▼
     *                    ▼ ▼ ▼ ▼
     */
    @Test
    public void shallContainIBlock() throws IllegalAccessException {
        //given
        List<Supplier<Block>> feedList = getBlocksList();

        //when
        boolean containsLBlock = feedList.stream()
                .map(Supplier::get)
                .anyMatch(e -> e instanceof LBlock);

        //then
        assertTrue(containsLBlock);
    }

    @Test
    public void shallContainOnlyOneIBlock() throws IllegalAccessException {
        //given
        List<Supplier<Block>> feedList = getBlocksList();

        //when
        int actual = (int) feedList.stream()
                .map(Supplier::get)
                .filter(block -> block instanceof IBlock)
                .count();

        //then
        assertEquals(actual, 1);
    }

    private static List getBlocksList() throws IllegalAccessException {
        Field[] fields = BlockFeed.class.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> field.setAccessible(true));
        return (List) fields[1].get(new BlockFeed());
    }
}
