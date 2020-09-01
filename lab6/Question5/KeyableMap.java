import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

class KeyableMap<T,K,V extends Keyable<K>> implements Keyable<T> {
   	private T key;
    private Map<K,V> map = new HashMap<>();
     
	KeyableMap(T key) {
        this.key = key;
    }
        
    @Override
    public T getKey() {
        return this.key;
    }

    public KeyableMap<T,K,V> put(V item) {
        map.put(item.getKey(), item);
        return this;
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(map.get(key));
    }

    @Override
    public String toString() {
        String str = key.toString() + ": {";
        int counter = map.size() - 1;
        for (V i : map.values()) {
            str = str + i + (counter == 0 ? "" : ", ");
            counter--;
        }
        str = str + "}";
        return str;
    }
}


