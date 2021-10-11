import java.util.Arrays;

public class Stack<Type>{

    private int count = 0;
    private int max_len = 0;

    @SuppressWarnings("unchecked")
    private Type[] stack = (Type[]) new Object[0];

    /**
     * returns number of elements in stack
     * @return - returns how many elements in stack right now
     */
    public int count(){
        return count;
    }

    /**
     * push one element type "Type" to stack
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
     * push array of elements type "Type" to stack
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
    public Type pop(){
        try {
            stack[count - 1] = stack[count - 1];
            count -= 1;
        }
        catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("stack is empty");
        }
        return stack[count];
    }

    /**
     * delete items from stack
     * @param number - how many items we should delete
     */
    public Type[] popStack(int number){
        @SuppressWarnings("unchecked")
        Type[] answ = (Type[]) new Object[0];
        answ = Arrays.copyOf(answ, number);
        try {
            stack[count - number] = stack[count - number];
        }
        catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("there are not so many elements on the stack");
        }

        for (int i = 0; i < number; i++){
            stack[count - 1] = stack[count - 1];
            count -= 1;
            answ[i] = stack[count];
        }
        return answ;
    }

/*    public stack popStack(int n) throws Exception {
        if (head < 0) {
            Exception e;
            throw e = new IndexOutOfBoundsException("Stack is empty");
        }
        int a = head - n;
        if (a < 0) a = -1;
        stack<T> ret = new stack();
        ret.arr = Arrays.copyOfRange(arr, a + 1, head + 1);
        ret.head = n - 1;
        ret.len = n;
        head = a;
        return ret;
    }

 */


    /**
     * write current stack
     */
    public void write(){
        for (int i = 0; i < count; i++)
            System.out.println(stack[i]);
        System.out.println();
    }
}