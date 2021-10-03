import java.util.Arrays;
import java.util.Iterator;

public class Stack<Type> implements Iterable<Type> {

    private int count = 0;
    private int max_len = 0;

    @SuppressWarnings("unchecked")
    private Type[] stack = (Type[]) new Object[0];

    /**
     * returns number of elements in stack
     * @return - returns how how many elements in stack right now
     */
    public int count(){
        return count;
    }

    /**
     * push one element type Type to stack
     * @param aa - item
     */
    public void push(Type aa){
        if (count == max_len){
            max_len = max_len * 2 + 1;
            stack = Arrays.copyOf(stack, max_len);
        }
        stack[count++] = aa;
    }

    /**
     * push array of elements type Type to stack
     * @param aa - array with items
     */
    public void pushStack(Type[] aa){
        for (Type type : aa) {
            if (count == max_len) {
                max_len = max_len * 2 + 1;
                stack = Arrays.copyOf(stack, max_len);
            }
            stack[count++] = type;
        }
    }

    /**
     * delete one element from stack
     */
    public void pop(){
        try {
            stack[count - 1] = stack[count - 1];
            count -= 1;
        }
        catch (IndexOutOfBoundsException e){
            count = 0;
            System.err.println(e.getMessage());
        }
    }

    /**
     * delete items from stack
     * @param number - how many items we should delete
     */
    public void popStack(int number){
        try {
            stack[count - number] = stack[count - number];
            count -= number;
        }
        catch (IndexOutOfBoundsException e){
            count = 0;
            System.err.println(e.getMessage());
        }
    }

    /**
     * write current stack
     */
    public void write(){
        for (int i = 0; i < count; i++)
            System.out.println(stack[i]);
        System.out.println();
    }

    @Override
    public Iterator<Type> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return count > index;
            }

            @Override
            public Type next() {
                return stack[index++];
            }
        };
    }
}
