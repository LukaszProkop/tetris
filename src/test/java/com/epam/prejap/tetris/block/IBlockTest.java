package com.epam.prejap.tetris.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test(groups = "IBlock")
public class IBlockTest {

    //given
    @Test(dataProvider = "iBlockShape")
    public void testIBlockShape(byte[][] expected) throws IllegalAccessException {
        //when
        byte[][] actual = getIBlock();

        //then
        assertEquals(actual, expected);
    }

    //given
    @Test(dataProvider = "shapeOfBlocks")
    public void testIBlockShapeAgainstOtherShapes(byte[][] anyBlock) throws IllegalAccessException {
        //when
        byte[][] actual = getIBlock();

        //then
        assertNotEquals(anyBlock, actual);
    }

    //given
    @Test(dataProvider = "iBlockShape")
    public void testIfBlockConstructorAcceptIBlock(byte[][] expected) throws IllegalAccessException {
        //given
        byte[][] iBlock = getIBlock();

        //when
        Block block = new Block(iBlock) {};
        byte[][] actual = block.image;

        //then
        assertEquals(actual, expected);

    }

    private static byte[][] getIBlock() throws IllegalAccessException {
        Field[] fields = IBlock.class.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> field.setAccessible(true));
        return (byte[][]) fields[0].get(new IBlock());
    }

    @DataProvider
    public static Object[][] shapeOfBlocks() {
        byte[][] lBlock = {{1, 0}, {1, 0}, {1, 1}};
        byte[][] jBlock = {{0, 1}, {0, 1}, {1, 1}};
        byte[][] sBlock = {{0, 1, 1}, {1, 1, 0}};
        byte[][] zBlock = {{1, 1, 0}, {0, 1, 1}};
        byte[][] oBlock = {{1, 1}, {1, 1}};
        return new Object[][]{
                {lBlock},
                {jBlock},
                {sBlock},
                {zBlock},
                {oBlock}
        };
    }

    @DataProvider
    public static Object[] iBlockShape(){
        byte[][] iBlock = {{1}, {1}, {1}, {1}};
        return new Object[]{iBlock};
    }
}
