import java.util.ArrayList;

public class Repository<T> {
	protected ArrayList<T> list;
	public Repository()
	{
		list = new ArrayList<T>();
	}
	public void add(T obj) //am schimbat numele la functie din ,,addObj" in add ca e mai usor
	{
		this.list.add(obj);

	}
	public int size()
	{
		return this.list.size();
	}
	public T get(int index) {
	        return list.get(index);
	 }
	public ArrayList<T> getList()
	{
		return this.list;
	}
	public void update(T objectName , int index)
	{
		list.set(index, objectName);
	}
	public void remove(int index) {
	    list.remove(index);
	}

	
	
	
}



