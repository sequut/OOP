import java.util.Arrays;

public class SStack<Type>{

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
    public void pushStack(SStack<Type> aa){
        for (int i = 0; i < aa.count; i++) {
            if (count == max_len) {
                max_len = max_len * 2 + 1;
                stack = Arrays.copyOf(stack, max_len);
            }
            stack[count++] = aa.stack[i];
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
    public SStack<Type> popStack(int number){

        SStack<Type> answ = new SStack<>();
        try {
            stack[count - number] = stack[count - number];
        }
        catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("there are not so many elements on the stack");
        }

        for (int i = 0; i < number; i++){
            stack[count - 1] = stack[count - 1];
            count -= 1;
            answ.push(stack[count]);
        }
        return answ;
    }

    /**
     * write current stack
     */
    public void write(){
        for (int i = 0; i < count; i++)
            System.out.println(stack[i]);
        System.out.println();
    }
}