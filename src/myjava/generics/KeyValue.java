package myjava.generics;

public class KeyValue<K, V> {

    private K key;
    private V value;

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("{%s:%s}", key, value);
    }

    public static void main(String[] args) {
        KeyValue<String, Integer> kv1 = new KeyValue<String,Integer>("key", 1);
        System.err.println(kv1);
        KeyValue<Character, Float> kv2 = new KeyValue<Character,Float>('a', 2.0f);
        System.out.println(kv2);
    }

}
