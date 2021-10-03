import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stack<Type> implements Iterable<Type> {

    private int count = 0;
    private ArrayList<Type> stack = new ArrayList<>();

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
        stack.add(aa);
        count += 1;
    }

    /**
     * push array of elements type Type to stack
     * @param aa - array with items
     */
    public void pushStack(Type[] aa){
        stack.addAll(List.of(aa));
        count += aa.length;
    }

    /**
     * delete one element from stack
     */
    public void pop(){
        if (count > 0)
            stack.remove(--count);
    }

    /**
     * delete items from stack
     * @param number - how many items we should delete
     */
    public void popStack(int number){
        for (int i = 0; i < number; i++) {
            try {
                stack.remove(count - 1);
                count -= 1;
            }
            catch (IndexOutOfBoundsException e){
                count = 0;
                break;
            }
        }
    }

    /**
     * write current stack
     */
    public void write(){
        for (int i = 0; i < count; i++)
            System.out.println(stack.get(i));
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
                return stack.get(index++);
            }
        };
    }
}
