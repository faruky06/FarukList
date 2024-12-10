public class FarukList<T> implements Cloneable{
    private int length;
    private Object[] array;
    private int firstEmptyIndex = 0;
    private int n = 1;

    public FarukList(){
        //Initialize default length of farukList to 10
        this(10);
    }
    public FarukList(int length){
        this.length = length;
        this.array = new Object[length];
    }


    //increases the size of the internal array which stores the elements of the list exponentially (a power of 2)
    private void internalArray(){
            Object[] placeholder = new Object[length];

            for(int i = 0; i < (length); i++){
                placeholder[i] = array[i];
            }

            array = new Object[length * 2];
            for(int i = 0; i < (length); i++){
                this.array[i] = placeholder[i];
            }
            this.length = this.length * 2;
            n++;
    }

    //pushes all elements at specified index to the right, checks if current array size is big enough
    private void pushRight(int index){
        if(firstEmptyIndex != length){
        for(int i = index; i <= firstEmptyIndex; i++){
            T temp = (T)array[i + 1];
            T temp2 =(T)array[i];
            array[i + 1] = temp2;
            temp2 = temp;
        }
    }
    else{
        internalArray();
        pushRight(index);
    }
    }

    private void pushLeft(int index){ //pushes every 
        T temp = null;
        for(int i = firstEmptyIndex; i > index; i--){
            T temp2 =(T)array[i - 1];
            array[i - 1] = temp;
            temp = temp2;
        }
        firstEmptyIndex--;
    }

    public int size(){
        return firstEmptyIndex;
    }

    public void add(T a){
        //Check if last element is non default value
            if(array[length - 1] != null){
            firstEmptyIndex = length + 1;
            internalArray();
            this.array[(length / 2)] = a;
            return;
            } else if(array[firstEmptyIndex] == null){
                    this.array[firstEmptyIndex] = a;
                    firstEmptyIndex++;
            }

    }

    //Add object of type T into index 
    //Checks if index is empty, if not shifts all elements >= i to the right
    public void add(T a, int index){
        //Throws exception if trying to add element to index that is greater than the first empty index
        if(index > firstEmptyIndex){
            throw new IndexOutOfBoundsException();
        }

        if(array[index] == null)
            array[index] = a;
        else {
            pushRight(index);
            firstEmptyIndex++;
            array[index] = a;
            }

        }
    
    public void remove(int index){
        pushLeft(index);
    }

    public void remove(T object){ //Removes first instance of object
        for(int i = 0; i < array.length; i++){
            if((T)array[i] == object){
                remove(i);
                break;
            }
        }
    }
    public void remove(T object, int n) { //removes nth occurence of object type T, does nothing if <n occurrences
        int count = 0;
        for(int i = 0; i < array.length; i++){
            if((T)array[i] == object && count == n - 1){
                remove(i);
            }
            else if((T)array[i] == object)
                count++;
        }
    }

    public boolean contains(T o){ //true if object of type T is contained in FarukList
        for(int i = 0; i < firstEmptyIndex; i++){
            if(array[i].equals(o))
            return true;
        }
        return false;
    }

    public int indexOf(T object){
        for(int i = 0; i < firstEmptyIndex; i++){
            if(array[i].equals(object))
            return i;
        }
        return -1;
    }
    //
    public int indexOf(T object, int occurence){
        int occurs = 1;
        int index = -1;
            for(int i = indexOf(object) + 1; i < firstEmptyIndex; i++){
                if(array[i] == object){
                    occurs++;
                    index = i;
                }
                if(occurs == occurence)
                return index;
            }
            return -1;
        }
    public int occurs(T object){ //returns how many times object type T occurs in FarukList
        int occurs = 0;
       for(int i = 0; i < firstEmptyIndex; i++){
        if(array[i] == object)
        occurs++;
       }
       return occurs;
    }

    /*gets value of specific element, if list has n 
    elements and tries to access index i > n - 1 throws IndexOutOfBounds Exception*/
    public T get(int index){
        if(index > firstEmptyIndex){
            throw new IndexOutOfBoundsException();
        }
        return (T)(array[index]);
    }
    @Override
    public String toString(){
        String s = "[";
        for(int i = 0; i < firstEmptyIndex; i++){
            if(i == firstEmptyIndex - 1)
                s += array[i];
            else
                s += array[i] + ", ";
        }
        s += "]";
        return s;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{ //makes a shallow copy 
        return super.clone();
    }
}
