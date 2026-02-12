package com.tushar.lsm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LSMIntegrationTest {

    @Test
    void writeHeavyShouldUseSizeTieredCompaction() {
        LSMTree tree = new LSMTree(3);

        for (int i = 0; i < 10; i++) {
            tree.put("key" + i, "value" + i);
        }

        assertTrue(tree.getSSTables().size() <= 2);
    }

    @Test
    void readHeavyShouldPromoteToHotTier() {
        LSMTree tree = new LSMTree(2);

        tree.put("a", "1");
        tree.put("b", "2"); // flush

        for (int i = 0; i < 5; i++) {
            tree.get("a");
        }

        SSTable table = tree.getSSTables().get(0);

        assertEquals(StorageTier.HOT, table.getTier());
    }

    @Test
    void shouldRetrieveCorrectValues() {
        LSMTree tree = new LSMTree(2);

        tree.put("x", "100");
        tree.put("y", "200");

        assertEquals("100", tree.get("x"));
        assertEquals("200", tree.get("y"));
    }
}
