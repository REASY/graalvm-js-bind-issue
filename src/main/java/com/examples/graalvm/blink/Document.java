package com.examples.graalvm.blink;

import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyExecutable;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.util.Arrays;

public class Document implements ProxyObject {
    @Override
    public Object getMember(String key) {
        System.out.println("getMember: " + key);
        if (key.equals("createElement")) {
            ProxyExecutable pe = new ProxyExecutable() {
                @Override
                public Object execute(Value... arguments) {
                    System.out.println("execute(). Received " + Arrays.toString(arguments));
                    return "bar";
                }
            };
            return pe;
        } else {
            throw new RuntimeException("WTF?!");
        }
    }

    @Override
    public Object getMemberKeys() {
        System.out.println("getMemberKeys: ");
        return null;
    }

    @Override
    public boolean hasMember(String key) {
        System.out.println("hasMember: " + key);
        if (key.equals("createElement")) return true;
        return false;
    }

    @Override
    public void putMember(String key, Value value) {
        System.out.println("putMember: (" + key + ", " + value + ")");
    }
}
