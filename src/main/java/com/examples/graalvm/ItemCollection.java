package com.examples.graalvm;

import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.util.HashMap;
import java.util.stream.IntStream;

public class ItemCollection implements ProxyObject {
    private final ProxyObject internalProxy;
    private final Item[] items;

    public ItemCollection(Item[] elements) {
        items = elements;

        HashMap<String, Object> map = new HashMap<String, Object>() {
            {
                // Set `length`
                put("length", items.length);
            }
        };
        IntStream.range(0, items.length)
                .forEach(idx -> {
                    // Set index as property
                    map.put(Integer.toString(idx), items[idx]);
                });
        internalProxy = ProxyObject.fromMap(map);
    }

    @Override
    public Object getMember(String key) {
        return internalProxy.getMember(key);
    }

    @Override
    public Object getMemberKeys() {
        return internalProxy.getMemberKeys();
    }

    @Override
    public boolean hasMember(String key) {
        return internalProxy.hasMember(key);
    }

    @Override
    public void putMember(String key, Value value) {
    }

    public static class Item {
        public final String name;

        public Item(String name) {
            this.name = name;
        }
    }
}
