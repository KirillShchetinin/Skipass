package skipass.systema;

import java.util.Arrays;

public class MyArrayList {
    Object [] elementData;
    private int size;
   
    public MyArrayList(){
        this(10);
    }
    
    public MyArrayList(int initialCapacity) {
        this.elementData = new Object[initialCapacity];
    }
    
    public void add(Object elem){
        this.ensureCapacity(++size);
        this.elementData[size - 1] = elem;
    }
    
    public void add(int index, Object elem) {
        if (checkForOutOfBound(index)){
            this.ensureCapacity(size + 1);
            System.arraycopy(this.elementData, index, this.elementData,
                    index + 1, this.size - index);
            this.elementData[index] = elem;
            ++size;
        }
    }
    
    public void addAll(Object []c) {
        this.ensureCapacity(size + c.length);
        System.arraycopy(c, 0, this.elementData, this.size, c.length);
        size += c.length;
    }
    
    private Boolean checkForOutOfBound(int index){
        if (index < 0 || index > size + 1) {
            System.out.println("Error: out of bound");
            return false;
        } else {
            return true;
        }
    }
    
    public void addAll(int index, Object []c) {
        if (checkForOutOfBound(index)){
            this.ensureCapacity(size + c.length);
            System.arraycopy(this.elementData, index, this.elementData,
                    index + c.length, this.size - index);
            System.arraycopy(c, 0, this.elementData, index, c.length);
            this.size += c.length;
        }
    }
    
    public Object get(int index) {
        if (this.checkForOutOfBound(index)) {
            return this.elementData[index];
        } else {
            return null;
        }
    }
    
    public Object remove(int index) {
        if (this.checkForOutOfBound(index)) {
            Object result = this.elementData[index];
            System.arraycopy(this.elementData, index + 1, this.elementData, index, size - index);
            size--;
            this.trimToSize();
            return result;
        } else {
            return null;
        }
    }
    
    private void trimToSize() {
        if (this.elementData.length <= 10 ) {
            return;
        }
        if (this.size < this.elementData.length) {
            this.elementData = Arrays.copyOf(elementData, size);
        }
    }
    
    public void set(int index, Object elem) {
        if (this.checkForOutOfBound(index)) {
            this.elementData[index] = elem;
        }
    }
    
    public int indexOf(Object elem) {
        for (int i = 0; i < this.size;i++) {
            if (elem !=null) {
                if (elem.equals(this.elementData[i]))
                    return i;
            } else {
                if (this.elementData == null) {
                    return i;
                }
            }
        }
        return -1;
    } 
    
    public int size() {
        return this.size;
    }
    
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elementData[i] = null;
        }
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public Object[] toArray(){
        return Arrays.copyOf(elementData, size);
    }
    
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            if (i > 0) result.append(",");
            result.append(this.elementData[i]);
        }
        return result.toString();
    }
    
    public void ensureCapacity(int minCapacity){
        if (minCapacity > this.elementData.length) {
            int oldCapacity = this.elementData.length;
            int newCapacity = (oldCapacity * 3) / 2;
            if (newCapacity < minCapacity)  newCapacity = minCapacity + 1;
            Object[] array = new Object[newCapacity];
            System.arraycopy(this.elementData, 0, array, 0,
                    this.elementData.length);
            this.elementData = array;
            
        }
    }
    
}
