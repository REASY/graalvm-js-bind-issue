package com.examples.graalvm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import com.examples.graalvm.ItemCollection.Item;

public class ArrayMapExample {
    public static void main(String[] args) {
        Context ctx = Context.newBuilder("js")
                .allowAllAccess(true)
                .allowExperimentalOptions(true)
                .option("js.experimental-foreign-object-prototype", "true")
                .build();

        // Callback to apply on each element of collection
        ctx.eval("js", "function printAndReturnName(x) { console.log(x.name); return x.name; }");

        // This object can be traversed by Array.map
        /*
        jsItem = {
            length: 3,
            0: {
                name: "JsItem 1"
            },
            1: {
                name: "JsItem 2"
            },
            2: {
                name: "JsItem 3"
            }
        }
        */
        ctx.eval("js", "jsItem = {\n" +
                        "    length: 3,\n" +
                        "    0: {\n" +
                        "    \tname: \"JsItem 1\"\n" +
                        "    },\n" +
                        "    1: {\n" +
                        "    \tname: \"JsItem 2\"\n" +
                        "    },\n" +
                        "    2: {\n" +
                        "    \tname: \"JsItem 3\"\n" +
                        "    }\n" +
                        "}");
        Value rJs = ctx.eval("js", "Array.prototype.map.call(jsItem, printAndReturnName);");
        // Prints `(3)["JsItem 1", "JsItem 2", "JsItem 3"]`
        System.out.println(rJs);

        ItemCollection itemCollection = new ItemCollection(new Item[]{new Item("1"), new Item("2"), new Item("3")});
        Value bindings = ctx.getBindings("js");
        bindings.putMember("items", itemCollection);

        // Can access by index
        ctx.eval("js", "console.log('item[0]:', items[0].name)");
        ctx.eval("js", "console.log('item[1]:', items[1].name)");
        ctx.eval("js", "console.log('item[2]:', items[2].name)");
        Value rJava = ctx.eval("js", "Array.prototype.map.call(items, printAndReturnName);");
        // Prints `(3)[empty × 3]`
        System.out.println(rJava);
    }
}
