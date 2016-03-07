package com.bubbles.server.domain;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Test for AbstractEntity.
 *
 * @author Mingshan Lei
 * @since 2016/3/7
 */
public class AbstractEntityTest {

    private static final Long value = 1000L;

    @Test
    public void testGetId() throws Exception {
        AbstractEntity entity = new AbstractEntity();
        assertNull(entity.getId());

        setEntityId(entity, value);
        assertTrue(entity.getId() == value);
    }

    @Test
    public void testEquals() throws Exception {
        AbstractEntity entity = new AbstractEntity();
        assertTrue(entity.equals(entity));
        assertFalse(entity.equals(null));

        setEntityId(entity, value);
        assertFalse(entity.equals(null));
        assertFalse(entity.equals(value));

        AbstractEntity entity2 = new AbstractEntity();
        setEntityId(entity2, value);
        assertTrue(entity.equals(entity2));
    }

    @Test
    public void testHashCode() throws Exception {
        AbstractEntity entity = new AbstractEntity();
        assertEquals(entity.hashCode(), 0);

        setEntityId(entity, value);
        assertEquals(entity.hashCode(), value.hashCode());
    }

    private void setEntityId(AbstractEntity entity, Long value) throws Exception  {
        Class clazz = AbstractEntity.class;
        Field field = clazz.getDeclaredField("id");
        field.setAccessible(true);
        field.set(entity, value);
    }
}
