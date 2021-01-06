package com.epam.prejap.tetris.block;

/**
 * I-shaped block implementation of the {@link Block} abstraction class.
 * <br><br>
 * <div style="text-indent: 20;"> Implementation note: <p>This class
 * implements static 2d array in order to create required "I" shape block
 * </p></div>
 * <br>
 *
 * @author Łukasz Prokop
 * @see Block
 */
final class IBlock extends Block {

    private static final byte[][] IMAGE = {
            {1},
            {1},
            {1},
            {1},
    };

    IBlock() {
        super(IMAGE);
    }
}
