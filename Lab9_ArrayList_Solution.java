import java.util.Iterator;

// تعريف ArrayList مخصصة
public class CustomArrayList<T> implements Iterable<T> {
    private T[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;
    
    // المُنشئ
    public CustomArrayList() {
        data = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }
    
    // إضافة عنصر إلى القائمة
    public void add(T element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }
    
    // إزالة عنصر بناءً على الفهرس
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        T removedElement = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        return removedElement;
    }
    
    // الحصول على عنصر بناءً على الفهرس
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return data[index];
    }
    
    // إرجاع حجم القائمة
    public int size() {
        return size;
    }
    
    // توسيع المصفوفة عند الحاجة
    private void resize() {
        T[] newData = (T[]) new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
    
    // تنفيذ Iterator
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }
    
    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;
        
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }
            return data[currentIndex++];
        }
    }
    
    // اختبار القائمة
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        
        System.out.println("Elements in the list:");
        for (int num : list) {
            System.out.println(num);
        }
        
        System.out.println("Removed: " + list.remove(1));
        System.out.println("Element at index 1: " + list.get(1));
    }
}
