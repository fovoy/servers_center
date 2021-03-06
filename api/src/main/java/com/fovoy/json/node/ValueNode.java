package com.fovoy.json.node;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zxz.zhang on 16/8/15.
 */
@JsonSerialize(using = JacksonSupport.ValueNodeSerializer.class)
@JsonDeserialize(using = JacksonSupport.ValueNodeDeserializer.class)
public interface ValueNode extends Iterable<ValueNode>, Serializable {

    enum Type {
        list, map, bool, number, string, NULL
    }

    Type getType();

    List<ValueNode> find(String... path);

    List<ValueNode> find(int count, String... path);

    ValueNode firstChild(String... path);

    ValueNode get(int childIndex);

    int size();

    ValueNode get(String childName);

    Set<Map.Entry<String, ValueNode>> entrySet();

    Set<String> keySet();

    Date toDateValue();

    Number toNumber();

    Boolean toBooleanValue();

    String toJson();

    int getIntValue(int def);

    float getFloatValue(float def);

    long getLongValue(long def);

    Map<Path, ValueNode> flatten();

    List<DiffValue> diff(ValueNode other);

    boolean contains(ValueNode other, Set<Path> ignores);

    static interface DiffValue extends Comparable<DiffValue> {

        boolean getAction();

        Path getPath();

        ValueNode getValue();
    }
}
