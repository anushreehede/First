package math;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;
import java.util.Arrays;
public class Calc {
	private final int size=250;
	private int top=-1;
    private char[] Stack=new char[50];
    private int intStack[]= new int[50];
    private List<Character> expr = new ArrayList<Character>(100);
    private List<Character> post = new ArrayList<Character>(100);
    
    public void getData(String str)
    {
    	//Scanner input = new Scanner(System.in);
    	for(int i = 0; i<str.length();i++){
            expr.add(str.charAt(i));
        }
    }
	public int Push(char ele)
	{
		if (top==size-1)
			return -1;
		else if (top==-1)
		{top=0;
		 Stack[top]=ele;
		}
		else
		{
			++top;
			Stack[top]=ele;
		}
		return 0;
	}
	public int Push_calc(int ele)
	{
		if (top==size-1)
			return -1;
		else if (top==-1)
		{top=0;
	     intStack[top]=ele;
		}
		else
		{
			++top;
			intStack[top]=ele;
		}
		return 0;
	}
	public char Pop()
	{
		char ret;
		if(top==-1)
			return 'X';
		else
		{ret=Stack[top];
		 top--;
		}
		return ret;
	}
	public int Pop_calc()
	{
		int ret;
		if(top==-1)
			return -1;
		else
		{ret=intStack[top];
		 top--;
		}
		return ret;
	}
	public void insertRight(int pos)
	{
		int j,right,left,f;
		right=0;left=0; f=0;
		for(j=pos+1;j<expr.size();++j)
		{
			if(expr.get(j)=='(')
				++left;
			if(expr.get(j)==')')
				++right;
			if(right==left)
			{f=1; break;}
		}
		if(f==1)
		{
			expr.add(j+1,')');
		}
		return;
	}
	public void insertLeft(int pos)
	{
		int j,right,left,f;
		right=0;left=0; f=0;
		for(j=pos-1;j>=0;--j)
		{
			if(expr.get(j)=='(')
				++left;
			if(expr.get(j)==')')
				++right;
		    if(right==left)
			{f=1; break;}
		}
		if(f==1)
		{
			expr.add(j,'(');
		}
		return;
	}
	public void parenth()
	{
		int i;
		for(i=0;i<expr.size();++i)
		{
			if(expr.get(i)=='^')
			{
				insertRight(i);
				insertLeft(i);
				++i;
			}
		}
		for(i=0;i<expr.size();++i)
		{
			if(expr.get(i)=='*'||expr.get(i)=='/')
			{
				insertRight(i);
				insertLeft(i);
				++i;
			}
		}
		for(i=0;i<expr.size();++i)
		{
			if(expr.get(i)=='-'||expr.get(i)=='+')
			{
				insertRight(i);
				insertLeft(i);
				++i;
			}
		}
		//System.out.println(expr+"\n");
	}
	public void makePostfix()
	{
		int i,j=0,c;
		char ch;
		for(i=0;i<expr.size();++i)
			{
				if(expr.get(i)>='0' && expr.get(i)<='9')
				{
				    post.add(j,expr.get(i));
					++j;
				}
				else if(expr.get(i)=='(' || expr.get(i)=='+' || expr.get(i)=='-'||expr.get(i)=='*'||expr.get(i)=='/'||expr.get(i)=='^' )
				{
					c=Push(expr.get(i));
					if(c==-1) break;
				}
				else if(expr.get(i)==')')
				{
					while(Stack[top]!='(')
					{
						ch=Pop();
						post.add(j,ch);
						++j;
					}
					ch=Pop();
				}
				else continue;
			}
		post.add(j,'\0');
	    //System.out.println(post+"\n");
	}
	public int evaluate()
	{
		int i,val,lue,num1,num2,c;
		for(i=0;post.get(i)!='\0';++i)
			{
				if(post.get(i)>='0' && post.get(i)<='9')
				{
					lue=post.get(i)-48;
					c=Push_calc(lue);
					if(c==-1) break;
				}
				else if(post.get(i)=='(' || post.get(i)=='+' || post.get(i)=='-'||post.get(i)=='*'||post.get(i)=='/'||post.get(i)=='^' )
				{
					num1=Pop_calc();
					num2=Pop_calc();
					switch(post.get(i))
					{
					case '+': val=num2+num1; break;
					case '-': val=num2-num1; break;
					case '*': val=num2*num1; break;
					case '/': val=num2/num1; break;
					default: val=0; 
					}
					c=Push_calc(val);
					if(c==-1) break;
				}
				else continue;
			}
		return intStack[top];
	}
}
