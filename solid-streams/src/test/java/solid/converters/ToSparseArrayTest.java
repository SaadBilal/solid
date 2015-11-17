package solid.converters;

import android.util.SparseArray;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import solid.functions.SolidFunc1;
import test_utils.MockSparseArray;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static solid.converters.ToSparseArray.toSparseArray;
import static solid.stream.Stream.stream;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SparseArray.class, ToSparseArray.class})
public class ToSparseArrayTest {
    @Test
    public void testToSparseArray() throws Exception {
        MockSparseArray.powerMockNew();

        assert123(stream(asList("1", "2", "3"))
            .collect(toSparseArray(new SolidFunc1<String, Integer>() {
                @Override
                public Integer call(String value) {
                    return Integer.parseInt(value);
                }
            })));

        assert123(stream(asList("1", "2", "3"))
            .collect(toSparseArray(new SolidFunc1<String, Integer>() {
                @Override
                public Integer call(String value) {
                    return Integer.parseInt(value);
                }
            }, 10)));


        assert123(stream(asList("1", "2", "3"))
            .collect(new ToSparseArray<>(value -> Integer.parseInt(value))));

        assert123(stream(asList("1", "2", "3"))
            .collect(new ToSparseArray<>(value -> Integer.parseInt(value), 10)));

        assertEquals(0, stream(new ArrayList<>()).collect(toSparseArray(null)).size());
    }

    private void assert123(SparseArray<String> array4) {
        assertEquals(3, array4.size());
        assertEquals("1", array4.get(1));
        assertEquals("2", array4.get(2));
        assertEquals("3", array4.get(3));
    }
}
