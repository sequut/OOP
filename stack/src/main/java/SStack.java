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
        stack[count] = aa;
        count += 1;
    }

    /**
     * push array of elements type "Type" to stack
     * @param aa - array with items
     */
    public void pushStack(SStack<Type> aa){

        int rem = aa.count();
        max_len += rem*2;
        count += rem;
        stack = Arrays.copyOf(stack, max_len);

        for (int i = 0; i < rem; i++)
            stack[count - i - 1] = aa.pop();
    }

    /**
     * delete one element from stack
     */
    public Type pop(){
        if (count == 0)
            throw new IndexOutOfBoundsException("stack is empty");
        else{
            count -= 1;
            return stack[count];
        }
    }

    /**
     * delete items from stack
     * @param number - how many items we should delete
     */
    public SStack<Type> popStack(int number){
        SStack<Type> answ = new SStack<>();
        if (count - number < 0){
            if (count == 0)
                throw new IndexOutOfBoundsException("stack is empty");
            else
                throw new IndexOutOfBoundsException("there are not so many elements on the stack");
        }
        else{
            for (int i = 0; i < number; i++)
                answ.push(stack[count - number + i]);
            count -= number;
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