package com.skocken.efficientadapter.lib.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Testing class
 */
public class AdapterUpdaterTest {

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String F = "F";

    AdapterUpdater<String> underTest;
    FakeUpdater updater;

    @Before
    public void setUp() throws Exception {
        updater = Mockito.spy(new FakeUpdater());
        updater.getObjects().addAll(Arrays.asList(A, B, C, D, E));

        underTest = new AdapterUpdater<>(updater);
    }

    @Test
    public void testUpdate_fromEmpty() throws Exception {
        updater = Mockito.spy(new FakeUpdater());
        underTest = new AdapterUpdater<>(updater);
        assertUpdateWith(newList(A, B, C, D, E, F));
    }

    @Test
    public void testUpdate_fromEmptyWithDuplicate() throws Exception {
        updater = Mockito.spy(new FakeUpdater());
        underTest = new AdapterUpdater<>(updater);
        assertUpdateWith(newList(A, B, C, D, C, F));
    }

    @Test
    public void testUpdate_addF() throws Exception {
        assertUpdateWith(newList(A, B, F, C, D, E));

        verify(updater).notifyItemChanged(eq(0));
        verify(updater).notifyItemChanged(eq(1));
        verify(updater).notifyItemChanged(eq(2));
        verify(updater).notifyItemChanged(eq(3));
        verify(updater).notifyItemChanged(eq(4));
    }

    @Test
    public void testUpdate_removeC() throws Exception {
        assertUpdateWith(newList(A, B, D, E));

        verify(updater).notifyItemChanged(eq(0));
        verify(updater).notifyItemChanged(eq(1));
        verify(updater, never()).notifyItemChanged(eq(2));
        verify(updater).notifyItemChanged(eq(3));
        verify(updater).notifyItemChanged(eq(4));
    }

    @Test
    public void testUpdate_moveCAfter() throws Exception {
        assertUpdateWith(newList(A, B, D, C, E));
    }

    @Test
    public void testUpdate_moveCEnd() throws Exception {
        assertUpdateWith(newList(A, B, D, E, C));
    }

    @Test
    public void testUpdate_moveCBefore() throws Exception {
        assertUpdateWith(newList(A, C, B, D, E));
    }

    @Test
    public void testUpdate_moveCBegin() throws Exception {
        assertUpdateWith(newList(C, A, B, D, E));
    }

    @Test
    public void testUpdate_withDuplicate() throws Exception {
        assertUpdateWith(newList(A, B, C, D, E, C));
    }

    @Test
    public void testUpdate_removeEverything() throws Exception {
        assertUpdateWith(newList());
    }

    @Test
    public void testUpdate_moveEverything() throws Exception {
        assertUpdateWith(newList(E, B, D, C, A));
    }

    private void assertUpdateWith(List<String> newList) {
        underTest.update(newList);

        assertEquals(newList, updater.getObjects());
    }

    private List<String> newList(String... content) {
        return Arrays.asList(content);
    }

    private static class FakeUpdater implements AdapterUpdater.Updater<String> {

        private List<String> mObjects = new ArrayList<>();

        public FakeUpdater() {
        }

        @Override
        public List<String> getObjects() {
            return mObjects;
        }

        @Override
        public int size() {
            return mObjects.size();
        }

        @Override
        public String get(int index) {
            return mObjects.get(index);
        }

        @Override
        public void notifyItemChanged(int i) {
        }

        @Override
        public void removeAt(int i) {
            mObjects.remove(i);
        }

        @Override
        public int indexOf(String item) {
            return mObjects.indexOf(item);
        }

        @Override
        public void add(int i, String item) {
            mObjects.add(i, item);
        }
    }

}